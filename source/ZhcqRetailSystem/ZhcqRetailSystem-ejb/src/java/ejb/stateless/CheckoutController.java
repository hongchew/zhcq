package ejb.stateless;

import entity.Member;
import entity.ProductEntity;
import entity.SaleTransaction;
import entity.SaleTransactionLineItem;
import entity.ShoppingCart;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.EmptyShoppingCartException;
import util.exception.MemberNotFoundException;
import util.exception.OutOfStockException;
import util.exception.ProductNotFoundException;
import util.exception.SaleTransactionNotFoundException;
import util.exception.ShoppingCartNotFoundException;

@Stateless
@Local(CheckoutControllerLocal.class)
public class CheckoutController implements CheckoutControllerLocal {

    @EJB
    private MemberControllerLocal memberController;

    @EJB
    private ProductControllerLocal productControllerLocal;

    @PersistenceContext(unitName = "ZhcqRetailSystem-ejbPU")
    private EntityManager em;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public CheckoutController() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public ShoppingCart retrieveShoppingCartById(Long cartId) throws ShoppingCartNotFoundException {
        ShoppingCart shoppingCart = em.find(ShoppingCart.class, cartId);

        if (shoppingCart != null) {

            return shoppingCart;
        } else {
            throw new ShoppingCartNotFoundException("Shopping Cart " + cartId + " does not exist!");
        }
    }

    @Override
    public ShoppingCart retrieveShoppingCartByUserId(Long userId) throws ShoppingCartNotFoundException {
        Query query = em.createQuery("select sc from ShoppingCart sc where sc.member.memberId = :userId");
        query.setParameter("userId", userId);

        ShoppingCart sc = (ShoppingCart) query.getSingleResult();
        if (sc == null) {
            throw new ShoppingCartNotFoundException("User Id does not exist: userId=" + userId);
        } else {
            return sc;
        }
    }

    @Override
    public SaleTransaction retrieveSaleTransactionById(Long transactionId) throws SaleTransactionNotFoundException {
        SaleTransaction saleTransaction = em.find(SaleTransaction.class, transactionId);

        if (saleTransaction != null) {

            return saleTransaction;
        } else {
            throw new SaleTransactionNotFoundException("Sale Transaction " + transactionId + " does not exist!");
        }
    }

    @Override
    public void addToCart(Long cartId, Long productId, Integer quantity) throws ShoppingCartNotFoundException, ProductNotFoundException { //true if adding, false if deleting
        if(quantity < 1){ //nothing to add
            return;
        }
        ShoppingCart shoppingCart = retrieveShoppingCartById(cartId);
        ProductEntity prod = productControllerLocal.retrieveProductById(productId);

        if (shoppingCart.getProducts().contains(prod)) {
            int idx = shoppingCart.getProducts().indexOf(prod);
            shoppingCart.getQuantity().set(idx, shoppingCart.getQuantity().get(idx) + quantity);
        } else {
            shoppingCart.getProducts().add(prod);
            shoppingCart.getQuantity().add(quantity);
            prod.getShoppingcarts().add(shoppingCart);
        }

        System.out.println("***CHECK ADD***");
        for (int i = 0; i < shoppingCart.getProducts().size(); i++) {
            System.out.println("Product Id: " + shoppingCart.getProducts().get(i).getProductId());
            System.out.println("Quantity: " + shoppingCart.getQuantity().get(i));
            System.out.println();
        }
    }

    @Override
    public void removeFromCart(Long cartId, Long productId) throws ShoppingCartNotFoundException, ProductNotFoundException {
        ShoppingCart shoppingCart = retrieveShoppingCartById(cartId);
        ProductEntity prod = productControllerLocal.retrieveProductById(productId);
        if (shoppingCart.getProducts().contains(prod)) {
            int idx = shoppingCart.getProducts().indexOf(prod);
            shoppingCart.getQuantity().remove(idx);
            shoppingCart.getProducts().remove(idx);
            prod.getShoppingcarts().remove(shoppingCart);
        } else {
            throw new ProductNotFoundException("No such product on the shopping cart");
        }
    }

    @Override
    public void updateCart(Long cartId, Long productId, Integer quantity) throws ShoppingCartNotFoundException, ProductNotFoundException {
        ShoppingCart shoppingCart = retrieveShoppingCartById(cartId);
        ProductEntity prod = productControllerLocal.retrieveProductById(productId);
        if (shoppingCart.getProducts().contains(prod)) {
            if(quantity < 1) {
                removeFromCart(cartId, productId);
                        
            }else{
                int idx = shoppingCart.getProducts().indexOf(prod);
                shoppingCart.getQuantity().set(idx, quantity);
            }
        } else {
            throw new ProductNotFoundException("No such product on the shopping cart");
        }
        
    }

    @Override
    public SaleTransaction checkOut(Long cartId) throws ShoppingCartNotFoundException, EmptyShoppingCartException, OutOfStockException, MemberNotFoundException {

        ShoppingCart shoppingCart = retrieveShoppingCartById(cartId);

        if (shoppingCart.getProducts().isEmpty()) {
            throw new EmptyShoppingCartException("The shopping cart is empty.");
        }

        SaleTransaction transaction = new SaleTransaction(shoppingCart.getMember());

        for (ProductEntity pdt : shoppingCart.getProducts()) {
            System.out.println("*** Pdt in cart ID: " + pdt.getProductId());
        }

        List<SaleTransactionLineItem> list = new ArrayList<>();

        for (int i = 0; i < shoppingCart.getProducts().size(); i++) {
            ProductEntity pe = shoppingCart.getProducts().get(i);
            Integer quantity = shoppingCart.getQuantity().get(i);

            if (pe.getQuantityOnHand() < quantity) {
                throw new OutOfStockException("Product " + pe.getProductName() + " out of stock!");
            } else {

                SaleTransactionLineItem lineItem = new SaleTransactionLineItem(transaction, shoppingCart.getProducts().get(i));
                lineItem.setQuantity(shoppingCart.getQuantity().get(i));
                BigDecimal subtotal = shoppingCart.getProducts().get(i).getUnitPrice().multiply(BigDecimal.valueOf(shoppingCart.getQuantity().get(i)));
                if(pe.getPromotion() != null){
                    BigDecimal discount = pe.getPromotion().getDiscountRate();
                    discount = BigDecimal.ONE.subtract(discount);
                    subtotal = subtotal.multiply(discount);
                    lineItem.setSubTotal(subtotal);
                    lineItem.setPromotionApplied(pe.getPromotion());
                }else{
                    lineItem.setSubTotal(subtotal);
                }
                pe.setQuantityOnHand(pe.getQuantityOnHand() - quantity);
                list.add(lineItem); 
                em.persist(lineItem);
                transaction.addToTotalPrice(lineItem.getSubTotal());
                System.out.println("Pdt id: " + shoppingCart.getProducts().get(i).getProductId() + " added to new line item");
            }
            
            pe.getShoppingcarts().remove(shoppingCart);

        }
        Member member = memberController.retrieveMemberById(shoppingCart.getMember().getMemberId());
        member.getSaleTransactions().add(transaction);
        member.setLoyaltyPoints(member.getLoyaltyPoints() + transaction.getTotalPrice().intValue());
        transaction.setMember(member);
        transaction.setSaleTransactionLineItems(list); 

        
        em.persist(transaction);
        shoppingCart.getProducts().clear();
        shoppingCart.getQuantity().clear();
        em.merge(shoppingCart);
        

        return transaction;

    }
    
    @Override
    public SaleTransaction checkOutWithPoints(Long cartId) throws ShoppingCartNotFoundException, EmptyShoppingCartException, OutOfStockException, MemberNotFoundException {

        ShoppingCart shoppingCart = retrieveShoppingCartById(cartId);

        if (shoppingCart.getProducts().isEmpty()) {
            throw new EmptyShoppingCartException("The shopping cart is empty.");
        }

        SaleTransaction transaction = new SaleTransaction(shoppingCart.getMember());

        for (ProductEntity pdt : shoppingCart.getProducts()) {
            System.out.println("*** Pdt in cart ID: " + pdt.getProductId());
        }

        List<SaleTransactionLineItem> list = new ArrayList<>();

        for (int i = 0; i < shoppingCart.getProducts().size(); i++) {
            ProductEntity pe = shoppingCart.getProducts().get(i);
            Integer quantity = shoppingCart.getQuantity().get(i);

            if (pe.getQuantityOnHand() < quantity) {
                throw new OutOfStockException("Product " + pe.getProductName() + " out of stock!");
            } else {

                SaleTransactionLineItem lineItem = new SaleTransactionLineItem(transaction, shoppingCart.getProducts().get(i));
                lineItem.setQuantity(shoppingCart.getQuantity().get(i));
                BigDecimal subtotal = shoppingCart.getProducts().get(i).getUnitPrice().multiply(BigDecimal.valueOf(shoppingCart.getQuantity().get(i)));
                if(pe.getPromotion() != null){
                    BigDecimal discount = pe.getPromotion().getDiscountRate();
                    discount = BigDecimal.ONE.subtract(discount);
                    subtotal = subtotal.multiply(discount);
                    lineItem.setSubTotal(subtotal);
                    lineItem.setPromotionApplied(pe.getPromotion());
                }else{
                    lineItem.setSubTotal(subtotal);
                }
                pe.setQuantityOnHand(pe.getQuantityOnHand() - quantity);
                list.add(lineItem); 
                em.persist(lineItem);
                transaction.addToTotalPrice(lineItem.getSubTotal());
                System.out.println("Pdt id: " + shoppingCart.getProducts().get(i).getProductId() + " added to new line item");
            }
            
            pe.getShoppingcarts().remove(shoppingCart);

        }
        
        
        Member member = memberController.retrieveMemberById(shoppingCart.getMember().getMemberId());
        BigDecimal points = BigDecimal.valueOf(member.getLoyaltyPoints()).divide(BigDecimal.TEN);
        if(points.compareTo(transaction.getTotalPrice()) >= 0){ //points can pay more than total
            member.setLoyaltyPoints(member.getLoyaltyPoints() - transaction.getTotalPrice().multiply(BigDecimal.TEN).intValue());
            transaction.setTotalPrice(BigDecimal.ZERO);
        }else{
            transaction.setTotalPrice(transaction.getTotalPrice().subtract(points));
            member.setLoyaltyPoints(0);
        }
        
        member.getSaleTransactions().add(transaction);
        transaction.setMember(member);
        transaction.setSaleTransactionLineItems(list);
        
        em.persist(transaction);
        shoppingCart.getProducts().clear();
        shoppingCart.getQuantity().clear();
        em.merge(shoppingCart);
        
        
        member.setLoyaltyPoints(member.getLoyaltyPoints() + transaction.getTotalPrice().intValue());
        return transaction;

    }
}

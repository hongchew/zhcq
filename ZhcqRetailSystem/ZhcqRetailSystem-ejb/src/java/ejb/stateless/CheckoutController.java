
package ejb.stateless;

import entity.ProductEntity;
import entity.SaleTransaction;
import entity.SaleTransactionLineItem;
import entity.ShoppingCart;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.OutOfStockException;
import util.exception.ProductNotFoundException;
import util.exception.SaleTransactionNotFoundException;
import util.exception.ShoppingCartNotFoundException;


@Stateless
@Local(CheckoutControllerLocal.class)
public class CheckoutController implements CheckoutControllerLocal {

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
    public ShoppingCart retrieveShoppingCartByUserId(Long userId) throws ShoppingCartNotFoundException{
        Query query = em.createQuery("select sc from ShoppingCart sc where sc.member.memberId = :userId");
        query.setParameter("userId", userId);
        
        ShoppingCart sc = (ShoppingCart) query.getSingleResult();
        if(sc == null){
            throw new ShoppingCartNotFoundException("User Id does not exist: userId=" + userId);
        }else{
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
    public void updateCart (Long cartId, Long productId, boolean addition) throws OutOfStockException, ShoppingCartNotFoundException, ProductNotFoundException { //true if adding, false if deleting

        ShoppingCart shoppingCart = retrieveShoppingCartById(cartId);
        System.out.println("***CHECK UPDATE 1***");
        ProductEntity prod = productControllerLocal.retrieveProductById(productId);
        System.out.println("***CHECK UPDATE 2***");

        if (addition) {
            if (prod.getQuantityOnHand()<=0) {
                System.out.println("Oops! Product out of stock!");
                throw new OutOfStockException("Oops! Product out of stock!");
            }
            shoppingCart.getProducts().add(prod);
            for(ProductEntity pdt : shoppingCart.getProducts()){
                System.out.println(pdt.getProductId());
            }
            System.out.println("***CHECK ADD***");
            prod.setQuantityOnHand(prod.getQuantityOnHand()-1);
            em.merge(shoppingCart); 
            
        } else { //addtion == false
            System.out.println("***CHECK REMOVE 1***");
            if(shoppingCart.getProducts().remove(prod)){
                System.out.println("***CHECK REMOVE 2***");
                prod.setQuantityOnHand(prod.getQuantityOnHand()+1);
            }else{
                throw new ProductNotFoundException("No such product on the shopping cart");
            }
        }
      
    }
    
    @Override
    public SaleTransaction checkOut (Long cartId) throws ShoppingCartNotFoundException  {
        
        
        ShoppingCart shoppingCart = retrieveShoppingCartById(cartId);
        SaleTransaction transaction = new SaleTransaction(shoppingCart.getMember());
        
        for(ProductEntity pdt : shoppingCart.getProducts()){
            System.out.println("*** Pdt in cart ID: " + pdt.getProductId());
        }

        List <SaleTransactionLineItem> list = new ArrayList <> ();
        boolean added = false;
        for (ProductEntity product : shoppingCart.getProducts()) {
            for(SaleTransactionLineItem stli : list){
                if(Objects.equals(stli.getProductEntity().getProductId(), product.getProductId())){
                    stli.setQuantity(stli.getQuantity() + 1);
                    System.out.println("Pdt id: " + product.getProductId() + " added to existing line item");
                    added = true;
                    break;
                }
            }
            if(!added){
                SaleTransactionLineItem lineItem = new SaleTransactionLineItem (transaction, product);
                lineItem.setQuantity(1);
                list.add(lineItem);
                em.persist(lineItem);
                System.out.println("Pdt id: " + product.getProductId() + " added to new line item");
            }
            added = false;
        }
        transaction.setSaleTransactionLineItems(list);
        em.persist(transaction);
        shoppingCart.getProducts().clear();
        em.merge(shoppingCart);
        
        return transaction;
          
        
    }
}

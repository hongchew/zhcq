/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.Member;
import entity.ProductEntity;
import entity.WishList;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.AlreadyInWishlistException;
import util.exception.DeleteWishListException;
import util.exception.InputDataValidationException;
import util.exception.MemberNotFoundException;
import util.exception.ProductNotFoundException;
import util.exception.UpdateWishListException;
import util.exception.WishListNotFoundException;

@Stateless
@Local(WishListControllerLocal.class)
public class WishListController implements WishListControllerLocal {

    @EJB(name = "ProductControllerLocal")
    private ProductControllerLocal productControllerLocal;

    @EJB(name = "MemberControllerLocal")
    private MemberControllerLocal memberControllerLocal;

    @PersistenceContext(unitName = "ZhcqRetailSystem-ejbPU")
    private EntityManager em;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public WishListController() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public List<WishList> retrieveAllWishList() {
        Query query = em.createQuery("SELECT wl FROM WishList wl");

        return query.getResultList();
    }

    @Override
    public WishList retrieveWishListByMemberId(Long memberId) throws WishListNotFoundException, MemberNotFoundException {
        if (memberId == null) {
            throw new WishListNotFoundException("Member ID not provided!");
        } else {
            Member member = memberControllerLocal.retrieveMemberById(memberId);

            Query query = em.createQuery("SELECT wl FROM WishList wl WHERE  wl.member = :inMember");
            query.setParameter("inMember", member);

            try {
                return (WishList) query.getSingleResult();
            } catch (NoResultException | NonUniqueResultException ex) {
                throw new WishListNotFoundException("WishList does not exist for member of member id " + memberId + " !");
            }
        }
    }

    @Override
    public WishList retrieveWishListByWishListId(Long wishlistId) throws WishListNotFoundException {
        WishList wishList = em.find(WishList.class, wishlistId);
        if (wishList != null) {
            return wishList;
        } else {
            throw new WishListNotFoundException("WishList ID " + wishlistId + " does not exist!");
        }

    }

    @Override
    public void updateWishList(WishList wishlist, List<Long> productEntityIds) throws InputDataValidationException, WishListNotFoundException, UpdateWishListException, ProductNotFoundException {
        Set<ConstraintViolation<WishList>> constraintViolations = validator.validate(wishlist);

        if (constraintViolations.isEmpty()) {
            if (wishlist.getWishListId() != null) {
                WishList wishListToUpdate = retrieveWishListByWishListId(wishlist.getWishListId());

                if (wishListToUpdate.getMember().equals(wishlist.getMember())) {
                    if (productEntityIds != null) {
                        for (ProductEntity productEntity : wishListToUpdate.getProductEntities()) {
                            productEntity.getWishLists().remove(wishListToUpdate);
                        }

                        wishListToUpdate.getProductEntities().clear();

                        for (Long tagId : productEntityIds) {
                            ProductEntity productEntity = productControllerLocal.retrieveProductById(tagId);
                            wishListToUpdate.addProduct(productEntity);
                        }
                    }
                } else {
                    throw new UpdateWishListException("Wish List record to be updated does not match the existing record");
                }
            } else {
                throw new WishListNotFoundException("WishList ID not provided!");
            }

        } else {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }

    @Override
    public void deleteWishList(Long memberId) throws WishListNotFoundException, MemberNotFoundException, DeleteWishListException {
        WishList wishListToRemove = retrieveWishListByMemberId(memberId);

        if (wishListToRemove.getProductEntities().isEmpty()) {
            em.remove(wishListToRemove);
        } else {
            throw new DeleteWishListException("There are still products left in the wishlist!!");
        }
    }

    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<WishList>> constraintViolations) {
        String msg = "Input data validation error!:";

        for (ConstraintViolation constraintViolation : constraintViolations) {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }

        return msg;
    }
    
    @Override
    public void addProductToWishlist(Long memberId, Long pdtId) throws MemberNotFoundException, ProductNotFoundException, AlreadyInWishlistException{
        
        Member member = memberControllerLocal.retrieveMemberById(memberId);
        ProductEntity pdt = productControllerLocal.retrieveProductById(pdtId);
        if(member.getWishList().getProductEntities().contains(pdt)){
            throw new AlreadyInWishlistException("Item Already In Wishlist.");
        }
        
        member.getWishList().getProductEntities().add(pdt);
        pdt.getWishLists().add(member.getWishList());
        
    }
    
    @Override
    public void removeProductFromWishlist(Long memberId, Long pdtId) throws MemberNotFoundException, ProductNotFoundException{
        Member member = memberControllerLocal.retrieveMemberById(memberId);
        ProductEntity pdt = productControllerLocal.retrieveProductById(pdtId);
        
        
        
        if(!member.getWishList().getProductEntities().remove(pdt)){
            throw new ProductNotFoundException("Product is not in the wishlist");
        }else{
            pdt.getWishLists().remove(member.getWishList());
        }
    }

}

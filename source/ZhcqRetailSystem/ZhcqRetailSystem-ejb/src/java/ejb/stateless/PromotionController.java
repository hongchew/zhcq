/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ProductEntity;
import entity.Promotion;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.CreatePromotionException;
import util.exception.InputDataValidationException;
import util.exception.ProductNotFoundException;
import util.exception.PromotionNotFoundException;
import util.exception.UpdatePromotionException;

@Stateless
@Local(PromotionControllerLocal.class)
public class PromotionController implements PromotionControllerLocal {

    @EJB(name = "ProductControllerLocal")
    private ProductControllerLocal productControllerLocal;

    @PersistenceContext(unitName = "ZhcqRetailSystem-ejbPU")
    private EntityManager em;

    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public PromotionController() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Override
    public List<Promotion> retrieveAllPromotions() {
        Query query = em.createQuery("SELECT p FROM Promotion p");
        
        List<Promotion> promotions = query.getResultList();
        for(Promotion promotion: promotions) {
            promotion.getPromotionalProducts().size();
        }
        return promotions;
    }

    @Override
    public Promotion retrievePromotionByPromotionId(Long promotionId) throws PromotionNotFoundException {
        if (promotionId == null) {
            throw new PromotionNotFoundException("Promotion ID not provided");
        }

        Promotion promotion = em.find(Promotion.class, promotionId);

        if (promotion != null) {
            promotion.getPromotionalProducts().size();
            return promotion;
        } else {
            throw new PromotionNotFoundException("Promotion ID " + promotionId + " does not exist!");
        }
    }

    @Override
    public Promotion createNewPromotion(Promotion newPromotion, List<Long> productIds) throws CreatePromotionException, InputDataValidationException {
        Set<ConstraintViolation<Promotion>> constraintViolations = validator.validate(newPromotion);

        if (constraintViolations.isEmpty()) {
            try {
                em.persist(newPromotion);
                if (productIds != null && (!productIds.isEmpty())) {
                    for (Long productId : productIds) {
                        ProductEntity product = productControllerLocal.retrieveProductById(productId);
                        newPromotion.addProduct(product);
                    }
                    
                }
                em.flush();

                return newPromotion;
            } catch (ProductNotFoundException ex) {
                throw new CreatePromotionException("An unexpected error has occurred: " + ex.getMessage());
            }
        } else {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }

    @Override
    public void updatePromotion(Promotion promotion, List<Long> productIds) throws UpdatePromotionException {
        Set<ConstraintViolation<Promotion>> constraintViolations = validator.validate(promotion);

        if (constraintViolations.isEmpty()) {
            try {
                Promotion promotionToUpdate = retrievePromotionByPromotionId(promotion.getPromotionId());

                promotionToUpdate.setPromotionName(promotion.getPromotionName());
                promotionToUpdate.setStartDate(promotion.getStartDate());
                promotionToUpdate.setEndDate(promotion.getEndDate());
                
                //disassociate both sides
                for(ProductEntity product : promotionToUpdate.getPromotionalProducts()) {
                    product.setPromotion(null);
                    System.out.println("disassociated!");
                }
                promotionToUpdate.setPromotionalProducts(new ArrayList<ProductEntity>());
     
                
                if (productIds != null || (!productIds.isEmpty())) {
                    for (Long id : productIds) {
                        ProductEntity product = productControllerLocal.retrieveProductById(id);
                        promotionToUpdate.addProduct(product);
                        System.out.println("added product " + product.getProductName());
                    }
                }
            } catch (ProductNotFoundException | PromotionNotFoundException ex) {
                throw new UpdatePromotionException(ex.getMessage());
            }
        } else {
            throw new UpdatePromotionException("Input data for update of promotion is invalid!");

        }
    }

    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<Promotion>> constraintViolations) {
        String msg = "Input data validation error!:";

        for (ConstraintViolation constraintViolation : constraintViolations) {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }

        return msg;
    }

    @Override
    public void deletePromotion(Long promotionId) throws PromotionNotFoundException {
        Promotion promotionToRemove = retrievePromotionByPromotionId(promotionId);

        for (ProductEntity product : promotionToRemove.getPromotionalProducts()) {
            product.setPromotion(null);
        }

        em.remove(promotionToRemove);

    }
    
    

}

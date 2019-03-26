/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.Member;
import entity.ProductEntity;
import entity.WishList;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.CreateNewProductException;
import util.exception.CreateNewWishListException;
import util.exception.InputDataValidationException;


@Stateless
@Local(WishListControllerLocal.class)
public class WishListController implements WishListControllerLocal {

    @EJB(name = "MemberControllerLocal")
    private MemberSessionBeanLocal memberControllerLocal;

    @PersistenceContext(unitName = "ZhcqRetailSystem-ejbPU")
    private EntityManager em;
    
    

   private final ValidatorFactory validatorFactory;
    private final Validator validator;
    
    
    
    public WishListController()
    {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    public WishList createNewWishList(WishList newWishList, Long memberId) throws InputDataValidationException, CreateNewWishListException
    {
        Set<ConstraintViolation<WishList>>constraintViolations = validator.validate(newWishList);
        
        if(constraintViolations.isEmpty())
        {
           try{
               if(memberId == null){
                   throw new CreateNewWishListException("The new wishList must be associated with a memember!");
               } else {
                   Member newMember = memberControllerLocal.retrieveMemberById(memberId); 
                   
                    em.persist(newWishList);
                    newWishList.setMember(newMember);
                    em.flush();
           
                    return newWishList;
               }
                   
           } catch (PersistenceException ex){
               if(ex.getCause() != null && 
                        ex.getCause().getCause() != null &&
                        ex.getCause().getCause().getClass().getSimpleName().equals("SQLIntegrityConstraintViolationException"))
                {
                    throw new CreateNewWishListException("WishList already exist");
                }
                else
                {
                    throw new CreateNewWishListException("An unexpected error has occurred: " + ex.getMessage());
                }  
           } catch(Exception ex)
            {
                throw new CreateNewWishListException("An unexpected error has occurred: " + ex.getMessage());
            }
        } else { 
             throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
        
    }
    
    

    
    
    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<WishList>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }
    
}

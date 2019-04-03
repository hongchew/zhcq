/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.Category;
import entity.CoordinatedOutfit;
import entity.ProductEntity;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.CreateNewOutfitException;
import util.exception.InputDataValidationException;
import util.exception.OutfitNotFoundException;
import util.exception.ProductNotFoundException;
import util.exception.UpdateOutfitException;


@Stateless
@Local(CoordinatedOutfitControllerLocal.class)
public class CoordinatedOutfitController implements CoordinatedOutfitControllerLocal {

    @EJB(name = "ProductControllerLocal")
    private ProductControllerLocal productControllerLocal;

    @PersistenceContext(unitName = "ZhcqRetailSystem-ejbPU")
    private EntityManager em;
    
    
    
    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    
    
    public CoordinatedOutfitController() 
    {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    @Override
    public CoordinatedOutfit createNewOutfit(CoordinatedOutfit newCoordinatedOutfit, List<Long> productIds, Date date) throws InputDataValidationException, CreateNewOutfitException, ProductNotFoundException
    {
         Set<ConstraintViolation<CoordinatedOutfit>>constraintViolations = validator.validate(newCoordinatedOutfit);
        
        if(constraintViolations.isEmpty())
        {
            try{   
                if(productIds.size() <= 1){
                   throw new CreateNewOutfitException("Coordinated Outfit must be associated with more than one products!");
                } 

                for(Long productId : productIds){
                    if(productId == null){
                        throw new CreateNewOutfitException("Product ID Is invalid!");
                    }
                }
                
                System.out.println(date);
                newCoordinatedOutfit.setDateCreated(date);
                
                em.persist(newCoordinatedOutfit);

                for(Long productId: productIds){
                    ProductEntity product = productControllerLocal.retrieveProductById(productId);

                    if(product.getCoordinatedOutfit() == null)
                    {
                        newCoordinatedOutfit.addProduct(product);
                    } 
                    else 
                    {
                        throw new CreateNewOutfitException("Product already has an Outfit!");
                    }
                }
                
                em.flush();
                
                return newCoordinatedOutfit;
                
            } catch(PersistenceException ex){
                if(ex.getCause() != null && 
                        ex.getCause().getCause() != null &&
                        ex.getCause().getCause().getClass().getSimpleName().equals("SQLIntegrityConstraintViolationException"))
                {
                    throw new CreateNewOutfitException("Outfit with the same name exist!");
                }
                else
                {
                    throw new CreateNewOutfitException("An unexpected error has occurred: " + ex.getMessage());
                }
            }
            catch(Exception ex)
            {
                throw new CreateNewOutfitException("An unexpected error has occurred: " + ex.getMessage());
            }
       
            
            
        } else { 
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }
    
    @Override
    public List<CoordinatedOutfit> retrieveAllOutfits(){ //retrieved by date added
        Query query = em.createQuery("SELECT c FROM CoordinatedOutfit c ORDER BY c.dateCreated DESC");
        List<CoordinatedOutfit> outfits = query.getResultList(); 
        
        for(CoordinatedOutfit outfit: outfits)
        {
            outfit.getProductEntities().size();
        }
        return outfits;
    }
    
    @Override
    public CoordinatedOutfit retrieveOutfitById(Long outfitId) throws OutfitNotFoundException
    {
        if(outfitId == null)
        {
            throw new OutfitNotFoundException("Outfit Id Not provided");
        }
        CoordinatedOutfit outfit = em.find(CoordinatedOutfit.class, outfitId);
        
        if(outfit != null)
        {
            return outfit;
        } else {
            throw new OutfitNotFoundException("Outfit ID " + outfitId + " does not exist" );
        }
    }
    
    @Override
    public void updateOutfit(CoordinatedOutfit outfit, List<Long> productIds) throws InputDataValidationException, OutfitNotFoundException, ProductNotFoundException, UpdateOutfitException
    {
        Set<ConstraintViolation<CoordinatedOutfit>>constraintViolations = validator.validate(outfit);
        
        if(constraintViolations.isEmpty())
        {
            if(outfit.getCoordinatedOutfitId() != null){
                CoordinatedOutfit outfitToUpdate = retrieveOutfitById(outfit.getCoordinatedOutfitId());
                
                if(outfitToUpdate.getOutfitName().equals(outfit.getOutfitName())){
                    
                    if(productIds != null)
                    {
                        for(ProductEntity product : outfitToUpdate.getProductEntities())
                        {
                            product.setCoordinatedOutfit(null);
                        }
                        outfitToUpdate.getProductEntities().clear();
                    
                        for(Long productId : productIds )
                        {
                            ProductEntity product =  productControllerLocal.retrieveProductById(productId);
                            outfitToUpdate.addProduct(product);
                        }
                    }
                    
                    outfitToUpdate.setOutfitName(outfit.getOutfitName());   
                   
                    
                } else {
                    throw new UpdateOutfitException("Outfit ID does not match record");
                }
                
            } else {
                throw new OutfitNotFoundException("Outfit ID "+ outfit.getCoordinatedOutfitId() + " does not exist in the database" );
            }
        } else {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }
    
    @Override
    public void deleteOutfit(Long outfitId) throws OutfitNotFoundException
    { 
       CoordinatedOutfit outfitToDelete = retrieveOutfitById(outfitId);
       
       for (ProductEntity product : outfitToDelete.getProductEntities()){
           product.setCoordinatedOutfit(null);
       }
       outfitToDelete.getProductEntities().clear();
       em.remove(outfitToDelete);        
    }    
    
   
    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<CoordinatedOutfit>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }
    
}


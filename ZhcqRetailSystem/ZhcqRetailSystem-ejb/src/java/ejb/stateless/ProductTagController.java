/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ProductTag;
import java.util.List;
import java.util.Set;
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
import util.exception.CreateNewProductTagException;
import util.exception.DeleteTagException;
import util.exception.InputDataValidationException;
import util.exception.ProductTagNotFoundException;
import util.exception.UpdateProductTagException;


@Stateless
@Local(ProductTagControllerLocal.class)
public class ProductTagController implements ProductTagControllerLocal {

    @PersistenceContext(unitName = "ZhcqRetailSystem-ejbPU")
    private EntityManager em;
    
    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public ProductTagController() 
    {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    @Override
    public List<ProductTag> retrieveAllProductTags(){
        Query q = em.createQuery("SELECT pt FROM ProductTag pt");
        return q.getResultList();
    }
    
    @Override
     public ProductTag retrieveProductTagByTagId(Long productTagId) throws ProductTagNotFoundException
    {
        if(productTagId == null)
        {
            throw new ProductTagNotFoundException("Product Tag ID not provided");
        }
        
        ProductTag tagEntity = em.find(ProductTag.class, productTagId);
        
        if(tagEntity != null)
        {
            return tagEntity;
        }
        else
        {
            throw new ProductTagNotFoundException("Tag ID " + productTagId + " does not exist!");
        }               
    }
     
    @Override
    public ProductTag createNewProductTag(ProductTag newTag) throws InputDataValidationException, CreateNewProductTagException
    {
        Set<ConstraintViolation<ProductTag>>constraintViolations = validator.validate(newTag);
        
        if(constraintViolations.isEmpty())
        {
            try
            {
                em.persist(newTag);
                em.flush();

                return newTag;
            }
            catch(PersistenceException ex)
            {                
                if(ex.getCause() != null && 
                        ex.getCause().getCause() != null &&
                        ex.getCause().getCause().getClass().getSimpleName().equals("SQLIntegrityConstraintViolationException"))
                {
                    throw new CreateNewProductTagException("Tag with same name already exist");
                }
                else
                {
                    throw new CreateNewProductTagException("An unexpected error has occurred: " + ex.getMessage());
                }
            }
            catch(Exception ex)
            {                
                throw new CreateNewProductTagException("An unexpected error has occurred: " + ex.getMessage());
            }
        }
        else
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }
    
    @Override
    public void updateProductTag(ProductTag productTag) throws InputDataValidationException, ProductTagNotFoundException, UpdateProductTagException
    {
        Set<ConstraintViolation<ProductTag>>constraintViolations = validator.validate(productTag);
        
        if(constraintViolations.isEmpty())
        {
            if(productTag.getProductTagId() != null)
            {
                ProductTag tagToUpdate = retrieveProductTagByTagId(productTag.getProductTagId());
                Query query = em.createQuery("SELECT pt FROM ProductTag pt WHERE pt.productTagName = :inName AND pt.productTagId <> :inTagId");
                query.setParameter("inName", productTag.getProductTagName());
                query.setParameter("inTagId", productTag.getProductTagId());
                
                if(!query.getResultList().isEmpty())
                {
                    throw new UpdateProductTagException("The name of the tag to be updated is duplicated");
                }
                
                tagToUpdate.setProductTagName(productTag.getProductTagName());
                
            } else {
                throw new ProductTagNotFoundException("Tag ID not provided for tag to be updated");
            }
        } else {
            throw new InputDataValidationException("Tag ID to be updated not provided");
        }
    }
    
    @Override
    public void deleteProductTag(Long tagId) throws ProductTagNotFoundException, DeleteTagException
    {
        ProductTag tagEntityToRemove = retrieveProductTagByTagId(tagId);
        
        if(!tagEntityToRemove.getProductEntities().isEmpty())
        {
            throw new DeleteTagException("Tag ID " + tagId + " is associated with existing products and cannot be deleted!");
        }
        else
        {
            em.remove(tagEntityToRemove);
        }                
    }

    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<ProductTag>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }
}

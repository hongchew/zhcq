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
import util.exception.CreateNewTagException;
import util.exception.DeleteTagException;
import util.exception.InputDataValidationException;
import util.exception.TagNotFoundException;
import util.exception.UpdateTagException;


@Stateless
@Local(ProductControllerLocal.class)
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
    public List<ProductTag> retrieveAllTags(){
        Query q = em.createQuery("SELECT pt FROM ProductTag pt");
        return q.getResultList();
    }
    
    @Override
     public ProductTag retrieveTagByTagId(Long tagId) throws TagNotFoundException
    {
        if(tagId == null)
        {
            throw new TagNotFoundException("Tag ID not provided");
        }
        
        ProductTag tagEntity = em.find(ProductTag.class, tagId);
        
        if(tagEntity != null)
        {
            return tagEntity;
        }
        else
        {
            throw new TagNotFoundException("Tag ID " + tagId + " does not exist!");
        }               
    }
     
    @Override
    public ProductTag createNewTagEntity(ProductTag newTag) throws InputDataValidationException, CreateNewTagException
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
                    throw new CreateNewTagException("Tag with same name already exist");
                }
                else
                {
                    throw new CreateNewTagException("An unexpected error has occurred: " + ex.getMessage());
                }
            }
            catch(Exception ex)
            {                
                throw new CreateNewTagException("An unexpected error has occurred: " + ex.getMessage());
            }
        }
        else
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }
    
    @Override
    public void updateTag(ProductTag productTag) throws InputDataValidationException, TagNotFoundException, UpdateTagException
    {
        Set<ConstraintViolation<ProductTag>>constraintViolations = validator.validate(productTag);
        
        if(constraintViolations.isEmpty())
        {
            if(productTag.getProductTagId() != null)
            {
                ProductTag tagToUpdate = retrieveTagByTagId(productTag.getProductTagId());
                Query query = em.createQuery("SELECT pt FROM ProductTag pt WHERE pt.tagName = :inName AND pt.productTagId <> :inTagId");
                query.setParameter("inName", productTag.getTagName());
                query.setParameter("inTagId", productTag.getProductTagId());
                
                if(!query.getResultList().isEmpty())
                {
                    throw new UpdateTagException("The name of the tag to be updated is duplicated");
                }
                
                tagToUpdate.setTagName(productTag.getTagName());
                
            } else {
                throw new TagNotFoundException("Tag ID not provided for tag to be updated");
            }
        } else {
            throw new InputDataValidationException("Tag ID to be updated not provided");
        }
    }
    
    @Override
    public void deleteTag(Long tagId) throws TagNotFoundException, DeleteTagException
    {
        ProductTag tagEntityToRemove = retrieveTagByTagId(tagId);
        
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

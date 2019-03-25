/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.ProductTag;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.ProductTagNotFoundException;


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
     public ProductTag retrieveTagByTagId(Long tagId) throws ProductTagNotFoundException
    {
        if(tagId == null)
        {
            throw new ProductTagNotFoundException("Tag ID not provided");
        }
        
        ProductTag tagEntity = em.find(ProductTag.class, tagId);
        
        if(tagEntity != null)
        {
            return tagEntity;
        }
        else
        {
            throw new ProductTagNotFoundException("Tag ID " + tagId + " does not exist!");
        }               
    }

    
}

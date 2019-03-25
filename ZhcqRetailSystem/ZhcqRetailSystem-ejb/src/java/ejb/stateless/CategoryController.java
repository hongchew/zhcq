/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.Category;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.exception.CategoryNotFoundException;


@Stateless
@Local(CategoryControllerLocal.class)

public class CategoryController implements CategoryControllerLocal {

    @PersistenceContext(unitName = "ZhcqRetailSystem-ejbPU")
    private EntityManager em;
    
    
    @Override
    public Category retrieveCategoryByCategoryId(Long categoryId) throws CategoryNotFoundException
    {
        if(categoryId == null)
        {
            throw new CategoryNotFoundException("Category ID not provided");
        }
        
        Category categoryEntity = em.find(Category.class, categoryId);
        
        if(categoryEntity != null)
        {
            return categoryEntity;
        }
        else
        {
            throw new CategoryNotFoundException("Category ID " + categoryId + " does not exist!");
        }               
    }

}

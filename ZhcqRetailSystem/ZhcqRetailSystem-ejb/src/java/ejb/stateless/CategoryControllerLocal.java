/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.Category;
import java.util.List;
import javax.ejb.Local;
import util.exception.CategoryNotFoundException;
import util.exception.CreateNewCategoryException;
import util.exception.DeleteCategoryException;
import util.exception.InputDataValidationException;
import util.exception.UpdateCategoryException;


@Local
public interface CategoryControllerLocal {

    public Category retrieveCategoryByCategoryId(Long categoryId) throws CategoryNotFoundException;

    public void deleteCategory(Long categoryId) throws CategoryNotFoundException, DeleteCategoryException;

    public Category createNewCategoryEntity(Category newCategoryEntity) throws InputDataValidationException, CreateNewCategoryException;

    public void updateCategory(Category categoryEntity) throws InputDataValidationException, CategoryNotFoundException, UpdateCategoryException;

    public List<Category> retrieveAllCategories();
    
}

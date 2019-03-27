/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.CategoryControllerLocal;
import ejb.stateless.ProductControllerLocal;
import entity.Category;
import entity.ProductEntity;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import util.exception.CategoryNotFoundException;
import util.exception.CreateNewCategoryException;
import util.exception.CreateNewProductException;
import util.exception.DeleteCategoryException;
import util.exception.InputDataValidationException;
import util.exception.UpdateCategoryException;


@Named(value = "categoryManagementManagedBean")
@RequestScoped
public class categoryManagementManagedBean implements Serializable
{

    @EJB(name = "ProductControllerLocal")
    private ProductControllerLocal productControllerLocal;

    @EJB
    private CategoryControllerLocal categoryControllerLocal;
    
    
    
    private List<Category> categories; 
    private List<Category> filteredCategories; 
    
    private List<ProductEntity> productEntities; 
    
    private Category newCategory;
    private Category selectedCategoryToUpdate;
    private Category selectedCategoryToView;
    
    
    
    public categoryManagementManagedBean() {
    }
    
    @PostConstruct
    public void postConstruct()
    {
       productEntities = productControllerLocal.retrieveAllProducts();
       categories = categoryControllerLocal.retrieveAllCategories();
    }
    
//    public void viewProductDetails(ActionEvent event) throws IOException
//    {
//        Long productIdToView = (Long)event.getComponent().getAttributes().get("productId");
//        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("productIdToView", productIdToView);
//        FacesContext.getCurrentInstance().getExternalContext().redirect("viewProductDetails.xhtml");
//    }
    
    public void createCategory(ActionEvent event)
    {
        try
        {
            Category category = categoryControllerLocal.createNewCategoryEntity(newCategory);
            categories.add(category);
            
            newCategory = new Category();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Category created successfully (Product ID: " + category.getCategoryId() + ")", null));
        }
        catch(InputDataValidationException | CreateNewCategoryException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating the new product: " + ex.getMessage(), null));
        }
    }
    
    public void doUpdateCategory(ActionEvent event)
    {
        selectedCategoryToUpdate = (Category)event.getComponent().getAttributes().get("categoryToUpdate");
    }
    
    public void updateCateory(ActionEvent event)
    {
        try
        {
            categoryControllerLocal.updateCategory(selectedCategoryToUpdate);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Category updated successfully", null));
        }
        catch(UpdateCategoryException | InputDataValidationException | CategoryNotFoundException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while updating category: " + ex.getMessage(), null));
        }
    }
    
    public void deleteCategory(ActionEvent event)
    {
        try{
            Category categoryToDelete = (Category)event.getComponent().getAttributes().get("categoryToDelete");
            categoryControllerLocal.deleteCategory(categoryToDelete.getCategoryId());
        } catch(CategoryNotFoundException | DeleteCategoryException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while delete category: " + ex.getMessage(), null));
        }
        
    }

  
    public ProductControllerLocal getProductControllerLocal() {
        return productControllerLocal;
    }

    public void setProductControllerLocal(ProductControllerLocal productControllerLocal) {
        this.productControllerLocal = productControllerLocal;
    }

    public CategoryControllerLocal getCategoryControllerLocal() {
        return categoryControllerLocal;
    }

   
    public void setCategoryControllerLocal(CategoryControllerLocal categoryControllerLocal) {
        this.categoryControllerLocal = categoryControllerLocal;
    }

    
    public List<Category> getCategories() {
        return categories;
    }

    
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    
    public List<Category> getFilteredCategories() {
        return filteredCategories;
    }

    
    public void setFilteredCategories(List<Category> filteredCategories) {
        this.filteredCategories = filteredCategories;
    }

  
    public List<ProductEntity> getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }

   
    public Category getNewCategory() {
        return newCategory;
    }

    public void setNewCategory(Category newCategory) {
        this.newCategory = newCategory;
    }

  
    public Category getSelectedCategoryToUpdate() {
        return selectedCategoryToUpdate;
    }

  
    public void setSelectedCategoryToUpdate(Category selectedCategoryToUpdate) {
        this.selectedCategoryToUpdate = selectedCategoryToUpdate;
    }

   
    public Category getSelectedCategoryToView() {
        return selectedCategoryToView;
    }

   
    public void setSelectedCategoryToView(Category selectedCategoryToView) {
        this.selectedCategoryToView = selectedCategoryToView;
    }
}

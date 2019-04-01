/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.CategoryControllerLocal;
import ejb.stateless.ProductControllerLocal;
import ejb.stateless.ProductTagControllerLocal;
import entity.Category;
import entity.ProductEntity;
import entity.ProductTag;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.enumeration.ColourEnum;
import util.enumeration.SizeEnum;
import util.exception.CreateNewProductException;
import util.exception.InputDataValidationException;
import util.exception.ProductNotFoundException;

/**
 *
 * @author Hong Chew
 */
@Named(value = "productManagementManagedBean")
@ViewScoped
public class ProductManagementManagedBean {

    @EJB
    private ProductTagControllerLocal productTagController;

    @EJB
    private CategoryControllerLocal categoryController;

    @EJB
    private ProductControllerLocal productController;

    
    
    
    private List<ProductEntity> productEntities;
    private List<ProductEntity> filteredProductEntities;
    private ColourEnum[] colours;
    private SizeEnum[] sizes;
    
    
    private ProductEntity newProductEntity;
    private Long categoryIdNew;
    private List<String> tagIdsStringNew;
    private List<Category> categoryEntities;
    private List<ProductTag> tagEntities;

    private List<String> tagIdsStringFilter;
    
    private ProductEntity selectedProductEntityToView;    
    
    private ProductEntity selectedProductEntityToUpdate;
    private Long categoryIdUpdate;
    private List<String> tagIdsStringUpdate;
    /**
     * Creates a new instance of ProductManagementManagedBean
     */
    public ProductManagementManagedBean() {
        newProductEntity = new ProductEntity();
    }
    
    @PostConstruct
    public void postConstruct()
    {
        setProductEntities(productController.retrieveAllProducts());
        setCategoryEntities(categoryController.retrieveAllCategories());
        setTagEntities(productTagController.retrieveAllProductTags());
        setColours(ColourEnum.values());
        setSizes(SizeEnum.values());
    }
    
    
    
    public void createNewProduct(ActionEvent event)
    {
        List<Long> tagIdsNew = null;
        
        if(getCategoryIdNew() == 0)
        {
            setCategoryIdNew(null);
        }
        
        if(getTagIdsStringNew() != null && (!tagIdsStringNew.isEmpty()))
        {
            tagIdsNew = new ArrayList<>();
            
            for(String tagIdString:getTagIdsStringNew())
            {
                tagIdsNew.add(Long.valueOf(tagIdString));
            }
        }
        
        try
        {
            ProductEntity pe = productController.createNewProduct(getNewProductEntity(), getCategoryIdNew(), tagIdsNew);
            getProductEntities().add(pe);
            
            setNewProductEntity(new ProductEntity());
            setCategoryIdNew(null);
            setTagIdsStringNew(null);
            

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New product created successfully (Product ID: " + pe.getProductId() + ")", null));
        }
        catch(InputDataValidationException | CreateNewProductException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating the new product: " + ex.getMessage(), null));
        }
    }
    
    public void updateProduct(ActionEvent event)
    {
        List<Long> tagIdsUpdate = null;
        
        if(getCategoryIdUpdate()  == 0)
        {
            setCategoryIdUpdate(null);
        }
        
        if(getTagIdsStringUpdate() != null && (!tagIdsStringUpdate.isEmpty()))
        {
            tagIdsUpdate = new ArrayList<>();
            
            for(String tagIdString:getTagIdsStringUpdate())
            {
                tagIdsUpdate.add(Long.valueOf(tagIdString));
            }
        }
        
        try
        {
            productController.updateProduct(getSelectedProductEntityToUpdate(), getCategoryIdUpdate(), tagIdsUpdate);
                        
            for(Category c:getCategoryEntities())
            {
                if(c.getCategoryId().equals(getCategoryIdUpdate()))
                {
                    getSelectedProductEntityToUpdate().setProductCategory(c);
                    break;
                }                
            }
            
            getSelectedProductEntityToUpdate().getProductTags().clear();
            
            for(ProductTag tag:getTagEntities())
            {
                if(tagIdsUpdate.contains(tag.getProductTagId()))
                {
                    getSelectedProductEntityToUpdate().getProductTags().add(tag);
                }                
            }

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product updated successfully", null));
        }
        catch(ProductNotFoundException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while updating product: " + ex.getMessage(), null));
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }
    
    public void deleteProduct(ActionEvent event)
    {
        try
        {
            ProductEntity productEntityToDelete = (ProductEntity)event.getComponent().getAttributes().get("productEntityToDelete");
            productController.deleteProduct(productEntityToDelete.getProductId());
            
            getProductEntities().remove(productEntityToDelete);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product deleted successfully", null));
        }
        catch(ProductNotFoundException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while deleting product: " + ex.getMessage(), null));
        }
        catch(Exception ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An unexpected error has occurred: " + ex.getMessage(), null));
        }
    }

    /**
     * @return the productEntities
     */
    public List<ProductEntity> getProductEntities() {
        return productEntities;
    }

    /**
     * @param productEntities the productEntities to set
     */
    public void setProductEntities(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }

    /**
     * @return the filteredProductEntities
     */
    public List<ProductEntity> getFilteredProductEntities() {
        return filteredProductEntities;
    }

    /**
     * @param filteredProductEntities the filteredProductEntities to set
     */
    public void setFilteredProductEntities(List<ProductEntity> filteredProductEntities) {
        this.filteredProductEntities = filteredProductEntities;
    }

    /**
     * @return the newProductEntity
     */
    public ProductEntity getNewProductEntity() {
        return newProductEntity;
    }

    /**
     * @param newProductEntity the newProductEntity to set
     */
    public void setNewProductEntity(ProductEntity newProductEntity) {
        this.newProductEntity = newProductEntity;
    }

    /**
     * @return the categoryIdNew
     */
    public Long getCategoryIdNew() {
        return categoryIdNew;
    }

    /**
     * @param categoryIdNew the categoryIdNew to set
     */
    public void setCategoryIdNew(Long categoryIdNew) {
        this.categoryIdNew = categoryIdNew;
    }

    /**
     * @return the tagIdsStringNew
     */
    public List<String> getTagIdsStringNew() {
        return tagIdsStringNew;
    }

    /**
     * @param tagIdsStringNew the tagIdsStringNew to set
     */
    public void setTagIdsStringNew(List<String> tagIdsStringNew) {
        this.tagIdsStringNew = tagIdsStringNew;
    }

    /**
     * @return the categoryEntities
     */
    public List<Category> getCategoryEntities() {
        return categoryEntities;
    }

    /**
     * @param categoryEntities the categoryEntities to set
     */
    public void setCategoryEntities(List<Category> categoryEntities) {
        this.categoryEntities = categoryEntities;
    }

    /**
     * @return the tagEntities
     */
    public List<ProductTag> getTagEntities() {
        return tagEntities;
    }

    /**
     * @param tagEntities the tagEntities to set
     */
    public void setTagEntities(List<ProductTag> tagEntities) {
        this.tagEntities = tagEntities;
    }

    /**
     * @return the selectedProductEntityToView
     */
    public ProductEntity getSelectedProductEntityToView() {
        return selectedProductEntityToView;
    }

    /**
     * @param selectedProductEntityToView the selectedProductEntityToView to set
     */
    public void setSelectedProductEntityToView(ProductEntity selectedProductEntityToView) {
        this.selectedProductEntityToView = selectedProductEntityToView;
    }

    /**
     * @return the selectedProductEntityToUpdate
     */
    public ProductEntity getSelectedProductEntityToUpdate() {
        return selectedProductEntityToUpdate;
    }

    /**
     * @param selectedProductEntityToUpdate the selectedProductEntityToUpdate to set
     */
    public void setSelectedProductEntityToUpdate(ProductEntity selectedProductEntityToUpdate) {
        this.selectedProductEntityToUpdate = selectedProductEntityToUpdate;
    }

    /**
     * @return the categoryIdUpdate
     */
    public Long getCategoryIdUpdate() {
        return categoryIdUpdate;
    }

    /**
     * @param categoryIdUpdate the categoryIdUpdate to set
     */
    public void setCategoryIdUpdate(Long categoryIdUpdate) {
        this.categoryIdUpdate = categoryIdUpdate;
    }

    /**
     * @return the tagIdsStringUpdate
     */
    public List<String> getTagIdsStringUpdate() {
        return tagIdsStringUpdate;
    }

    /**
     * @param tagIdsStringUpdate the tagIdsStringUpdate to set
     */
    public void setTagIdsStringUpdate(List<String> tagIdsStringUpdate) {
        this.tagIdsStringUpdate = tagIdsStringUpdate;
    }

    /**
     * @return the colours
     */
    public ColourEnum[] getColours() {
        return colours;
    }


    /**
     * @param colours the colours to set
     */
    public void setColours(ColourEnum[] colours) {
        this.colours = colours;
    }

    /**
     * @return the sizes
     */
    public SizeEnum[] getSizes() {
        return sizes;
    }

    /**
     * @param sizes the sizes to set
     */
    public void setSizes(SizeEnum[] sizes) {
        this.sizes = sizes;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.ProductControllerLocal;
import entity.ProductEntity;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import util.exception.CategoryNotFoundException;
import util.exception.InputDataValidationException;
import util.exception.ProductNotFoundException;
import util.exception.TagNotFoundException;
import util.exception.UpdateProductException;

/**
 *
 * @author Hong Chew
 */
@Named(value = "updateProductManagedBean")
@RequestScoped
public class UpdateProductManagedBean {

    @EJB
    private ProductControllerLocal productController;

    /**
     * Creates a new instance of UpdateProductManagedBean
     */
    private ProductEntity product;
    private Long categoryId;
    private List<Long> tagIds;
    
    
    public UpdateProductManagedBean() {
        product = new ProductEntity();
    }
    
    public void updateProduct(){
        try {
            long pdtId = productController.updateProduct(getProduct(), getCategoryId(), getTagIds());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product updated successfully: " + pdtId, "Product updated successfully: " + pdtId ));
            
        } catch (InputDataValidationException | ProductNotFoundException | CategoryNotFoundException | TagNotFoundException | UpdateProductException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error updating product:" + ex.getMessage(), "Error updating product:" + ex.getMessage()));
        }
        
        
    }

    /**
     * @return the product
     */
    public ProductEntity getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    /**
     * @return the categoryId
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the tagIds
     */
    public List<Long> getTagIds() {
        return tagIds;
    }

    /**
     * @param tagIds the tagIds to set
     */
    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }
    
}

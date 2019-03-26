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
import util.exception.CreateNewProductException;
import util.exception.InputDataValidationException;

/**
 *
 * @author Hong Chew
 */
@Named(value = "createNewProductManagedBean")
@RequestScoped
public class CreateNewProductManagedBean {

    @EJB
    private ProductControllerLocal productController;

    private ProductEntity newProduct;
    private Long categoryId;
    private List<Long> tagIds;
    
    /**
     * Creates a new instance of createNewProductManagedBean
     */
    public CreateNewProductManagedBean() {
        newProduct = new ProductEntity();
    }
    
    public void createNewProduct(){
        try {
            Long newPdtId = productController.createNewProduct(newProduct, categoryId, tagIds).getProductId();   
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New product created successfully: " + newPdtId, "New product created successfully: " + newPdtId ));
        
        } catch (InputDataValidationException | CreateNewProductException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error creating product:" + ex.getMessage(), "Error creating product:" + ex.getMessage()));
        }
    }

    /**
     * @return the newProduct
     */
    public ProductEntity getNewProduct() {
        return newProduct;
    }

    /**
     * @param newProduct the newProduct to set
     */
    public void setNewProduct(ProductEntity newProduct) {
        this.newProduct = newProduct;
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

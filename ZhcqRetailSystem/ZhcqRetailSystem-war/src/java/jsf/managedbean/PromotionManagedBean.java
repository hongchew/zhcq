/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.ProductControllerLocal;
import ejb.stateless.PromotionControllerLocal;
import entity.ProductEntity;
import entity.Promotion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.exception.CreatePromotionException;
import util.exception.InputDataValidationException;
import util.exception.PromotionNotFoundException;
import util.exception.UpdatePromotionException;

@Named
@ViewScoped
public class PromotionManagedBean implements Serializable {

    @EJB(name = "ProductControllerLocal")
    private ProductControllerLocal productControllerLocal;

    @EJB(name = "PromotionControllerLocal")
    private PromotionControllerLocal promotionControllerLocal;

    private Date currentDate;
    
    private List<ProductEntity> productEntities;
    private List<String> createProductIds;
    private List<String> updateProductIds;

    private List<Promotion> promotions;
    private List<Promotion> filteredPromotions;

    private Promotion newPromotion;
    private Promotion promotionToView;
    private Promotion promotionToUpdate;
    private Promotion promotionToDelete;

    public PromotionManagedBean() {
    }

    @PostConstruct
    public void postConstruct() {
        currentDate = new Date();
        promotions = promotionControllerLocal.retrieveAllPromotions();
        productEntities = productControllerLocal.retrieveAllProducts();
        newPromotion = new Promotion();
        createProductIds = new ArrayList<String>();
        updateProductIds = new ArrayList<String>();
    }

    public void createPromotion(ActionEvent event) {
        List<Long> createLongIds = new ArrayList<Long>();
        for(String id : createProductIds) {
            createLongIds.add(Long.valueOf(id));
        }
        try {
            promotionControllerLocal.createNewPromotion(newPromotion, createLongIds);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully created promotion!", null));
            newPromotion = new Promotion();
            createProductIds.clear();
        } catch (CreatePromotionException| InputDataValidationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error creating promotion: " + ex.getMessage(), null));
        }
    }

    public void updatePromotion(ActionEvent event) {
        List<Long> updateLongIds = new ArrayList<Long>();
        for(String id : updateProductIds) {
            updateLongIds.add(Long.valueOf(id));
        }
        try {
            promotionControllerLocal.updatePromotion(promotionToUpdate, updateLongIds);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully updated promotion: ", null));
        } catch (UpdatePromotionException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to update promotion: " + ex.getMessage(), null));
        }
    }

    public void deletePromotion(ActionEvent event) {
        try {
            promotionToDelete = (Promotion) event.getComponent().getAttributes().get("promotionToDelete");
            promotionControllerLocal.deletePromotion(promotionToDelete.getPromotionId());
            promotions.remove(promotionToDelete);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Promotion deleted successfully!", null));
        } catch (PromotionNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to delete promotion" + ex.getMessage(), null));
        }
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public List<Promotion> getFilteredPromotions() {
        return filteredPromotions;
    }

    public void setFilteredPromotions(List<Promotion> filteredPromotions) {
        this.filteredPromotions = filteredPromotions;
    }

    public Promotion getNewPromotion() {
        return newPromotion;
    }

    public void setNewPromotion(Promotion newPromotion) {
        this.newPromotion = newPromotion;
    }

    public Promotion getPromotionToView() {
        return promotionToView;
    }

    public void setPromotionToView(Promotion promotionToView) {
        this.promotionToView = promotionToView;
    }

    public Promotion getPromotionToUpdate() {
        return promotionToUpdate;
    }

    public void setPromotionToUpdate(Promotion promotionToUpdate) {
        this.promotionToUpdate = promotionToUpdate;
        updateProductIds.clear();
        for(ProductEntity p : promotionToUpdate.getPromotionalProducts()) {
            updateProductIds.add(p.getProductId().toString());
        }
    }

    public Promotion getPromotionToDelete() {
        return promotionToDelete;
    }

    public void setPromotionToDelete(Promotion promotionToDelete) {
        this.promotionToDelete = promotionToDelete;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public List<ProductEntity> getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }


    public List<String> getUpdateProductIds() {
        return updateProductIds;
    }

    public List<String> getCreateProductIds() {
        return createProductIds;
    }

    public void setCreateProductIds(List<String> createProductIds) {
        this.createProductIds = createProductIds;
    }

    public void setUpdateProductIds(List<String> updateProductIds) {
        this.updateProductIds = updateProductIds;
    }

}

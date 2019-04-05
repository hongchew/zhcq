/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.PromotionControllerLocal;
import entity.Promotion;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.exception.InputDataValidationException;
import util.exception.PromotionNotFoundException;

@Named
@ViewScoped
public class PromotionManagedBean implements Serializable {

    @EJB(name = "PromotionControllerLocal")
    private PromotionControllerLocal promotionControllerLocal;

    private Date currentDate;

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
        newPromotion = new Promotion();
    }

    public void createPromotion(ActionEvent event) {
        try {
            promotionControllerLocal.createNewPromotion(newPromotion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully created promotion!", null));
            newPromotion = new Promotion();
        } catch (InputDataValidationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error creating promotion: " + ex.getMessage(), null));
        }
    }

    public void updatePromotion(ActionEvent event) {
        try {
            promotionControllerLocal.updatePromotion(newPromotion);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully updated promotion: ", null));
        } catch (InputDataValidationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to update promotion: " + ex.getMessage(), null));
        }
    }

    public void deletePromotion(ActionEvent event) {
        try {
            promotionToDelete = (Promotion) event.getComponent().getAttributes().get("promotionToDelete");
            promotionControllerLocal.deletePromotion(promotionToDelete.getPromotionId());
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

}


package jsf.managedbean;

import ejb.stateless.CoordinatedOutfitControllerLocal;
import ejb.stateless.ProductControllerLocal;
import entity.CoordinatedOutfit;
import entity.ProductEntity;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.exception.CreateNewOutfitException;
import util.exception.InputDataValidationException;
import util.exception.OutfitNotFoundException;
import util.exception.ProductNotFoundException;
import util.exception.UpdateOutfitException;


@Named(value = "outfitManagementManagedBean")
@ViewScoped
public class OutfitManagementManagedBean implements Serializable {

    @EJB(name = "ProductControllerLocal")
    private ProductControllerLocal productControllerLocal;

    @EJB(name = "CoordinatedOutfitControllerLocal")
    private CoordinatedOutfitControllerLocal coordinatedOutfitControllerLocal;

    private List<CoordinatedOutfit> outfits; 
    //private List<CoordinatedOutfit> filiteredOutfits; 
    
    
    private CoordinatedOutfit newCoordinatedOutfit; 
    private List<String> newProductIds;
    
    private CoordinatedOutfit selectedOutfitToView; 
    private CoordinatedOutfit selectedOutfitToUpdate; 
    private List<ProductEntity> productEntities;
    
    private List<String> updatedProductIds;
    
    
    public OutfitManagementManagedBean() 
    {
        newCoordinatedOutfit = new CoordinatedOutfit();
    }
    
    @PostConstruct
    public void postConstruct()
    {
        outfits = coordinatedOutfitControllerLocal.retrieveAllOutfits();
    }
    
    public void viewOutfitDetails(ActionEvent event) throws IOException
    {
        Long outfitIdToView = (Long)event.getComponent().getAttributes().get("outfitId");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("outfitIdToView", outfitIdToView);
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewOutfitDetails.xhtml");
    }
    
    public void createNewOutfit(ActionEvent event)
    {
        List<Long>productIdsNew = null;
        
        if(newProductIds != null && (!newProductIds.isEmpty()))
        {
            productIdsNew = new ArrayList<>();
            for(String productIdString : newProductIds)
            {
                productIdsNew.add(Long.valueOf(productIdString));
            }
        }
        
        try{
            CoordinatedOutfit outfit = coordinatedOutfitControllerLocal.createNewOutfit(newCoordinatedOutfit, productIdsNew);
            outfits.add(outfit);
            newCoordinatedOutfit = new CoordinatedOutfit();
            newProductIds = null;
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Outfit created successfully (Cordinated Outfit ID: " + outfit.getCoordinatedOutfitId() + ")", null));
        } catch(InputDataValidationException | CreateNewOutfitException | ProductNotFoundException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating the new Outfit: " + ex.getMessage(), null));
        }
        
    }
    
    public void doUpdateOutfit(ActionEvent event)
    {
        selectedOutfitToUpdate = (CoordinatedOutfit)event.getComponent().getAttributes().get("outfitToUpdate");
        for(ProductEntity product: selectedOutfitToUpdate.getProductEntities())
        {
            updatedProductIds.add(product.getProductId().toString());
        }
    }
    
    public void UpdateOutfit(ActionEvent event)
    {
        List<Long>productIdsUpdate = null; 
        
        if( updatedProductIds!= null && (!updatedProductIds.isEmpty()))
        {
            productIdsUpdate = new ArrayList<>();
            for(String productIdString : updatedProductIds)
            {
                productIdsUpdate.add(Long.valueOf(productIdString));
            }
        }
            
        try{
            coordinatedOutfitControllerLocal.updateOutfit(selectedOutfitToUpdate, productIdsUpdate);
            
            selectedOutfitToUpdate.getProductEntities().clear();
            
            for(ProductEntity pe : productEntities)
            {
                if(productIdsUpdate.contains(pe.getProductId()))
                {
                    selectedOutfitToUpdate.getProductEntities().add(pe);
                }
            }
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Outfit Updated Successfully", null));
            
        } catch(InputDataValidationException | ProductNotFoundException | UpdateOutfitException | OutfitNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has Occured when updating outfit", null));
        }
         
    }
    
    public void deleteProduct(ActionEvent event)
    {
        try
        {    
            CoordinatedOutfit outfitToDelete = (CoordinatedOutfit)event.getComponent().getAttributes().get("outfitToDelete");
            coordinatedOutfitControllerLocal.deleteOutfit(outfitToDelete.getCoordinatedOutfitId());
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucessfully deleted outfit!", null));
        } 
        catch(OutfitNotFoundException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "An error has occurred when deleting Outfit", null));
        }
    }

    /**
     * @return the productControllerLocal
     */
    public ProductControllerLocal getProductControllerLocal() {
        return productControllerLocal;
    }

    /**
     * @param productControllerLocal the productControllerLocal to set
     */
    public void setProductControllerLocal(ProductControllerLocal productControllerLocal) {
        this.productControllerLocal = productControllerLocal;
    }

    /**
     * @return the coordinatedOutfitControllerLocal
     */
    public CoordinatedOutfitControllerLocal getCoordinatedOutfitControllerLocal() {
        return coordinatedOutfitControllerLocal;
    }

    /**
     * @param coordinatedOutfitControllerLocal the coordinatedOutfitControllerLocal to set
     */
    public void setCoordinatedOutfitControllerLocal(CoordinatedOutfitControllerLocal coordinatedOutfitControllerLocal) {
        this.coordinatedOutfitControllerLocal = coordinatedOutfitControllerLocal;
    }

    /**
     * @return the outfits
     */
    public List<CoordinatedOutfit> getOutfits() {
        return outfits;
    }

    /**
     * @param outfits the outfits to set
     */
    public void setOutfits(List<CoordinatedOutfit> outfits) {
        this.outfits = outfits;
    }

    /**
     * @return the newCoordinatedOutfit
     */
    public CoordinatedOutfit getNewCoordinatedOutfit() {
        return newCoordinatedOutfit;
    }

    /**
     * @param newCoordinatedOutfit the newCoordinatedOutfit to set
     */
    public void setNewCoordinatedOutfit(CoordinatedOutfit newCoordinatedOutfit) {
        this.newCoordinatedOutfit = newCoordinatedOutfit;
    }

    /**
     * @return the newProductIds
     */
    public List<String> getNewProductIds() {
        return newProductIds;
    }

    /**
     * @param newProductIds the newProductIds to set
     */
    public void setNewProductIds(List<String> newProductIds) {
        this.newProductIds = newProductIds;
    }

    /**
     * @return the selectedOutfitToView
     */
    public CoordinatedOutfit getSelectedOutfitToView() {
        return selectedOutfitToView;
    }

    /**
     * @param selectedOutfitToView the selectedOutfitToView to set
     */
    public void setSelectedOutfitToView(CoordinatedOutfit selectedOutfitToView) {
        this.selectedOutfitToView = selectedOutfitToView;
    }

    /**
     * @return the selectedOutfitToUpdate
     */
    public CoordinatedOutfit getSelectedOutfitToUpdate() {
        return selectedOutfitToUpdate;
    }

    /**
     * @param selectedOutfitToUpdate the selectedOutfitToUpdate to set
     */
    public void setSelectedOutfitToUpdate(CoordinatedOutfit selectedOutfitToUpdate) {
        this.selectedOutfitToUpdate = selectedOutfitToUpdate;
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
     * @return the updatedProductIds
     */
    public List<String> getUpdatedProductIds() {
        return updatedProductIds;
    }

    /**
     * @param updatedProductIds the updatedProductIds to set
     */
    public void setUpdatedProductIds(List<String> updatedProductIds) {
        this.updatedProductIds = updatedProductIds;
    }
    
    
    
}

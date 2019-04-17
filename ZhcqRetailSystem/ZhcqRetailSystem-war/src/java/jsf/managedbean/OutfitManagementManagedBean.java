
package jsf.managedbean;

import ejb.stateless.CoordinatedOutfitControllerLocal;
import ejb.stateless.ProductControllerLocal;
import entity.CoordinatedOutfit;
import entity.ProductEntity;
import java.io.IOException;
import java.io.Serializable;
import java.sql.Timestamp;
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
import util.exception.CreateNewOutfitException;
import util.exception.InputDataValidationException;
import util.exception.OutfitNotFoundException;
import util.exception.ProductNotFoundException;
import util.exception.UpdateOutfitException;


@Named
@ViewScoped
public class OutfitManagementManagedBean implements Serializable {

    @EJB(name = "ProductControllerLocal")
    private ProductControllerLocal productControllerLocal;

    @EJB(name = "CoordinatedOutfitControllerLocal")
    private CoordinatedOutfitControllerLocal coordinatedOutfitControllerLocal;

    private List<CoordinatedOutfit> outfits; 
    private List<CoordinatedOutfit> filteredOutfits; 
    private List<ProductEntity> availableProducts; 
    private List<ProductEntity> allProducts; 
    
    
    private CoordinatedOutfit newCoordinatedOutfit; 
    private List<String> newProductIds;
    
    private CoordinatedOutfit selectedOutfitToView; 
    private CoordinatedOutfit selectedOutfitToUpdate; 
    
    
    private List<String> updatedProductIds;
    
    
    public OutfitManagementManagedBean() 
    {
        newCoordinatedOutfit = new CoordinatedOutfit();
    }
    
    @PostConstruct
    public void postConstruct()
    {
        outfits = coordinatedOutfitControllerLocal.retrieveAllOutfits();
        availableProducts = productControllerLocal.productsAvailableForOutfit();
        allProducts = productControllerLocal.retrieveAllProducts();
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
            CoordinatedOutfit outfit = coordinatedOutfitControllerLocal.createNewOutfit(newCoordinatedOutfit, productIdsNew, new Date());
            outfits.add(outfit);
            newCoordinatedOutfit = new CoordinatedOutfit();
            newProductIds = null;
            
            availableProducts = productControllerLocal.productsAvailableForOutfit();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "New Outfit created successfully (Cordinated Outfit ID: " + outfit.getCoordinatedOutfitId() + ")", null));
        } catch(InputDataValidationException | CreateNewOutfitException | ProductNotFoundException ex)
        {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has occurred while creating the new Outfit: " + ex.getMessage(), null));
        }
        
    }
    
    public void doUpdateOutfit(ActionEvent event)
    {
        selectedOutfitToUpdate = (CoordinatedOutfit)event.getComponent().getAttributes().get("outfitToUpdate");
        
       // System.out.println("NAME OF SELECTED OUTFIT IS : " + selectedOutfitToUpdate.getOutfitName());
        
        updatedProductIds = new ArrayList<>();
        
        for(ProductEntity product: selectedOutfitToUpdate.getProductEntities())
        {
            updatedProductIds.add(product.getProductId().toString());
            //System.out.println("PRODUCT IS ADDED");
        }
        availableProducts = productControllerLocal.productsAvailableForOutfit();
    }
    
    public void UpdateOutfit(ActionEvent event)
    {
        List<Long> productIdsUpdate = null; 
        
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
            
            for(ProductEntity pe : allProducts)
            {
                if(productIdsUpdate.contains(pe.getProductId()))
                {
                    selectedOutfitToUpdate.getProductEntities().add(pe);
                }
            }
            availableProducts = productControllerLocal.productsAvailableForOutfit();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Outfit Updated Successfully", null));
            
        } catch(InputDataValidationException | ProductNotFoundException | UpdateOutfitException | OutfitNotFoundException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "An error has Occured when updating outfit", null));
        }
         
    }
    
    public void deleteOutfit(ActionEvent event)
    {
        try
        {    
            CoordinatedOutfit outfitToDelete = (CoordinatedOutfit)event.getComponent().getAttributes().get("outfitToDelete");
            coordinatedOutfitControllerLocal.deleteOutfit(outfitToDelete.getCoordinatedOutfitId());
            outfits.remove(outfitToDelete);
            availableProducts = productControllerLocal.productsAvailableForOutfit();
            
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

    /**
     * @return the filteredOutfits
     */
    public List<CoordinatedOutfit> getFilteredOutfits() {
        return filteredOutfits;
    }

    /**
     * @param filteredOutfits the filteredOutfits to set
     */
    public void setFilteredOutfits(List<CoordinatedOutfit> filteredOutfits) {
        this.filteredOutfits = filteredOutfits;
    }

    /**
     * @return the availableProducts
     */
    public List<ProductEntity> getAvailableProducts() {
        return availableProducts;
    }

    /**
     * @param availableProducts the availableProducts to set
     */
    public void setAvailableProducts(List<ProductEntity> availableProducts) {
        this.availableProducts = availableProducts;
    }

    /**
     * @return the allProducts
     */
    public List<ProductEntity> getAllProducts() {
        return allProducts;
    }

    /**
     * @param allProducts the allProducts to set
     */
    public void setAllProducts(List<ProductEntity> allProducts) {
        this.allProducts = allProducts;
    }
    
    
    
}

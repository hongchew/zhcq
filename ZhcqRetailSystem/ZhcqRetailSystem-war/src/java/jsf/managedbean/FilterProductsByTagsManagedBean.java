/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.ProductControllerLocal;
import ejb.stateless.ProductTagControllerLocal;
import entity.ProductEntity;
import entity.ProductTag;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Hong Chew
 */
@Named(value = "filterProductsByTagsManagedBean")
@ViewScoped
public class FilterProductsByTagsManagedBean implements Serializable{

    @EJB
    private ProductControllerLocal productController;

    @EJB
    private ProductTagControllerLocal productTagController;

    
    
    private String condition;
    private List<String> selectedProductTagIds;
    private List<SelectItem> selectItems;
    private List<ProductEntity> productEntities;
    private ProductEntity selectedProductEntityToView;
    
    
    
    public FilterProductsByTagsManagedBean()
    {
        condition = "OR";
    }
    
    
    
    @PostConstruct
    public void postConstruct()
    {
        List<ProductTag> productTags = productTagController.retrieveAllProductTags();
        selectItems = new ArrayList<>();
        
        for(ProductTag productTag:productTags)
        {
            selectItems.add(new SelectItem(productTag.getProductTagId(), productTag.getProductTagName(), productTag.getProductTagName()));
        }
        
        
        // Optional demonstration of the use of custom converter
        // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("TagEntityConverter_tagEntities", tagEntities);
        
        condition = (String)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("productFilterCondition");
        selectedProductTagIds = (List<String>)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("productFilterTags");
        
        filterProduct();
    }
    
    
    
    @PreDestroy
    public void preDestroy()
    {
        // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("TagEntityConverter_tagEntities", null);
        // FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("TagEntityConverter_tagEntities", null);
    }
    
    
    
    public void filterProduct()
    {
        List<Long> tagIds = new ArrayList<>();
        
        if(getSelectedProductTagIds() != null && getSelectedProductTagIds().size() > 0)
        {
            for(String tagId:getSelectedProductTagIds())
            {
                tagIds.add(Long.valueOf(tagId));
            }
            
            productEntities = productController.filterProductsByTags(tagIds, condition);
        }
        else
        {
            productEntities = productController.retrieveAllProducts();
        }
    }
    
    
    
    public void viewProductDetails(ActionEvent event) throws IOException
    {
        Long productIdToView = (Long)event.getComponent().getAttributes().get("productId");
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("productIdToView", productIdToView);
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("backMode", "filterProductsByTags");
        FacesContext.getCurrentInstance().getExternalContext().redirect("viewProductDetails.xhtml");
    }

    
    
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) 
    {
        this.condition = condition;
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productFilterCondition", condition);
    }
    
    public List<String> getProductSelectedTagIds() {
        return getSelectedProductTagIds();
    }

    public void setSelectedProductTagIds(List<String> selectedProductTagIds) 
    {
        this.selectedProductTagIds = selectedProductTagIds;
        
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("productFilterTags", selectedProductTagIds);
    }

    public List<SelectItem> getSelectItems() {
        return selectItems;
    }

    public void setSelectItems(List<SelectItem> selectItems) {
        this.selectItems = selectItems;
    }    

    public List<ProductEntity> getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }
    
    public ProductEntity getSelectedProductEntityToView() {
        return selectedProductEntityToView;
    }

    public void setSelectedProductEntityToView(ProductEntity selectedProductEntityToView) {
        this.selectedProductEntityToView = selectedProductEntityToView;
    }

    /**
     * @return the selectedProductTagIds
     */
    public List<String> getSelectedProductTagIds() {
        return selectedProductTagIds;
    }
    
}

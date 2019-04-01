
package jsf.managedbean;

import ejb.stateless.ProductTagControllerLocal;
import entity.ProductTag;
import entity.ProductTag;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import util.exception.ProductTagNotFoundException;
import util.exception.CreateNewProductTagException;
import util.exception.DeleteTagException;
import util.exception.InputDataValidationException;
import util.exception.UpdateProductTagException;

@Named(value = "tagManagedBean")
@ViewScoped
public class TagManagedBean implements Serializable{

    @EJB(name = "ProductTagControllerLocal")
    private ProductTagControllerLocal productTagControllerLocal;

    private List<ProductTag> productTags;
    private List<ProductTag> filteredProductTags;

    private ProductTag newProductTag;
    private ProductTag productTagToView;
    private ProductTag productTagToUpdate;
    private ProductTag productTagToDelete;
    
    public TagManagedBean() {
    }
    
    @PostConstruct
    public void postConstruct() {
        productTags = productTagControllerLocal.retrieveAllProductTags();
        newProductTag = new ProductTag();
    }

    public void createNewProductTag(ActionEvent event) {
        try {
            productTagControllerLocal.createNewProductTag(newProductTag);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ProductTag created successfully", null));
            newProductTag = new ProductTag();
        } catch (CreateNewProductTagException | InputDataValidationException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to create ProductTag" + ex.getMessage(), null));
        }
    }

    public void deleteProductTag(ActionEvent event) {
        try {
            productTagToDelete = (ProductTag) event.getComponent().getAttributes().get("productTagToDelete");
            productTagControllerLocal.deleteProductTag(productTagToDelete.getProductTagId());
            productTags.remove(productTagToDelete);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully deleted ProductTag", null));    
        } catch (ProductTagNotFoundException | DeleteTagException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to delete ProductTag " + ex.getMessage(), null));
        }
    }
    
    public void doUpdateProductTag(ActionEvent event) {
        productTagToUpdate = (ProductTag) event.getComponent().getAttributes().get("productTagToUpdate");
    }

    public void updateCProductTag(ActionEvent event) {
        try {
            productTagControllerLocal.updateProductTag(productTagToUpdate);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully updated ProductTag", null));
        } catch (ProductTagNotFoundException | InputDataValidationException | UpdateProductTagException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to update ProductTag" + ex.getMessage(), null));
        }
    }

    public List<ProductTag> getProductTags() {
        return productTags;
    }

    public void setProductTags(List<ProductTag> productTags) {
        this.productTags = productTags;
    }

    public List<ProductTag> getFilteredProductTags() {
        return filteredProductTags;
    }

    public void setFilteredProductTags(List<ProductTag> filteredProductTags) {
        this.filteredProductTags = filteredProductTags;
    }

    public ProductTag getProductTagToUpdate() {
        return productTagToUpdate;
    }

    public void setProductTagToUpdate(ProductTag productTagToUpdate) {
        this.productTagToUpdate = productTagToUpdate;
    }

    public ProductTag getProductTagToDelete() {
        return productTagToDelete;
    }

    public void setProductTagToDelete(ProductTag productTagToDelete) {
        this.productTagToDelete = productTagToDelete;
    }

    public ProductTag getProductTagToView() {
        return productTagToView;
    }

    public void setProductTagToView(ProductTag productTagToView) {
        this.productTagToView = productTagToView;
    }

    public ProductTag getNewProductTag() {
        return newProductTag;
    }

    public void setNewProductTag(ProductTag newProductTag) {
        this.newProductTag = newProductTag;
    }
}

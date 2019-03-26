/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.ProductControllerLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import util.exception.ProductNotFoundException;

/**
 *
 * @author Hong Chew
 */
@Named(value = "deleteProductManagedBean")
@RequestScoped
public class DeleteProductManagedBean {

    @EJB
    private ProductControllerLocal productController;
    
    
    
    /**
     * Creates a new instance of DeleteProductManagedBean
     */
    public DeleteProductManagedBean() {
    }
    
    public void deleteProduct(Long pdtId){
        try {
            productController.deleteProduct(pdtId);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Product deleted successfully: " + pdtId, "Product deleted successfully: " + pdtId ));
        } catch (ProductNotFoundException ex) {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error deleting product:" + ex.getMessage(), "Error deleting product:" + ex.getMessage()));
        }
    }
}

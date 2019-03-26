/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.ProductControllerLocal;
import entity.ProductEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Hong Chew
 */
@Named(value = "viewAllProductsManagedBean")
@RequestScoped
public class ViewAllProductsManagedBean {

    @EJB
    private ProductControllerLocal productController;

    private List<ProductEntity> allProducts;
    
    /**
     * Creates a new instance of ViewAllProductsManagedBean
     */
    public ViewAllProductsManagedBean() {
        allProducts = new ArrayList<>();
    }
    
    @PostConstruct
    public void postConstruct(){
        setAllProducts(productController.retrieveAllProducts());
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

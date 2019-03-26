/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedbean;

import ejb.stateless.CategoryControllerLocal;
import ejb.stateless.ProductControllerLocal;
import ejb.stateless.ProductTagControllerLocal;
import entity.Category;
import entity.ProductEntity;
import entity.ProductTag;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Hong Chew
 */
@Named(value = "productManagementManagedBean")
@ViewScoped
public class ProductManagementManagedBean {

    @EJB
    private ProductTagControllerLocal productTagController;

    @EJB
    private CategoryControllerLocal categoryController;

    @EJB
    private ProductControllerLocal productController;

    
    
    
    private List<ProductEntity> productEntities;
    private List<ProductEntity> filteredProductEntities;
    
    private ProductEntity newProductEntity;
    private Long categoryIdNew;
    private List<String> tagIdsStringNew;
    private List<Category> categoryEntities;
    private List<ProductTag> tagEntities;
    
    private ProductEntity selectedProductEntityToView;    
    
    private ProductEntity selectedProductEntityToUpdate;
    private Long categoryIdUpdate;
    private List<String> tagIdsStringUpdate;
    /**
     * Creates a new instance of ProductManagementManagedBean
     */
    public ProductManagementManagedBean() {
        newProductEntity = new ProductEntity();
    }
    
    @PostConstruct
    public void postConstruct()
    {
        productEntities = productController.retrieveAllProducts();
        categoryEntities = categoryController.retrieveAllCategories();
        tagEntities = productTagController.retrieveAllTags();
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



/**
 *
 * @author chengyang
 */
@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    
    @Column(nullable = false, unique = true, length = 32)
    @NotNull
    @Size(max = 32)
    private String categoryName;
    
    @Column(nullable = false, length = 128)
    @NotNull
    @Size(max = 128)
    private String description;

    @OneToMany(mappedBy ="productCategory")
    private List<ProductEntity> productEntities;

    public Category() {
        productEntities = new ArrayList<>();
    }

    public Category(String categoryName, String description) {
        this();
        this.categoryName = categoryName;
        this.description = description;
    }
    
    public void addProduct(ProductEntity product)
    {
        if(product != null)
        {
            if(!this.productEntities.contains(product))
            {
                this.productEntities.add(product);
                
                if(product.getProductCategory() != this)
                {                    
                    product.setProductCategory(this);
                }
            }
        }
    }
    public void deleteProduct(ProductEntity product)
    {
        if(product != null)
        {
            if(this.productEntities.contains(product))
            {
                this.productEntities.remove(product);
                
                if(product.getProductCategory() == this)
                {                    
                    product.setProductCategory(null);
                }
            }
        }
    }
    public void removeProduct(ProductEntity product)
    {
        if(product != null)
        {
            if(this.productEntities.contains(product))
            {
                System.out.println("Entered HEREERERERERE");
                this.productEntities.remove(product);
                
            }
        }
    }
    
    

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    
    public List<ProductEntity> getProductEntities() {
        return productEntities;
    }

  
    public void setProductEntities(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
}

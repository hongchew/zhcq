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

    @OneToMany(mappedBy ="categoryEntity")
    private List<ProductEntity> productEntities;

    public Category() {
        productEntities = new ArrayList<>();
    }

    public Category(String categoryName) {
        this();
        
        this.categoryName = categoryName;
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
    
}

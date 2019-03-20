/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author chengyang
 */
@Entity
public class ProductEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    
    @OneToOne
    private SaleTransactionLineItem saleTransactionLineItem;
    
    @OneToMany(mappedBy="product")
    private List<ProductTag> productTags;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private Category category;
    
    @ManyToOne
    @JoinColumn
    private CoordinatedOutfit coordinatedOutfit;
    
  
    public ProductEntity() {
        productTags = new ArrayList<ProductTag>();
    }
    
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author chengyang
 */
@Entity
public class SaleTransactionLineItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long saleTransactionLineItemId;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    private SaleTransaction saleTransaction;
    
    @OneToOne
    private ProductEntity productEntity;
    
    private Integer quantity;

    public SaleTransactionLineItem() {
    }
    
    public SaleTransactionLineItem(SaleTransaction saleTransaction, ProductEntity productEntity){
        this();
        this.saleTransaction = saleTransaction;
        this.productEntity = productEntity;
    }
   
    public Long getSaleTransactionLineItemId() {
        return saleTransactionLineItemId;
    }

    public void setSaleTransactionLineItemId(Long saleTransactionLineItemId) {
        this.saleTransactionLineItemId = saleTransactionLineItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the saleTransaction
     */
    public SaleTransaction getSaleTransaction() {
        return saleTransaction;
    }

    /**
     * @param saleTransaction the saleTransaction to set
     */
    public void setSaleTransaction(SaleTransaction saleTransaction) {
        this.saleTransaction = saleTransaction;
    }

    /**
     * @return the productEntity
     */
    public ProductEntity getProductEntity() {
        return productEntity;
    }

    /**
     * @param productEntity the productEntity to set
     */
    public void setProductEntity(ProductEntity productEntity) {
        this.productEntity = productEntity;
    }

    
}

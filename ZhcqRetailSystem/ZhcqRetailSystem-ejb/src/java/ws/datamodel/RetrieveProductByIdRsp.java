/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.ProductEntity;

/**
 *
 * @author zhimingkoh
 */
public class RetrieveProductByIdRsp {
    
    private ProductEntity product; 

    public RetrieveProductByIdRsp() {
    }

    public RetrieveProductByIdRsp(ProductEntity product) {
        this.product = product;
    }

    
    public ProductEntity getProduct() {
        return product;
    }

    
    public void setProduct(ProductEntity product) {
        this.product = product;
    }
    
    
    
}

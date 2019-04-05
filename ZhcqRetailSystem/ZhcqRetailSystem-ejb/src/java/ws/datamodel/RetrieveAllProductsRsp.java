/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.ProductEntity;
import java.util.List;

public class RetrieveAllProductsRsp {
    private List<ProductEntity> products;

    public RetrieveAllProductsRsp() {
    }
    

    public RetrieveAllProductsRsp(List<ProductEntity> products) {
        this.products = products;
    }
    
    public List<ProductEntity> getProducts() {
        return products;
    }

   
    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.ProductEntity;
import java.util.List;

/**
 *
 * @author zhimingkoh
 */
public class RetrieveProductByIdRsp {
    
    private ProductEntity selectedProduct; 
    private List<ProductEntity> sameProducts; 
    private List<ProductEntity> recommendedProducts; 
    

    public RetrieveProductByIdRsp() {
    }

    public RetrieveProductByIdRsp(ProductEntity product, List<ProductEntity> sameProducts,List<ProductEntity> recommendedProducts ) {
        this.sameProducts = sameProducts;
        this.selectedProduct = product;
        this.recommendedProducts = recommendedProducts;
    }

    
    public ProductEntity getSelectedProduct() {
        return selectedProduct;
    }

    
    public void setSelectedProduct(ProductEntity selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    
    public List<ProductEntity> getSameProducts() {
        return sameProducts;
    }

    
    public void setSameProducts(List<ProductEntity> sameProducts) {
        this.sameProducts = sameProducts;
    }

    
    public List<ProductEntity> getRecommendedProducts() {
        return recommendedProducts;
    }

    
    public void setRecommendedProducts(List<ProductEntity> recommendedProducts) {
        this.recommendedProducts = recommendedProducts;
    }
    
    
    
}

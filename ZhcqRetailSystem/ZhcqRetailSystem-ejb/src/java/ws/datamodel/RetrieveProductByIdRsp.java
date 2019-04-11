    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.ProductEntity;
import java.util.List;


public class RetrieveProductByIdRsp {
    
    private ProductEntity selectedProduct; 
    private List<ProductEntity> sameProducts; 
    private List<ProductEntity> suggestedProducts; 
    
    

    public RetrieveProductByIdRsp() {
        
    }

    public RetrieveProductByIdRsp(ProductEntity selectedProduct, List<ProductEntity> sameProducts, List<ProductEntity> suggestedProducts) {
        this.selectedProduct = selectedProduct;
        this.sameProducts = sameProducts;
        this.suggestedProducts = suggestedProducts;
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

  
    public List<ProductEntity> getSuggestedProducts() {
        return suggestedProducts;
    }

   
    public void setSuggestedProducts(List<ProductEntity> suggestedProducts) {
        this.suggestedProducts = suggestedProducts;
    }
    
    
    
}

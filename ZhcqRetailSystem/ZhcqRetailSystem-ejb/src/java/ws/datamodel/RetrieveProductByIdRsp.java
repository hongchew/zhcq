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
   
    private List<ProductEntity> diffColours; 
    private List<ProductEntity> diffSizes; 
    private List<ProductEntity> suggestedProducts; 
    
    

    public RetrieveProductByIdRsp() {
        
    }

    public RetrieveProductByIdRsp(ProductEntity selectedProduct, List<ProductEntity> diffColours, List<ProductEntity> diffSizes, List<ProductEntity> suggestedProducts) {
        this.selectedProduct = selectedProduct;
        this.diffColours = diffColours;
        this.diffSizes = diffSizes;
        this.suggestedProducts = suggestedProducts;
    }
    

    
    public ProductEntity getSelectedProduct() {
        return selectedProduct;
    }

    
    public void setSelectedProduct(ProductEntity selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

   
//    public List<ProductEntity> getSameProducts() {
//        return sameProducts;
//    }
//
//    
//    public void setSameProducts(List<ProductEntity> sameProducts) {
//        this.sameProducts = sameProducts;
//    }

  
    public List<ProductEntity> getSuggestedProducts() {
        return suggestedProducts;
    }

   
    public void setSuggestedProducts(List<ProductEntity> suggestedProducts) {
        this.suggestedProducts = suggestedProducts;
    }

    public List<ProductEntity> getDiffColours() {
        return diffColours;
    }

    public void setDiffColours(List<ProductEntity> diffColours) {
        this.diffColours = diffColours;
    }

    public List<ProductEntity> getDiffSizes() {
        return diffSizes;
    }

    public void setDiffSizes(List<ProductEntity> diffSizes) {
        this.diffSizes = diffSizes;
    }
    
    
    
}

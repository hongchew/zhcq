    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.ProductEntity;


public class RetrieveProductByIdRsp {
    
    private ProductEntity selectedProduct; 
    
    

    public RetrieveProductByIdRsp() {
        
    }

    public RetrieveProductByIdRsp(ProductEntity selectedProduct) {
        this.selectedProduct = selectedProduct;
        
    }
   

    
    public ProductEntity getSelectedProduct() {
        return selectedProduct;
    }

    
    public void setSelectedProduct(ProductEntity selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
    
    
    
}

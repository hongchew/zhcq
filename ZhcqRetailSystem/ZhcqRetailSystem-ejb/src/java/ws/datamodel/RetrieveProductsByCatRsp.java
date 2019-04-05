
package ws.datamodel;

import entity.ProductEntity;
import java.util.List;

/**
 *
 * @author zhimingkoh
 */
public class RetrieveProductsByCatRsp {
    private List<ProductEntity> products ; 

    public RetrieveProductsByCatRsp() {
    }

    public RetrieveProductsByCatRsp(List<ProductEntity> products) {
        this.products = products;
    }

   
    public List<ProductEntity> getProducts() {
        return products;
    }

    
    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
    
    
}

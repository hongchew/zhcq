
package ws.datamodel;

import entity.ProductEntity;
import java.util.List;

/**
 *
 * @author zhimingkoh
 */
public class RetrieveProductByCatRsp {
    private List<ProductEntity> products ; 

    public RetrieveProductByCatRsp() {
    }

    public RetrieveProductByCatRsp(List<ProductEntity> products) {
        this.products = products;
    }

   
    public List<ProductEntity> getProducts() {
        return products;
    }

    
    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
    
    
}

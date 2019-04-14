package ws.datamodel;

import entity.ProductEntity;
import java.util.List;

public class RetrieveAllUniqueProductsRsp 
{
    private List<ProductEntity> products;


    public RetrieveAllUniqueProductsRsp() {
    }

    public RetrieveAllUniqueProductsRsp(List<ProductEntity> products) {
        this.products = products;
       
    }
    

    
    public List<ProductEntity> getProducts() {
        return products;
    }

   
    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    
    
}

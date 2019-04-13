package ws.datamodel;

import entity.ProductEntity;
import java.util.List;

public class RetrieveAllProductsRsp 
{
    private List<ProductEntity> products;
    private List<String> imgUrls;

    public RetrieveAllProductsRsp() {
    }

    public RetrieveAllProductsRsp(List<ProductEntity> products, List<String> imgUrls) {
        this.products = products;
        this.imgUrls = imgUrls;
    }
    

    
    public List<ProductEntity> getProducts() {
        return products;
    }

   
    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }
    
}

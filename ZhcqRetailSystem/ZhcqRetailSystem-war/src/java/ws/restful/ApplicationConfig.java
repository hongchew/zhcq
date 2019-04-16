package ws.restful;

import java.util.Set;
import javax.ws.rs.core.Application;


//starting point of the restful resource service
@javax.ws.rs.ApplicationPath("Resources")

public class ApplicationConfig extends Application
{
    @Override
    public Set<Class<?>> getClasses() 
    {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
    
    

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(ws.restful.CategoryResource.class);
        resources.add(ws.restful.CoordinatedOutfitResource.class);
        resources.add(ws.restful.MemberResource.class);
        resources.add(ws.restful.ProductResource.class);
        resources.add(ws.restful.PromotionResource.class);
        resources.add(ws.restful.SaleTransactionResource.class);
        resources.add(ws.restful.ShoppingCartResource.class);
        resources.add(ws.restful.WishListResource.class);
    }
}
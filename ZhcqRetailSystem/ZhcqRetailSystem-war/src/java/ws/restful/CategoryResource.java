
package ws.restful;

import ejb.stateless.CategoryControllerLocal;
import entity.Category;
import entity.ProductEntity;
import entity.ProductTag;
import entity.Promotion;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import ws.datamodel.RetrieveAllCategoriesRsp;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import ws.datamodel.ErrorRsp;
import ws.datamodel.RetrieveCategoyByIdRsp;

@Path("Category")

public class CategoryResource 
{
    @Context
    private UriInfo context;
    
    private CategoryControllerLocal categoryControllerLocal;

    public CategoryResource() {
        categoryControllerLocal = lookupCategoryControllerLocal();
    }
    
    @Path("retrieveAllCategories")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllCategories()
    {
        try
        {
            List<Category> categories = categoryControllerLocal.retrieveAllCategories();
            System.out.println("number of Categories = "+ categories.size());
            if(categories!=null)
            {
                for(Category cat: categories)
                {
                    cat.setProductEntities(null);
                }
            }
            else  
            {
                categories = new ArrayList<>();
            }
            
            RetrieveAllCategoriesRsp retrieveAllCategoriesRsp = new RetrieveAllCategoriesRsp(categories);
            
            System.out.println("retrieveAllCategoryRSP Created");
            for(Category cat: retrieveAllCategoriesRsp.getCategories())
            {
                System.out.println(cat.getCategoryName());
            }
           
            return Response.status(Status.OK).entity(retrieveAllCategoriesRsp).build();
            
        }
        catch(Exception ex)
        {
            System.out.println("Entered Error");
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("retrieveCategoryById/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveCategoryById(@PathParam("id") Long id)
    {
        System.out.println("catId = " + id);
        try{
            Category category = categoryControllerLocal.retrieveCategoryByCategoryId(id);
            
            if(category != null){
                for( ProductEntity product: category.getProductEntities())
                {
                    product.setProductCategory(null);

                    for(ProductTag tag: product.getProductTags())
                    {
                        tag.getProductEntities().clear();
                    }

                    product.getWishLists().clear();

                    product.getShoppingcarts().clear();

                    product.setCoordinatedOutfit(null);
                    
                    Promotion promotion = product.getPromotion();

                    if(promotion !=null){
                        promotion.getPromotionalProducts().clear();  
                    }
                }
            }
            RetrieveCategoyByIdRsp retrieveCategoyByIdRsp = new RetrieveCategoyByIdRsp(category);
            return Response.status(Status.OK).entity(retrieveCategoyByIdRsp).build();
        } catch (Exception ex){
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(errorRsp).build();
        }
        
    }
    
    
    
    
    private CategoryControllerLocal lookupCategoryControllerLocal() 
    {
        try 
        {
            javax.naming.Context c = new InitialContext();
            return (CategoryControllerLocal) c.lookup("java:global/ZhcqRetailSystem/ZhcqRetailSystem-ejb/CategoryController!ejb.stateless.CategoryControllerLocal");
        }
        catch (NamingException ne) 
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}

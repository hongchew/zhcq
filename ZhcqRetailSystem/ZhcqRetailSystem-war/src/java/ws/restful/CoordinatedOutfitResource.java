
package ws.restful;

import ejb.stateless.CoordinatedOutfitControllerLocal;
import ejb.stateless.ProductControllerLocal;
import entity.Category;
import entity.CoordinatedOutfit;
import entity.ProductEntity;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import ws.datamodel.ErrorRsp;
import ws.datamodel.RetrieveAllOutfitsRsp;
import ws.datamodel.RetrieveOutfitByIdRsp;

/**
 *
 * @author zhimingkoh
 */



@Path("CoordinatedOutfit")
public class CoordinatedOutfitResource {

    CoordinatedOutfitControllerLocal coordinatedOutfitController;
    
    @Context
    private UriInfo context;

    public CoordinatedOutfitResource() {
        coordinatedOutfitController = lookupCoordinatedOutfitControllerLocal();
    }
    
    @Path("retrieveAllOutfits")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllOutfits()
    {
        try{
            List<CoordinatedOutfit> outfits = coordinatedOutfitController.retrieveAllOutfits();
            
            if(outfits != null && !outfits.isEmpty()){
                for(CoordinatedOutfit outfit : outfits)
                {
                    List<ProductEntity> products = outfit.getProductEntities();
                    
                    if(products != null && !products.isEmpty())
                    {
                        for(ProductEntity product:products)
                        {
                            Category category = product.getProductCategory();
                            category.getProductEntities().clear();

                            product.getProductTags().clear();

                            product.getWishLists().clear();

                            product.getShoppingcarts().clear();

                            product.setCoordinatedOutfit(null);

                            product.setPromotion(null);
                           
                        }
                        
                    }
                        
                }
            } 
            
            
            RetrieveAllOutfitsRsp retrieveAllOutfitsRsp = new RetrieveAllOutfitsRsp(outfits);
            return Response.status(Status.OK).entity(retrieveAllOutfitsRsp).build();
            
        } catch (Exception ex){
            
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
        
    }
    
    @Path("retrieveOutfitById/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveOutfitById(@PathParam("id") Long id)
    {
        try{
            CoordinatedOutfit outfit = coordinatedOutfitController.retrieveOutfitById(id);
            
            List<ProductEntity> products = outfit.getProductEntities();
            if(products != null && !products.isEmpty())
                {
                    for(ProductEntity product:products)
                    {
                        Category category = product.getProductCategory();
                        category.getProductEntities().clear();

                        product.getProductTags().clear();

                        product.getWishLists().clear();

                        product.getShoppingcarts().clear();

                        product.setCoordinatedOutfit(null);

                        product.setPromotion(null);

                    }

                }
            
            
            RetrieveOutfitByIdRsp retrieveOutfitByIdRsp = new RetrieveOutfitByIdRsp(outfit);
            return Response.status(Status.OK).entity(retrieveOutfitByIdRsp).build();
            
        } catch (Exception ex){
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }
    
    

    private CoordinatedOutfitControllerLocal lookupCoordinatedOutfitControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CoordinatedOutfitControllerLocal) c.lookup("java:global/ZhcqRetailSystem/ZhcqRetailSystem-ejb/CoordinatedOutfitController!ejb.stateless.CoordinatedOutfitControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}

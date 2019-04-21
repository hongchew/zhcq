/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful;

import ejb.stateless.PromotionControllerLocal;
import entity.Category;
import entity.CoordinatedOutfit;
import entity.ProductEntity;
import entity.ProductTag;
import entity.Promotion;
import entity.ShoppingCart;
import entity.WishList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import util.exception.PromotionNotFoundException;
import ws.datamodel.ErrorRsp;
import ws.datamodel.RetrieveAllPromotionRsp;
import ws.datamodel.RetrievePromotionByIdRsp;

/**
 *
 * @author Hong Chew
 */
@Path("Promotion")
public class PromotionResource {
    
    @Context
    private UriInfo context;
    
    private PromotionControllerLocal promotionController;

    public PromotionResource() {
        promotionController = lookupPromotionControllerLocal();
    }
    
    

    @Path("retrieveAllPromotions")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllPromotions(){
        System.out.println("Entered Into Retrieve All Promotions Controller");
        
        try{
            List<Promotion> promotions = promotionController.retrieveAllPromotions();
            
            System.out.println("got list of promotions");
            
            for(Promotion promo : promotions){
                System.out.println("Promo: " + promo.getPromotionName());
                
                List<ProductEntity> products = promo.getPromotionalProducts();
                
                if(products != null && !products.isEmpty())
                {
                    for(ProductEntity product : products){
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
        
        
            RetrieveAllPromotionRsp rsp = new RetrieveAllPromotionRsp(promotions);
            
            System.out.println("Retrieve All Promotion Rsp created !");
            return Response.status(Status.OK).entity(rsp).header("Access-Control-Allow-Origin", "*").build();
            
        } catch (Exception ex){
            
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).header("Access-Control-Allow-Origin", "*").build();
        }
        
        
        
    }
    
    @Path("retrievePromotionById/{promotionId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrievePromotionById(@PathParam("promotionId") Long promotionId ){
        
        try {
            Promotion promo = promotionController.retrievePromotionByPromotionId(promotionId);
            
            for(ProductEntity product : promo.getPromotionalProducts()){
                Category category = product.getProductCategory();
                category.getProductEntities().clear();

                product.getProductTags().clear();

                product.getWishLists().clear();

                product.getShoppingcarts().clear();

                product.setCoordinatedOutfit(null);

                product.setPromotion(null);
            }
            
            RetrievePromotionByIdRsp rsp = new RetrievePromotionByIdRsp(promo) ;
            return Response.status(Response.Status.OK).entity(rsp).header("Access-Control-Allow-Origin", "*").build();
            
        } catch (PromotionNotFoundException ex) {
            return Response.status(Response.Status.BAD_REQUEST).header("Access-Control-Allow-Origin", "*").build();
        }
        
    }
    
    private PromotionControllerLocal lookupPromotionControllerLocal() {
        try 
        {
            javax.naming.Context c = new InitialContext();
            return (PromotionControllerLocal) c.lookup("java:global/ZhcqRetailSystem/ZhcqRetailSystem-ejb/PromotionController!ejb.stateless.PromotionControllerLocal");
        }
        catch (NamingException ne) 
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
}

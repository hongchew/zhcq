/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful;

import ejb.stateless.PromotionControllerLocal;
import entity.ProductEntity;
import entity.ProductTag;
import entity.Promotion;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import util.exception.PromotionNotFoundException;
import ws.datamodel.RetrieveAllPromotionRsp;
import ws.datamodel.RetrievePromotionByIdRsp;

/**
 *
 * @author Hong Chew
 */
@Path("Promotion")
public class PromotionResource {

    PromotionControllerLocal promotionController = lookupPromotionControllerLocal();

    @Path("retrieveAllPromotions")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllPromotions(){
        
        List<Promotion> promotions = promotionController.retrieveAllPromotions();
        
        for(Promotion promo : promotions){
            for(ProductEntity pdt : promo.getPromotionalProducts()){
                pdt.setShoppingcarts(null);
                pdt.setWishLists(null);
                pdt.setPromotion(null);
                pdt.getProductCategory().setProductEntities(null);
                for(ProductTag tag : pdt.getProductTags()){
                    tag.setProductEntities(null);
                }
            }
        }
        
        
        RetrieveAllPromotionRsp rsp = new RetrieveAllPromotionRsp(promotions);
        
        return Response.status(Response.Status.OK).entity(rsp).build();
        
    }
    
    @Path("retrievePromotionById")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrievePromotionById(@QueryParam("promotionId") Long promotionId ){
        
        try {
            Promotion promo = promotionController.retrievePromotionByPromotionId(promotionId);
            
            for(ProductEntity pdt : promo.getPromotionalProducts()){
                pdt.setShoppingcarts(null);
                pdt.setWishLists(null);
                pdt.setPromotion(null);
                pdt.getProductCategory().setProductEntities(null);
                for(ProductTag tag : pdt.getProductTags()){
                    tag.setProductEntities(null);
                }
            }
            
            RetrievePromotionByIdRsp rsp = new RetrievePromotionByIdRsp(promo) ;
            return Response.status(Response.Status.OK).entity(rsp).build();
            
        } catch (PromotionNotFoundException ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        
    }
    
    private PromotionControllerLocal lookupPromotionControllerLocal() {
        try {
            Context c = new InitialContext();
            return (PromotionControllerLocal) c.lookup("java:global/ZhcqRetailSystem/ZhcqRetailSystem-ejb/PromotionController!ejb.stateless.PromotionControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
}

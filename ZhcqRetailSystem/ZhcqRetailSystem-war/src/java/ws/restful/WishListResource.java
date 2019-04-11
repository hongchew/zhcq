/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful;

import ejb.stateless.WishListControllerLocal;
import entity.Category;
import entity.CoordinatedOutfit;
import entity.ProductEntity;
import entity.ProductTag;
import entity.Promotion;
import entity.ShoppingCart;
import entity.WishList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import util.exception.MemberNotFoundException;
import util.exception.ProductNotFoundException;
import util.exception.WishListNotFoundException;
import ws.datamodel.ErrorRsp;
import ws.datamodel.RetrieveWishListRsp;




/**
 *
 * @author Hong Chew
 */

@Path("Wishlist")
public class WishListResource {

    WishListControllerLocal wishListController = lookupWishListControllerLocal();

    public WishListResource() {
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveWishList(@QueryParam("userId") Long userId){
        try{
            WishList wishList = wishListController.retrieveWishListByMemberId(userId);
            wishList.getMember().setWishList(null);
            wishList.getMember().setShoppingCart(null);
            wishList.getMember().setPassword(null);
            wishList.getMember().setSalt(null);
            wishList.getMember().setSaleTransactions(null);
            
            for(ProductEntity product : wishList.getProductEntities()){
                Category category = product.getProductCategory();
                    category.getProductEntities().clear();
                    
                    for(ProductTag tag: product.getProductTags())
                    {
                        tag.getProductEntities().clear();
                    }           
                   
                    for(WishList wishlist: product.getWishLists())
                    {
                        wishlist.getProductEntities().clear();
                    }     
                    
                    for(ShoppingCart cart: product.getShoppingcarts())
                    {
                        cart.getProducts().clear();
                    }
                    
                    
                    CoordinatedOutfit outfit = product.getCoordinatedOutfit();
                    if(outfit !=null){
                        outfit.getProductEntities().clear();
                    }
                 
                    
                    Promotion promotion = product.getPromotion();
                    
                    if(promotion !=null){
                        promotion.getPromotionalProducts().clear();  
                    }
                        
            }
            
            RetrieveWishListRsp rsp = new RetrieveWishListRsp(wishList);
            return Response.status(Response.Status.OK).entity(rsp).build();

            
        } catch (WishListNotFoundException | MemberNotFoundException ex) {
            System.err.println("***Error: " + ex.getMessage() );
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        } catch (Exception ex){
            System.err.println("***Error: " + ex.getMessage() );
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }       
    }
    
    @POST
    public Response addToWishlist(@QueryParam("userId") Long userId, @QueryParam("productId") Long productId){
        try{
            wishListController.addProductToWishlist(userId, productId);            
            return Response.status(Response.Status.OK).build();
            
        } catch (MemberNotFoundException | ProductNotFoundException ex) {
            System.err.println("***Error: " + ex.getMessage() );
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }catch (Exception ex){
            System.err.println("***Error: " + ex.getMessage() );
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }  
    } 
    
    @DELETE
    public Response deleteFromWishlist(@QueryParam("userId") Long userId, @QueryParam("productId") Long ProductId){
        try{
            wishListController.removeProductFromWishlist(userId, ProductId);
            return Response.status(Response.Status.OK).build();
        } catch (MemberNotFoundException | ProductNotFoundException ex) {
            System.err.println("***Error: " + ex.getMessage() );
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }catch (Exception ex){
            System.err.println("***Error: " + ex.getMessage() );
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }  
    }
    
    private WishListControllerLocal lookupWishListControllerLocal() {
        try {
            Context c = new InitialContext();
            return (WishListControllerLocal) c.lookup("java:global/ZhcqRetailSystem/ZhcqRetailSystem-ejb/WishListController!ejb.stateless.WishListControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }


}

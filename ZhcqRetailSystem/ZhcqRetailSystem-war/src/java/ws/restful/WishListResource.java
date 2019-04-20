/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful;

import com.sun.xml.ws.security.opt.impl.util.SOAPUtil;
import ejb.stateless.WishListControllerLocal;
import entity.Category;
import entity.CoordinatedOutfit;
import entity.ProductEntity;
import entity.ProductTag;
import entity.Promotion;
import entity.ShoppingCart;
import entity.WishList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
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

    @Path("retrieveWishList")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveWishList(@QueryParam("userId") Long userId){
        try{
            WishList wishList = wishListController.retrieveWishListByMemberId(userId);
            System.out.println("***WISHLIST:\n" + wishList.toString() + "\nEND WISHLIST ***");
            wishList.getMember().setWishList(null);
            wishList.getMember().setShoppingCart(null);
            wishList.getMember().setPassword(null);
            wishList.getMember().setSalt(null);
            wishList.getMember().setSaleTransactions(null);
            System.out.println("CHECK 1");
            
            for(ProductEntity product : wishList.getProductEntities()){
                
                Category category = product.getProductCategory();
                System.out.println("CHECK 1.1");
                
                category.getProductEntities().clear();
                System.out.println("CHECK 1.2 ");
                
//                for(ProductTag tag: product.getProductTags())
//                {
//                    tag.getProductEntities().clear();
//                }           

                product.getProductTags().clear();
                System.out.println("CHECK 1.3");
                
                
                product.getWishLists().clear();
                System.out.println("CHECK 1.4");
                
//                for(ShoppingCart cart: product.getShoppingcarts())
//                {
//                    cart.getProducts().clear();
//                }
                product.getShoppingcarts().clear();
                System.out.println("CHECK 1.5");

//                CoordinatedOutfit outfit = product.getCoordinatedOutfit();
//                if(outfit !=null){
//                    outfit.getProductEntities().clear();
//                }
                
                product.setCoordinatedOutfit(null);
                System.out.println("CHECK 1.6");

                Promotion promotion = product.getPromotion();
                System.out.println("CHECK 1.7");
                
                if(promotion !=null){
                    promotion.getPromotionalProducts().clear();  
                }
                System.out.println("CHECK 1.8");
            }
            
            System.out.println("CHECK 2");
            
            RetrieveWishListRsp rsp = new RetrieveWishListRsp(wishList);
            System.out.println("CHECK 3");
            return Response.status(Response.Status.OK).entity(rsp).header("Access-Control-Allow-Origin", "*").build();

            
        } catch (WishListNotFoundException | MemberNotFoundException ex) {
            System.err.println("***Error: " + ex.getMessage() );
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).header("Access-Control-Allow-Origin", "*").build();
        } catch (Exception ex){
            System.err.println("***Error: " + ex.getMessage() );
            ex.printStackTrace();
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).header("Access-Control-Allow-Origin", "*").build();
        }       
    }
    
    @Path("addToWishlist")
    @POST
    public Response addToWishlist(@QueryParam("userId") Long userId, @QueryParam("productId") Long productId){
        try{
            System.out.println("Entered add to wishlist method");
            wishListController.addProductToWishlist(userId, productId);            
            System.out.println("Product added to wishlist");
            return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").build();
            
        } catch (MemberNotFoundException | ProductNotFoundException ex) {
            System.err.println("***Error: " + ex.getMessage() );
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).header("Access-Control-Allow-Origin", "*").build();
        }catch (Exception ex){
            System.err.println("***Error: " + ex.getMessage() );
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).header("Access-Control-Allow-Origin", "*").build();
        }  
    } 
    
    @DELETE
    public Response deleteFromWishlist(@QueryParam("userId") Long userId, @QueryParam("productId") Long ProductId){
        try{
            wishListController.removeProductFromWishlist(userId, ProductId);
            return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").build();
        } catch (MemberNotFoundException | ProductNotFoundException ex) {
            System.err.println("***Error: " + ex.getMessage() );
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).header("Access-Control-Allow-Origin", "*").build();
        }catch (Exception ex){
            System.err.println("***Error: " + ex.getMessage() );
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).header("Access-Control-Allow-Origin", "*").build();
        }  
    }
    
    @OPTIONS
    public Response nonSimpleRequests(){
        return Response.status(Response.Status.OK).header("Access-Control-Allow-Origin", "*").header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, DELETE, OPTIONS").header("Access-Control-Allow-Headers", "Content-Type").build();
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

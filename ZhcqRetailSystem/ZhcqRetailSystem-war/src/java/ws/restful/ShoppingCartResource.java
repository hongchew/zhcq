/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful;

import ejb.stateless.CheckoutControllerLocal;
import entity.CoordinatedOutfit;
import entity.ProductEntity;
import entity.ProductTag;
import entity.Promotion;
import entity.SaleTransaction;
import entity.SaleTransactionLineItem;
import entity.ShoppingCart;
import entity.WishList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import util.exception.OutOfStockException;
import util.exception.ProductNotFoundException;
import util.exception.ShoppingCartNotFoundException;
import ws.datamodel.CheckoutRsp;
import ws.datamodel.ErrorRsp;
import ws.datamodel.RetrieveShoppingCartRsp;

/**
 *
 * @author Hong Chew
 */
@Path("ShoppingCart")
public class ShoppingCartResource {

    CheckoutControllerLocal checkoutController = lookupCheckoutControllerLocal();
    
    
    
    @Context
    private UriInfo context;

    public ShoppingCartResource() {
 
    }  

    private CheckoutControllerLocal lookupCheckoutControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (CheckoutControllerLocal) c.lookup("java:global/ZhcqRetailSystem/ZhcqRetailSystem-ejb/CheckoutController!ejb.stateless.CheckoutControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    @Path("retrieveShoppingCart")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveShoppingCart(@QueryParam("userId") Long id){
        try {
            ShoppingCart cart = checkoutController.retrieveShoppingCartById(id);
            
            for(ProductEntity product : cart.getProducts()){
                
                product.getProductCategory().setProductEntities(null);
                for(ProductTag tag: product.getProductTags())
                    {
                        tag.getProductEntities().clear();
                    }           
                    
                    for(WishList wishlist: product.getWishLists())
                    {
                        wishlist.getProductEntities().clear();
                    }     
                    
                    for(ShoppingCart scart: product.getShoppingcarts())
                    {
                        scart.getProducts().clear();
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
            
            RetrieveShoppingCartRsp cartRsp = new RetrieveShoppingCartRsp(cart);
            return Response.status(Response.Status.OK).entity(cartRsp).build();
            
        } catch (ShoppingCartNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }
    
    @Path("addToCart")
    @POST
    public Response addToCart(@QueryParam("cartId") Long cartId, @QueryParam("productId") Long pdtId){
        
        try {
            checkoutController.updateCart(cartId, pdtId, true);
            return Response.status(Response.Status.OK).build();
            
        } catch (OutOfStockException | ShoppingCartNotFoundException | ProductNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
        
    }
    
    @DELETE
    public Response removeFromCart(@QueryParam("cartId") Long cartId, @QueryParam("pdtId") Long pdtId){
        
        try {
            checkoutController.updateCart(cartId, pdtId, false);
            return Response.status(Response.Status.OK).build();
            
        } catch (OutOfStockException | ShoppingCartNotFoundException | ProductNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }
    
    @Path("checkout")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkout(@QueryParam("cartid") Long cartId){
        try {
            SaleTransaction txn = checkoutController.checkOut(cartId);
            for(SaleTransactionLineItem lineItem : txn.getSaleTransactionLineItems()){
                lineItem.setSaleTransaction(null);
                lineItem.getProductEntity().getProductCategory().setProductEntities(null);
                for(ProductTag tag: lineItem.getProductEntity().getProductTags())
                {
                    tag.getProductEntities().clear();
                }           

                for(WishList wishlist: lineItem.getProductEntity().getWishLists())
                {
                    wishlist.getProductEntities().clear();
                }     

                for(ShoppingCart scart: lineItem.getProductEntity().getShoppingcarts())
                {
                    scart.getProducts().clear();
                }


                CoordinatedOutfit outfit = lineItem.getProductEntity().getCoordinatedOutfit();
                if(outfit !=null){
                    outfit.getProductEntities().clear();
                }

                if(lineItem.getProductEntity().getPromotion() !=null){
                    lineItem.getProductEntity().getPromotion().getPromotionalProducts().clear();  
                }
            }
            
            CheckoutRsp checkoutRsp = new CheckoutRsp(txn);
            return Response.status(Response.Status.OK).entity(checkoutRsp).build();

            
        } catch (ShoppingCartNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
        
        
    }
}

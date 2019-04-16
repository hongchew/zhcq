/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful;

import ejb.stateless.CheckoutControllerLocal;
import entity.Category;
import entity.ProductEntity;
import entity.ProductTag;
import entity.Promotion;
import entity.SaleTransaction;
import entity.SaleTransactionLineItem;
import entity.ShoppingCart;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
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
    public Response retrieveShoppingCart(@QueryParam("userId") Long id) {
        try {
            ShoppingCart cart = checkoutController.retrieveShoppingCartByUserId(id);

            cart.getMember().setShoppingCart(null);
            cart.getMember().setSaleTransactions(null);
            cart.getMember().setWishList(null);
            cart.getMember().setPassword(null);
            cart.getMember().setSalt(null);

            for (ProductEntity product : cart.getProducts()) {

                Category category = product.getProductCategory();
                category.getProductEntities().clear();

                product.getProductTags().clear();

                product.getWishLists().clear();

                product.getShoppingcarts().clear();

                product.setCoordinatedOutfit(null);

                Promotion promotion = product.getPromotion();

                if (promotion != null) {
                    promotion.getPromotionalProducts().clear();
                }

            }

            RetrieveShoppingCartRsp cartRsp = new RetrieveShoppingCartRsp(cart);
            return Response.status(Response.Status.OK).entity(cartRsp).build();

        } catch (ShoppingCartNotFoundException ex) {
            System.err.println(ex.getMessage());
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    @Path("addToCart")
    @POST
    public Response addToCart(@QueryParam("cartId") Long cartId, @QueryParam("productId") Long pdtId, @QueryParam("quantity") Integer quantity) {

        try {
            checkoutController.addToCart(cartId, pdtId, quantity);
            return Response.status(Response.Status.OK).build();
        } catch (ShoppingCartNotFoundException | ProductNotFoundException ex) {
            System.err.println("***Error: " + ex.getMessage());
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        } catch (Exception ex) {
            System.err.println("***Error: " + ex.getMessage());
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }

    }

    @DELETE
    public Response removeFromCart(@QueryParam("cartId") Long cartId, @QueryParam("productId") Long pdtId) {

        try {
            checkoutController.removeFromCart(cartId, pdtId);
            return Response.status(Response.Status.OK).build();
        } catch (ShoppingCartNotFoundException | ProductNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        } catch (Exception ex) {
            System.err.println("***Error: " + ex.getMessage());
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    @Path("updateCart")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCart(@QueryParam("cartId") Long cartId, @QueryParam("productId") Long productId, @QueryParam("quantity") Integer quantity) {

        try {
            checkoutController.updateCart(cartId, productId, quantity);
            return Response.status(Response.Status.OK).build();
        } catch (ShoppingCartNotFoundException | ProductNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        } catch (Exception ex) {
            System.err.println("***Error: " + ex.getMessage());
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    @Path("checkout")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkout(@QueryParam("cartId") Long cartId) {
        try {
            SaleTransaction txn = checkoutController.checkOut(cartId);
//            for (SaleTransactionLineItem lineItem : txn.getSaleTransactionLineItems()) {
//                lineItem.setSaleTransaction(null);
//                lineItem.getProductEntity().getProductCategory().setProductEntities(null);
//
//                lineItem.getProductEntity().setCoordinatedOutfit(null);
//                lineItem.getProductEntity().setShoppingcarts(null);
//                lineItem.getProductEntity().setWishLists(null);
//                lineItem.getProductEntity().setPromotion(null);
//                lineItem.getPromotionApplied().setPromotionalProducts(null);
//                for (ProductTag tag : lineItem.getProductEntity().getProductTags()) {
//                    tag.getProductEntities().clear();
//                }
//            }
            txn.getSaleTransactionLineItems().clear();
            txn.getMember().setWishList(null);
            txn.getMember().setShoppingCart(null);
            txn.getMember().setSaleTransactions(null);
            txn.getMember().setPassword(null);
            txn.getMember().setSalt(null);

            CheckoutRsp checkoutRsp = new CheckoutRsp(txn);

            return Response.status(Response.Status.OK).entity(checkoutRsp).build();

        } catch (ShoppingCartNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        } catch (Exception ex) {
            System.err.println("***Error: " + ex.getMessage());
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }

    }
    
    @Path("checkoutWithPoints")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response checkoutWithPoints(@QueryParam("cartId") Long cartId) {
        try {
            SaleTransaction txn = checkoutController.checkOutWithPoints(cartId);
            for (SaleTransactionLineItem lineItem : txn.getSaleTransactionLineItems()) {
                lineItem.setSaleTransaction(null);
                lineItem.getProductEntity().getProductCategory().setProductEntities(null);

                lineItem.getProductEntity().setCoordinatedOutfit(null);
                lineItem.getProductEntity().setShoppingcarts(null);
                lineItem.getProductEntity().setWishLists(null);
                lineItem.getProductEntity().setPromotion(null);
                lineItem.getPromotionApplied().setPromotionalProducts(null);
                for (ProductTag tag : lineItem.getProductEntity().getProductTags()) {
                    tag.getProductEntities().clear();
                }
            }
            txn.getMember().setWishList(null);
            txn.getMember().setShoppingCart(null);
            txn.getMember().setSaleTransactions(null);
            txn.getMember().setPassword(null);
            txn.getMember().setSalt(null);

            CheckoutRsp checkoutRsp = new CheckoutRsp(txn);

            return Response.status(Response.Status.OK).entity(checkoutRsp).build();

        } catch (ShoppingCartNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        } catch (Exception ex) {
            System.err.println("***Error: " + ex.getMessage());
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }

    }
}

package ws.restful;

import ejb.stateless.ProductControllerLocal;
import entity.Category;
import entity.CoordinatedOutfit;
import entity.ProductEntity;
import entity.ProductTag;
import entity.Promotion;
import entity.ShoppingCart;
import entity.WishList;
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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import util.exception.CategoryNotFoundException;
import util.exception.ProductNotFoundException;
import ws.datamodel.ErrorRsp;
import ws.datamodel.RetrieveAllProductsRsp;
import ws.datamodel.RetrieveProductsByCatRsp;
import ws.datamodel.RetrieveProductByIdRsp;


@Path("Product")

public class ProductResource {
    @Context
    private UriInfo context;
    
    private ProductControllerLocal productControllerLocal;
    

    public ProductResource()
    {
        productControllerLocal = lookupProductControllerLocal();
    }
    
    @Path("retrieveAllProducts")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllProducts()
    {
        try
        { 
            List<ProductEntity> allProducts = productControllerLocal.retrieveAllProducts();
            //System.out.println("REACHED 1 Length of Array = " + allProducts.size());
            
            if(allProducts != null){
                for(ProductEntity product:allProducts)
                {
                   
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
            }
             
           
            RetrieveAllProductsRsp retrieveAllProductsRsp  = new RetrieveAllProductsRsp(allProducts);
        
            return Response.status(Status.OK).entity(retrieveAllProductsRsp).build();
        }
        catch(Exception ex)
        {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
        
    }
    
    @Path("retrieveProductById/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveProductById(@PathParam("id") Long id)
    {
        try{
            ProductEntity product = productControllerLocal.retrieveProductById(id);
            System.out.println(product.getProductName() + " retrieved");
            
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
            
            List<ProductEntity> diffColours = productControllerLocal.retrieveDiffColours(id);
            
            for(ProductEntity pdt : diffColours)
            {
                category = pdt.getProductCategory();
                category.getProductEntities().clear();
            
                for(ProductTag tag: pdt.getProductTags())
                {
                    tag.getProductEntities().clear();
                }
                for(WishList wishlist: pdt.getWishLists())
                {
                    wishlist.getProductEntities().clear();
                }     

                for(ShoppingCart cart: pdt.getShoppingcarts())
                {
                    cart.getProducts().clear();
                }
                outfit = pdt.getCoordinatedOutfit();
                if(outfit !=null){
                outfit.getProductEntities().clear();
                }
            
                promotion = pdt.getPromotion();
                
                if(promotion !=null){
                    promotion.getPromotionalProducts().clear();  
                }
            }
            
            List<ProductEntity> diffSizes = productControllerLocal.retrieveDiffSizes(id);
            
            for(ProductEntity suggestion : diffSizes)
            {
                category = suggestion.getProductCategory();
                category.getProductEntities().clear();
            
                for(ProductTag tag: suggestion.getProductTags())
                {
                    tag.getProductEntities().clear();
                }
                for(WishList wishlist: suggestion.getWishLists())
                {
                    wishlist.getProductEntities().clear();
                }     

                for(ShoppingCart cart: suggestion.getShoppingcarts())
                {
                    cart.getProducts().clear();
                }
                outfit = suggestion.getCoordinatedOutfit();
                if(outfit !=null){
                outfit.getProductEntities().clear();
                }
            
                promotion = suggestion.getPromotion();
                
                if(promotion !=null){
                    promotion.getPromotionalProducts().clear();  
                }
            }
            
            List<ProductEntity> productSuggestions = productControllerLocal.retrieveProductSuggestions(id);
            
            for(ProductEntity suggestion : productSuggestions)
            {
                category = suggestion.getProductCategory();
                category.getProductEntities().clear();
            
                for(ProductTag tag: suggestion.getProductTags())
                {
                    tag.getProductEntities().clear();
                }
                for(WishList wishlist: suggestion.getWishLists())
                {
                    wishlist.getProductEntities().clear();
                }     

                for(ShoppingCart cart: suggestion.getShoppingcarts())
                {
                    cart.getProducts().clear();
                }
                outfit = suggestion.getCoordinatedOutfit();
                if(outfit !=null){
                outfit.getProductEntities().clear();
                }
            
                promotion = suggestion.getPromotion();
                
                if(promotion !=null){
                    promotion.getPromotionalProducts().clear();  
                }
            }
            
            
            RetrieveProductByIdRsp retrieveProductByIdRsp = new RetrieveProductByIdRsp(product, diffColours, diffSizes, productSuggestions); 
            System.out.println("New Object created!");
            
            return Response.status(Status.OK).entity(retrieveProductByIdRsp).build();
            
        } catch (ProductNotFoundException ex){
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }
    
    @Path("retrieveProductByCat/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveProductByCat(@PathParam("id") Long id)
    {
        try{
           
            List<ProductEntity> products = productControllerLocal.filterProductsByCategory(id);
            
            List<ProductEntity> catProducts = new ArrayList<>();
            
            //to prevent ConcurrentModificationException !!! 
            if(products != null){
                for(ProductEntity product:products)
                {
                    catProducts.add(product);
                }
            }
            
            if(catProducts != null){
                for(ProductEntity product:catProducts)
                {
                    Category category = product.getProductCategory();
                    category.removeProduct(product);
                   
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
            }
            
            RetrieveProductsByCatRsp retrieveProductByCatRsp = new RetrieveProductsByCatRsp(catProducts);
            
            return Response.status(Status.OK).entity(retrieveProductByCatRsp).build();
            
        } catch (CategoryNotFoundException ex){
            System.out.println("ERROR CAUGHT !!!!!!");
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    
    private ProductControllerLocal lookupProductControllerLocal()
    {
        
        System.out.println("ENTERED INTO LOOK UP SESSION BEAN METHOD");
        try
        {
            javax.naming.Context c = new InitialContext();
            return (ProductControllerLocal) c.lookup("java:global/ZhcqRetailSystem/ZhcqRetailSystem-ejb/ProductController!ejb.stateless.ProductControllerLocal");
        } 
        catch (NamingException ne) 
        {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
}

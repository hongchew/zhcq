/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful;

import ejb.stateless.CategoryController;
import ejb.stateless.CategoryControllerLocal;
import ejb.stateless.ProductControllerLocal;
import entity.Category;
import entity.ProductEntity;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import util.exception.CategoryNotFoundException;
import util.exception.ProductNotFoundException;
import ws.datamodel.ErrorRsp;
import ws.datamodel.RetrieveAllProductsRsp;
import ws.datamodel.RetrieveProductByCatRsp;
import ws.datamodel.RetrieveProductByIdRsp;


@Path("Product")
public class ProductResource {
    @Context
    private UriInfo context;
    
    private ProductControllerLocal productControllerLocal;
    private CategoryControllerLocal categoryControllerLocal;

    public ProductResource(ProductControllerLocal productControllerLocal) {
        this.productControllerLocal = productControllerLocal;
    }
    
    @Path("retrieveAllProducts")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveAllBooks()
    {
        try
        { 
            List<ProductEntity> allProducts = productControllerLocal.retrieveAllProducts();
        
            RetrieveAllProductsRsp retrieveAllProductsRsp  = new RetrieveAllProductsRsp(allProducts);
        
            return Response.status(Response.Status.OK).entity(retrieveAllProductsRsp).build();
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
    public Response retrieveBookById(@PathParam("id") Long id)
    {
        try{
            ProductEntity product = productControllerLocal.retrieveProductById(id);
            RetrieveProductByIdRsp retrieveProductByIdRsp = new RetrieveProductByIdRsp(); 
            
            return Response.status(Response.Status.OK).entity(retrieveProductByIdRsp).build();
            
        } catch (ProductNotFoundException ex){
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    @Path("retrieveProductByCat/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveBookByCat(@PathParam("id") Long id)
    {
        try{
           
            List<ProductEntity> products = productControllerLocal.filterProductsByCategory(id);
            RetrieveProductByCatRsp retrieveProductByCatRsp = new RetrieveProductByCatRsp(products);
            
            return Response.status(Response.Status.OK).entity(retrieveProductByCatRsp).build();
            
        } catch (CategoryNotFoundException ex){
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }
    
    
    
    
}

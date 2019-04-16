/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful;

import ejb.stateless.SalesTransactionSessionBeanLocal;
import entity.SaleTransaction;
import entity.SaleTransactionLineItem;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import ws.datamodel.RetrieveSalesTransactionByUserIdRsp;

/**
 *
 * @author Hong Chew
 */
@Path("SaleTransaction")
public class SaleTransactionResource {

    SalesTransactionSessionBeanLocal salesTransactionSessionBean = lookupSalesTransactionSessionBeanLocal();
    
    
    @GET
    public Response retrieveSalesTransactionByUserId(@QueryParam("userId") Long userId){
        List<SaleTransaction> txns = salesTransactionSessionBean.retrieveAllSalesTransactionByUserId(userId);
        if(txns.isEmpty()){
            System.out.println("NO TRANSACTIONS");
        }
        for(SaleTransaction txn : txns){
            System.out.println("Sale Transaction Testing");
            System.out.println("ONE");
            System.out.println(txn.getSaleTransactionId());
            txn.setMember(null);
            
//            txn.getMember().getSaleTransactions().clear();
      
            for(SaleTransactionLineItem line : txn.getSaleTransactionLineItems()){
                System.out.println("TWO");
                line.getProductEntity().setWishLists(null);
                line.getProductEntity().setShoppingcarts(null);
                line.getProductEntity().setProductCategory(null);
                line.getProductEntity().setPromotion(null);
                line.getProductEntity().setCoordinatedOutfit(null);
                line.getProductEntity().setProductTags(null);
//                line.getPromotionApplied().setPromotionalProducts(null); 
//                CMI cus promotion applied is null and u cant (null).setpromotionalproducts

                line.setPromotionApplied(null);
                line.setSaleTransaction(null);
                System.out.println("THREE");
            }
        }
        RetrieveSalesTransactionByUserIdRsp rsp = new RetrieveSalesTransactionByUserIdRsp(txns);
        return Response.status(Response.Status.OK).entity(rsp).build();
        
    }
    
    
    private SalesTransactionSessionBeanLocal lookupSalesTransactionSessionBeanLocal() {
        try {
            Context c = new InitialContext();
            return (SalesTransactionSessionBeanLocal) c.lookup("java:global/ZhcqRetailSystem/ZhcqRetailSystem-ejb/SalesTransactionSessionBean!ejb.stateless.SalesTransactionSessionBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful;

import ejb.stateless.MemberControllerLocal;
import entity.Member;
import entity.SaleTransaction;
import entity.ShoppingCart;
import entity.WishList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import util.exception.DeleteMemberException;
import util.exception.InputDataValidationException;
import util.exception.InvalidLoginCredentialException;
import util.exception.MemberNotFoundException;
import util.exception.UpdateMemberException;
import ws.datamodel.CreateMemberReq;
import ws.datamodel.CreateMemberRsp;
import ws.datamodel.ErrorRsp;
import ws.datamodel.LoginReq;
import ws.datamodel.LoginRsp;
import ws.datamodel.RetrieveAllMembersRsp;
import ws.datamodel.RetrieveMemberRsp;
import ws.datamodel.UpdateMemberReq;

@Path("Member")
public class MemberResource {

    private MemberControllerLocal memberControllerLocal;

    @Context
    private UriInfo context;

    public MemberResource() {
        memberControllerLocal = lookupMemberControllerLocal();
    }

    private MemberControllerLocal lookupMemberControllerLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (MemberControllerLocal) c.lookup("java:global/ZhcqRetailSystem/ZhcqRetailSystem-ejb/MemberController!ejb.stateless.MemberControllerLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    @Path("retrieveAllMembers")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response retriveAllMembers() {
        try {
            List<Member> list = memberControllerLocal.retrieveAllMembers();
            for (Member member : list) {
                member.getWishList().setMember(null);
                member.getShoppingCart().setMember(null);
                List<SaleTransaction> listST = member.getSaleTransactions();
                for (SaleTransaction saleTransaction : listST) {
                    saleTransaction.setMember(null);
                }
            }
            RetrieveAllMembersRsp retrieveAllMembersRsp = new RetrieveAllMembersRsp(list);
            return Response.status(Response.Status.OK).entity(retrieveAllMembersRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@QueryParam("username") String username, @QueryParam("password") String password) {
        System.out.println("CHECK0");
        if (username != null && password != null) {
            try {
                Member member = memberControllerLocal.memberLogin(username, password);
                
//                member.setWishList(null);
//                member.setShoppingCart(null);
                
                ShoppingCart cart = member.getShoppingCart();
                cart.setMember(null);
                WishList wishlist = member.getWishList();
                wishlist.setMember(null);
                
                
                member.setPassword(null);
                member.setSalt(null);
                List<SaleTransaction> listST = member.getSaleTransactions();
                System.out.println("CHECK1");
                for (SaleTransaction saleTransaction : listST) {
                    saleTransaction.setMember(null);
                }
                LoginRsp loginRsp = new LoginRsp(member);
                System.out.println("CHECK2");
                return Response.status(Response.Status.OK).entity(loginRsp).build();
                
            } catch (InvalidLoginCredentialException ex) {
                System.out.println("*** INVALID LOGIN CREDS");
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
                System.out.println(ex.getMessage());
                return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
                
            }catch (Exception ex){
                System.out.println("*** SOME OTHER ERROR");
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
                System.out.println(ex.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
                
            }
        } else {
            ErrorRsp errorRsp = new ErrorRsp("Invalid login request");
            System.out.println("error");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMember(CreateMemberReq createMemberReq) {
        if (createMemberReq != null) {
            try {

                String firstName = createMemberReq.getFirstName();
                String lastName = createMemberReq.getLastName();
                String username = createMemberReq.getUsername();
                String password = createMemberReq.getPassword();
                Member newMember = memberControllerLocal.createNewMember(new Member(firstName, lastName, username, password, 0));
                newMember.getWishList().setMember(null);
                newMember.getShoppingCart().setMember(null);
                List<SaleTransaction> listST = newMember.getSaleTransactions();
                for (SaleTransaction saleTransaction : listST) {
                    saleTransaction.setMember(null);
                }
                CreateMemberRsp createMemberRsp = new CreateMemberRsp(newMember);
                return Response.status(Response.Status.OK).entity(createMemberRsp).build();
            } catch (InputDataValidationException ex) {
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
                return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
            } catch (Exception ex) {
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
            }
        } else {
            ErrorRsp errorRsp = new ErrorRsp("Invalid create member request");
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }

    @Path("retrieveMember/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveMember(@PathParam("id") Long id) {
        try {
            Member member = memberControllerLocal.retrieveMemberById(id);
            member.getWishList().setMember(null);
            member.getShoppingCart().setMember(null);
            List<SaleTransaction> listST = member.getSaleTransactions();
            for (SaleTransaction saleTransaction : listST) {
                saleTransaction.setMember(null);
            }
            RetrieveMemberRsp retrieveMemberRsp = new RetrieveMemberRsp(member);
            return Response.status(Response.Status.OK).entity(retrieveMemberRsp).build();
        } catch (MemberNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMember(@PathParam("id") Long id) {
        try {
            memberControllerLocal.deleteMember(id);
            return Response.status(Response.Status.OK).build();
        } catch (DeleteMemberException | MemberNotFoundException ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMember(UpdateMemberReq updateMemberReq) {
        Member member = updateMemberReq.getMember();
        if (member != null) {
            try {
                memberControllerLocal.updateMember(member);
                return Response.status(Response.Status.OK).build();
            } catch (InputDataValidationException | MemberNotFoundException | UpdateMemberException ex) {
                ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
                return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
            } 
        } else {
            ErrorRsp errorRsp = new ErrorRsp("Invalid update member request");
            return Response.status(Response.Status.BAD_REQUEST).entity(errorRsp).build();
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful;

import ejb.stateless.MemberControllerLocal;
import entity.Member;
import entity.SaleTransaction;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import util.exception.DeleteMemberException;
import util.exception.InputDataValidationException;
import util.exception.MemberNotFoundException;
import ws.datamodel.CreateMemberReq;
import ws.datamodel.CreateMemberRsp;
import ws.datamodel.ErrorRsp;
import ws.datamodel.RetrieveAllMembersRsp;
import ws.datamodel.RetrieveMemberRsp;

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
    public Response retriveAllMembers() {
        try {
            List<Member> list = memberControllerLocal.retrieveAllMembers();
            for (Member member : list) {
                member.getWishList().setMember(null);
                List<SaleTransaction> listST = member.getSaleTransactions();
                for (SaleTransaction saleTransaction : listST) {
                    saleTransaction.setMember(null);
                }
            }
            RetrieveAllMembersRsp retrieveAllMembersRsp = new RetrieveAllMembersRsp(list);
            return Response.status(Status.OK).entity(retrieveAllMembersRsp).build();
        } catch (Exception ex) {
            ErrorRsp errorRsp = new ErrorRsp(ex.getMessage());
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(errorRsp).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMember(CreateMemberReq createMemberReq) {
        if (createMemberReq != null) {
            try {
                Member member = createMemberReq.getMember();
                Member newMember = memberControllerLocal.createNewMember(member);
                newMember.getWishList().setMember(null);
                List<SaleTransaction> listST = member.getSaleTransactions();
                for (SaleTransaction saleTransaction : listST) {
                    saleTransaction.setMember(null);
                }
                CreateMemberRsp createMemberRsp = new CreateMemberRsp(newMember.getMemberId());
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

    @Path("retrieveMember")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveMember(@PathParam("id") Long id) {
        try {
            Member member = memberControllerLocal.retrieveMemberById(id);
            member.getWishList().setMember(null);
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.restful;

import ejb.stateless.MemberControllerLocal;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("Member")
public class MemberResource 
{
    @Context
    private UriInfo context;
    
    private MemberControllerLocal memberControllerLocal; 

    public MemberResource(MemberControllerLocal memberControllerLocal) {
        this.memberControllerLocal = memberControllerLocal;
    }
    
    
    
    
    
}

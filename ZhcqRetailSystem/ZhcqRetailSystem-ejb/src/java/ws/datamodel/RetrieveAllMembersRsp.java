/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.Member;
import java.util.List;

/**
 *
 * @author chengyang
 */
public class RetrieveAllMembersRsp {
    
    private List<Member> members;

    public RetrieveAllMembersRsp() {
    }

    public RetrieveAllMembersRsp(List<Member> members) {
        this.members = members;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
    
    
    
}

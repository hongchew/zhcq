/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.datamodel;

import entity.Member;

/**
 *
 * @author chengyang
 */
public class UpdateMemberReq {
    
    private Member member;

    public UpdateMemberReq() {
    }

    public UpdateMemberReq(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.Member;
import javax.ejb.Local;
import util.exception.InputDataValidationException;
import util.exception.MemberNotFoundException;
import util.exception.UpdateMemberException;


@Local
public interface MemberSessionBeanLocal {

    public Member retrieveMemberById(Long memberId) throws MemberNotFoundException;

    public Member createNewMember(Member newMember) throws InputDataValidationException;

    public void updateMember(Member memberEntity) throws InputDataValidationException, MemberNotFoundException, UpdateMemberException;
    
}

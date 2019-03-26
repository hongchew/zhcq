/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.Member;
import entity.WishList;
import java.util.Set;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.DeleteMemberException;
import util.exception.InputDataValidationException;
import util.exception.MemberNotFoundException;
import util.exception.UpdateMemberException;


@Stateless
@Local(MemberSessionBeanLocal.class)
public class MemberController implements MemberSessionBeanLocal {

    @PersistenceContext(unitName = "ZhcqRetailSystem-ejbPU")
    private EntityManager em;

   private final ValidatorFactory validatorFactory;
    private final Validator validator;
    
    
    
    public MemberController()
    {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    
    @Override
    public Member createNewMember(Member newMember) throws InputDataValidationException
    {        
        Set<ConstraintViolation<Member>>constraintViolations = validator.validate(newMember);
        
        if(constraintViolations.isEmpty())
        {
            em.persist(newMember);
            em.flush();

            return newMember;
        }
        else
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }
    
    
    
    @Override
    public Member retrieveMemberById(Long memberId) throws MemberNotFoundException
    {
        if(memberId == null)
        {
            throw new MemberNotFoundException("Member ID not provided");
        }
        
        Member memberEntity = em.find(Member.class, memberId);
        
        if(memberEntity != null)
        {
            memberEntity.getSaleTransactions().size();
            
            return memberEntity;
        }
        else
        {
            throw new MemberNotFoundException("Product ID " + memberId + " does not exist!");
        }               
    }
    
    
    @Override
     public void updateMember(Member memberEntity) throws InputDataValidationException, MemberNotFoundException, UpdateMemberException
    {
        // Updated in v4.1 to update selective attributes instead of merging the entire state passed in from the client
        // Also check for existing staff before proceeding with the update
        
        // Updated in v4.2 with bean validation
        
        Set<ConstraintViolation<Member>>constraintViolations = validator.validate(memberEntity);
        
        if(constraintViolations.isEmpty())
        {        
            if(memberEntity.getMemberId() != null)
            {
                Member memberEntityToUpdate = retrieveMemberById(memberEntity.getMemberId());
                
                if(memberEntityToUpdate.getUsername().equals(memberEntity.getUsername()))
                {
                    memberEntityToUpdate.setFirstName(memberEntity.getFirstName());
                    memberEntityToUpdate.setLastName(memberEntity.getLastName());               
                    // Username and password are deliberately NOT updated to demonstrate that client is not allowed to update account credential through this business method
                }
                else
                {
                    throw new UpdateMemberException("Username of member record to be updated does not match the existing record");
                }
            }
            else
            {
                throw new MemberNotFoundException("Member ID not provided for staff to be updated");
            }
        }
        else
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }
     
  
    public void deleteMember(Long memberId) throws MemberNotFoundException, DeleteMemberException
    {
        Member memberEntityToRemove = retrieveMemberById(memberId);
        
        if(memberEntityToRemove.getSaleTransactions().isEmpty())
        {
            em.remove(memberEntityToRemove);
        }
        else
        {
            // New in v4.1 to prevent deleting staff with existing sale transaction(s)
            throw new DeleteMemberException("Member ID " + memberId + " is associated with existing sale transaction(s) and cannot be deleted!");
        }
    }
     

    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<Member>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }
   
}

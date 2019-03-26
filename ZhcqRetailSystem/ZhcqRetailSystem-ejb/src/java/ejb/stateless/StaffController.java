/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.Staff;
import java.util.List;
import java.util.Set;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import util.exception.InputDataValidationException;
import util.exception.InvalidLoginCredentialException;
import util.exception.StaffNotFoundException;
import util.exception.UpdateStaffException;
import util.security.CryptographicHelper;

/**
 *
 * @author zhimingkoh
 */
@Stateless
@Local(StaffControllerLocal.class)
public class StaffController implements StaffControllerLocal {

    @PersistenceContext(unitName = "ZhcqRetailSystem-ejbPU")
    private EntityManager em;
    
    private final ValidatorFactory validatorFactory;
    private final Validator validator;

    public StaffController() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }
    
    @Override
    public Staff createNewStaff(Staff newStaffEntity) throws InputDataValidationException
    {        
        Set<ConstraintViolation<Staff>>constraintViolations = validator.validate(newStaffEntity);
        
        if(constraintViolations.isEmpty())
        {
            em.persist(newStaffEntity);
            em.flush();

            return newStaffEntity;
        }
        else
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }
    
    @Override
    public List<Staff> retrieveAllStaffs()
    {
        Query query = em.createQuery("SELECT s FROM Staff s");
        
        return query.getResultList();
    }
    
    @Override
    public Staff retrieveStaffByStaffId(Long staffId) throws StaffNotFoundException
    {
        if(staffId == null)
        {
            throw new StaffNotFoundException("Staff ID not provided");
        }
        
        Staff staff = em.find(Staff.class, staffId);
        
        if(staff != null)
        {
            return staff;
        }
        else
        {
            throw new StaffNotFoundException("Staff ID " + staffId + " does not exist!");
        }
    }
    
    @Override
    public Staff retrieveStaffByUsername(String username) throws StaffNotFoundException
    {
        Query query = em.createQuery("SELECT s FROM Staff s WHERE s.username = :inUsername");
        query.setParameter("inUsername", username);
        
        try
        {
            return (Staff)query.getSingleResult();
        }
        catch(NoResultException | NonUniqueResultException ex)
        {
            throw new StaffNotFoundException("Staff Username " + username + " does not exist!");
        }
    }
    
    @Override
    public Staff staffLogin(String username, String password) throws InvalidLoginCredentialException
    {
        try
        {
            Staff staff = retrieveStaffByUsername(username);
            String passwordHash = CryptographicHelper.getInstance().byteArrayToHexString(CryptographicHelper.getInstance().doMD5Hashing(password + staff.getSalt()));
            
            if(staff.getPassword().equals(passwordHash))
            {                
                return staff;
            }
            else
            {
                throw new InvalidLoginCredentialException("Username does not exist or invalid password!");
            }
        }
        catch(StaffNotFoundException ex)
        {
            throw new InvalidLoginCredentialException("Username does not exist or invalid password!");
        }
    }
    
    @Override
    public void updateStaff(Staff staff) throws InputDataValidationException, StaffNotFoundException, UpdateStaffException
    {
        
        Set<ConstraintViolation<Staff>>constraintViolations = validator.validate(staff);
        
        if(constraintViolations.isEmpty())
        {        
            if(staff.getStaffId() != null)
            {
                Staff staffEntityToUpdate = retrieveStaffByStaffId(staff.getStaffId());
                
                if(staffEntityToUpdate.getUsername().equals(staff.getUsername()))
                {
                    staffEntityToUpdate.setFirstName(staff.getFirstName());
                    staffEntityToUpdate.setLastName(staff.getLastName());
//                    staffEntityToUpdate.setAccessRightEnum(staff.getAccessRightEnum());                
                    // Username and password are deliberately NOT updated to demonstrate that client is not allowed to update account credential through this business method
                }
                else
                {
                    throw new UpdateStaffException("Username of staff record to be updated does not match the existing record");
                }
            }
            else
            {
                throw new StaffNotFoundException("Staff ID not provided for staff to be updated");
            }
        }
        else
        {
            throw new InputDataValidationException(prepareInputDataValidationErrorsMessage(constraintViolations));
        }
    }
    
    
    
    
    
    
    private String prepareInputDataValidationErrorsMessage(Set<ConstraintViolation<Staff>>constraintViolations)
    {
        String msg = "Input data validation error!:";
            
        for(ConstraintViolation constraintViolation:constraintViolations)
        {
            msg += "\n\t" + constraintViolation.getPropertyPath() + " - " + constraintViolation.getInvalidValue() + "; " + constraintViolation.getMessage();
        }
        
        return msg;
    }
    
    
    
    

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.stateless;

import entity.Staff;
import java.util.List;
import javax.ejb.Local;
import util.exception.InputDataValidationException;
import util.exception.InvalidLoginCredentialException;
import util.exception.StaffNotFoundException;
import util.exception.UpdateStaffException;

/**
 *
 * @author zhimingkoh
 */
@Local
public interface StaffControllerLocal {

    public Staff createNewStaff(Staff newStaffEntity) throws InputDataValidationException;

    public List<Staff> retrieveAllStaffs();

    public Staff retrieveStaffByStaffId(Long staffId) throws StaffNotFoundException;

    public Staff staffLogin(String username, String password) throws InvalidLoginCredentialException;

    public Staff retrieveStaffByUsername(String username) throws StaffNotFoundException;

    public void updateStaff(Staff staff) throws InputDataValidationException, StaffNotFoundException, UpdateStaffException;
    
}

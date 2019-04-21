
package jsf.managedbean;

import ejb.stateless.StaffControllerLocal;
import entity.Staff;
import java.io.IOException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import util.exception.InvalidLoginCredentialException;


@Named(value = "loginManagedBean")
@RequestScoped

public class LoginManagedBean implements Serializable {

    @EJB(name = "StaffControllerLocal")
    private StaffControllerLocal staffControllerLocal;

    private String username;
    private String password;

    public LoginManagedBean() {
    }

    public void login(ActionEvent event) throws IOException {
        System.out.println("ENTERED LOGIN METHOD IN MANAGED BEAN");
        try {
            Staff currentStaff = staffControllerLocal.staffLogin(username, password);
            FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("currentStaff", currentStaff);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("isLogin", true);
            FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/index.xhtml");
        
        } catch (InvalidLoginCredentialException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid login credential: " + ex.getMessage(), null));
        }
    }
    
    public void logout(javax.faces.event.ActionEvent event) throws IOException
    {
        ((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).invalidate();
        FacesContext.getCurrentInstance().getExternalContext().redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/index.xhtml");
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

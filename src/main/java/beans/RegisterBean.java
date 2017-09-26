package beans;

import ejb.MailService;
import ejb.UserEjb;
import entitys.Profile;
import entitys.User;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;

@ManagedBean(name = "registerBean")
@ViewScoped
public class RegisterBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String key;
    private String localSecret;
    private String phone;
    private String firstname;
    private String lastname;
    private String company;
    private String email;
    private String password;

    @EJB
    private MailService mailService;
    @EJB
    private UserEjb userEjb;

    public RegisterBean() {
    }

    public void sendKey() {
        localSecret = String.valueOf(Math.random()).replace(".", "");
        mailService.send(email, "Код подтверждения", localSecret);
    }

    private void createUser() {
        User user = new User();
        Profile profile = new Profile();
        profile.setCompany(company);
        profile.setFirstname(firstname);
        profile.setLastname(lastname);
        profile.setPhone(phone);
        user.setProfile(profile);
        user.setEmail(email);
        user.setPassword(getPassword(password));
        userEjb.add(user);
    }

    public String onFlowProcess(FlowEvent event) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (event.getOldStep().equals("personal")) {
            sendKey();
            showMessage("На вашу почту был отправлен код, введите его.", FacesMessage.SEVERITY_INFO);
        }

        if (event.getNewStep().equals("complete")) {
            if (!localSecret.equals(key)) {
                showMessage("Введенный код отличается от отправленного", FacesMessage.SEVERITY_ERROR);
                return "confirm";
            } else {
                try {
                    createUser();
                    showMessage("Учетная запись успешно создана", FacesMessage.SEVERITY_INFO);
                } catch (Exception e) {
                    e.printStackTrace();
                    showMessage("Email уже используется", FacesMessage.SEVERITY_FATAL);
                    
                    return "personal";
                }
            }
        }
        return event.getNewStep();
    }

    public void showMessage(String text, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(text);
        message.setSeverity(severity);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    private String getPassword(String pass) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(pass.getBytes());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(RegisterBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Base64.getEncoder().encodeToString(md.digest());
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}

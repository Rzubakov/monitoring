package beans;

import ejb.MailService;
import entitys.User;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import javax.annotation.PostConstruct;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FlowEvent;

@ManagedBean(name = "registerBean")
@ViewScoped
public class RegisterBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String code;
    private User user;
    private SecretKey secretKey;
    
    @EJB
    MailService mailService;

    public RegisterBean() {
    }

    @PostConstruct
    public void ini() {
        user = new User();
    }

    public void confirm() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String onFlowProcess(FlowEvent event) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        secretKey = keyGen.generateKey();
        mailService.send(user.getEmail(), secretKey.toString(), secretKey.toString());
        return event.getNewStep();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}

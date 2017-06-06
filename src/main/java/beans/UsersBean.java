package beans;

import ejb.MailService;
import entitys.User;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;


import ejb.UserEjb;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "usersBean")
@RequestScoped
public class UsersBean implements Serializable {

    private static final long serialVersionUID = 1270781916979195951L;

    @EJB
    private UserEjb userEjb;
    
    @EJB
    MailService mailService;
    
    private List<User> users;


    public UsersBean() {
    }
    @PostConstruct
    public void ini(){
        users = new ArrayList();
        users.addAll(userEjb.getUsers());
         
    }
    
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
    public void sendMail(){
        System.out.println("sending");
       mailService.send("rzubakov@protonmail.com", "text", "text");
    }

}

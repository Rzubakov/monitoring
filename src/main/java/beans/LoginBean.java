/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entitys.User;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import jpa.UserServiceJpa;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    private String email;
    private String password;
    private User user;

    public UserServiceJpa getUserService() {
        return userService;
    }

    public void setUserService(UserServiceJpa userService) {
        this.userService = userService;
    }

    @EJB
    private UserServiceJpa userService;

    public String login() {
        try {
            getRequest().login(email, password);
            user = userService.getUser(email);
            getRequest().getSession().setAttribute("user", user.getEmail());
            return "index";
        } catch (ServletException e) {
            System.out.println(e);
            return "error";
        }
    }

    public String logout() {
        getRequest().getSession().invalidate();
        return "login";
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

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
       
}

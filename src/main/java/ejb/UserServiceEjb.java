/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entitys.User;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import jpa.UserServiceJpa;

/**
 *
 * @author Rzubakov
 */
@Stateless
public class UserServiceEjb {

    public UserServiceEjb() {
    }

    @EJB
    private UserServiceJpa userServiceJpa;

    public List<User> getActiveUsers(String active) {
        return userServiceJpa.getActiveUsers(active);
    }
    
    public User getUser(Long id) {
        return userServiceJpa.get(id);
    }    
}

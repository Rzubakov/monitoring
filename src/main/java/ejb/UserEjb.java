package ejb;

import entitys.Profile;
import entitys.User;
import interceptors.Logged;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;


@Stateless
@Logged
@RolesAllowed({"ROBOT","ADMIN","USER"})
public class UserEjb extends GenericEjb<User> {

    public UserEjb() {
        super(User.class); 
    }

    public User getUser(String email) {
        return (User) manager.createNamedQuery(User.GETBYNAME).setParameter("email", email).getSingleResult();
    }

    public List<User> getUsers() {
        return manager.createNamedQuery(User.GETALL).getResultList();
    }


}

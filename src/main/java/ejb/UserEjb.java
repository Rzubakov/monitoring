package ejb;

import entitys.User;
import interceptors.Logged;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

@Stateless
@Logged
public class UserEjb extends GenericEjb<User> {

    public UserEjb() {
        super(User.class);
    }

    @RolesAllowed({"ROBOT", "ADMIN", "USER"})
    public User getUser(String email) {
        return (User) manager.createNamedQuery(User.GETBYNAME).setParameter("email", email).getSingleResult();
    }

    @RolesAllowed({"ROBOT", "ADMIN"})
    public List<User> getUsers() {
        return manager.createNamedQuery(User.GETALL).getResultList();
    }

}

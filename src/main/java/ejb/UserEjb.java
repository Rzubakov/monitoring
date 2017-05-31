package ejb;

import entitys.User;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import jpa.UserJpa;

@Stateless
public class UserEjb {

    public UserEjb() {
    }

    @EJB
    private UserJpa userJpa;

    public List<User> getActiveUsers(String active) {
        return userJpa.getActiveUsers(active);
    }

    public User getUser(Long id) {
        return userJpa.get(id);
    }
}

package ejb;

import java.util.List;
import entitys.Category;
import entitys.User;
import interceptors.Logged;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

@Stateless
@Logged
public class CategoryEjb extends GenericEjb<Category> {

    public CategoryEjb() {
        super(Category.class);
    }

    @RolesAllowed({"ROBOT", "ADMIN", "USER"})
    public Category getRoot(User user) {
        return (Category) manager.createNamedQuery(Category.GETROOT).setParameter("user", user).getSingleResult();
    }

    @RolesAllowed({"ROBOT", "ADMIN", "USER"})
    public List<Category> getCategories(User user) {
        return manager.createNamedQuery(Category.GETALL).setParameter("user", user).getResultList();
    }
}

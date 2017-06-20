package ejb;

import java.util.List;
import entitys.Category;
import entitys.User;
import interceptors.Logged;
import javax.ejb.Stateless;

@Stateless
@Logged
public class CategoryEjb extends GenericEjb<Category> {

    public CategoryEjb() {
        super(Category.class);
    }

    public Category getRoot(User user) {
        return (Category) manager.createNamedQuery(Category.GETROOT).setParameter("user", user).getSingleResult();
    }

    public Long getCount(Category category) {
        return (Long) manager.createNamedQuery(Category.GETITEMCOUNT).setParameter("category", category).getSingleResult();
    }

    public List<Category> getAll(User user) {
        return manager.createNamedQuery(Category.GETALL).setParameter("user", user).getResultList();
    }
}

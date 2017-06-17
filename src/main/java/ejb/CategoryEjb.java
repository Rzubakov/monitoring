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

    public List<Category> getCategories(Category category) {
        return manager.createNamedQuery(Category.GETCHILD).setParameter("parent", category).getResultList();
    }

    public List<Category> getAll(User user) {
        return manager.createNamedQuery(Category.GETALL).setParameter("user", user).getResultList();
    }
}

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
    
    public Category getGlobalRoot() {
        return (Category) manager.createNamedQuery(Category.GETGLOBALROOT).getSingleResult();
    }
    
    public Category getRoot(User user) {
        return (Category) manager.createNamedQuery(Category.GETROOT).setParameter("user", user).getSingleResult();
    }

    public List<Category> getAll(User user) {
        return manager.createNamedQuery(Category.GETALL).setParameter("user", user).getResultList();
    }
}

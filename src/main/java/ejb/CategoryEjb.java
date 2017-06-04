package ejb;

import java.util.List;
import entitys.Category;
import entitys.User;
import javax.ejb.Stateless;

@Stateless
public class CategoryEjb extends GenericEjb<Category> {

    public CategoryEjb() {
        super(Category.class);
    }

    public List<Category> getCategories(Category category) {
        return manager.createNamedQuery(Category.GETCHILD).setParameter("parent", category).getResultList();
    }

    public Category getRoot(User user) {
        return (Category) manager.createNamedQuery(Category.GETROOT).setParameter("user", user).getSingleResult();
    }

}
 
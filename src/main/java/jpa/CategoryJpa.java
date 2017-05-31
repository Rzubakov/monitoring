package jpa;

import java.util.List;
import entitys.Category;
import entitys.User;
import javax.ejb.Stateless;

@Stateless
public class CategoryJpa extends GenericJpa<Category> {

    public CategoryJpa() {
        super();
    }

    public List<Category> getCategories(Category category) {
        return manager.createNamedQuery(Category.GETCHILD).setParameter("parent", category).getResultList();
    }

    public List<Category> getCategories(User user) {
        return manager.createNamedQuery(Category.BYUSER).setParameter("user", user).getResultList();
    }

    public Category getRoot(User user) {
        return (Category) manager.createNamedQuery(Category.GETROOT).setParameter("user", user).getSingleResult();
    }

}

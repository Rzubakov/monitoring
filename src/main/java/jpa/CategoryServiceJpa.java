package jpa;

import java.util.List;

import entitys.Category;
import entitys.User;
import interceptors.Logger;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Interceptors(Logger.class)
@Stateless
public class CategoryServiceJpa extends AbstractService<Category> {

    public CategoryServiceJpa() {
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

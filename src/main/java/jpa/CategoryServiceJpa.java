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
        return manager.createNamedQuery("Category.getChild").setParameter("parent", category).getResultList();
    }

    public Category getRoot(User user) {
        return (Category) manager.createNamedQuery("Category.getRoot").setParameter("user", user).getSingleResult();
    }

}

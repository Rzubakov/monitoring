package jpa;

import entitys.Category;
import java.util.List;

import javax.ejb.Stateless;
import entitys.Item;
import entitys.User;

@Stateless
public class ItemJpa extends GenericJpa<Item> {

    public ItemJpa() {
        super();
    }

    public List<Item> getByUser(User user) {
        return manager.createNamedQuery(Item.BYUSER).setParameter("user", user).getResultList();
    }

    public List<Item> getByCategory(Category category, User user) {
        return manager.createNamedQuery(Item.BYCATEGORY).setParameter("category", category).setParameter("user", user).getResultList();
    }

    public Long getCount(Category category) {
        return (Long) manager.createNamedQuery(Item.GETCOUNT).setParameter("category", category).getSingleResult();
    }
}

package ejb;

import entitys.Category;
import java.util.List;

import javax.ejb.Stateless;
import entitys.Item;
import entitys.User;
import interceptors.Logged;
import javax.annotation.security.RolesAllowed;

@Stateless
@Logged
@RolesAllowed({"ROBOT","ADMIN","USER"})
public class ItemEjb extends GenericEjb<Item> {

    public ItemEjb() {
        super(Item.class);
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

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
public class ItemEjb extends GenericEjb<Item> {

    public ItemEjb() {
        super(Item.class);
    }

    @RolesAllowed({"ROBOT", "ADMIN", "USER"})
    public List<Item> getByCategory(Category category, User user) {
        return manager.createNamedQuery(Item.BYCATEGORY).setParameter("category", category).setParameter("user", user).getResultList();
    }
}

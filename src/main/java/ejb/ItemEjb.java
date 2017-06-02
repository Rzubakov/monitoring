package ejb;

import entitys.Item;
import entitys.User;
import interceptors.Logger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import jpa.ItemJpa;

@Logger
@Stateless
public class ItemEjb {

    public ItemEjb() {
    }

    @EJB
    private ItemJpa itemJpa;

    public List<Item> getByUser(User user) {
        return itemJpa.getByUser(user);
    }

    public Item getItem(Long id) {
        return itemJpa.get(id);
    }
}

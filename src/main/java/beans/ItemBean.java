package beans;

import ejb.ItemEjb;
import entitys.Category;
import entitys.Item;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;


@ManagedBean(name = "itemBean")
@ViewScoped
public class ItemBean implements Serializable {

    @ManagedProperty("#{loginBean}")
    private LoginBean loginBean;
    private Item item;
    private List<Item> items;

    private Category category;
    private Item selectedItem;
    private static final long serialVersionUID = -1605493260723628519L;
    @EJB
    private ItemEjb itemEjb;

    @PostConstruct
    public void ini() {
        item = new Item();
        items = new ArrayList<>();
        System.out.println("construct itemBean");
    }

    public void loadData() {
        items.clear();
        items.addAll(itemEjb.getByCategory(category, loginBean.getUser()));
    }

    public void addItem() {
        item.setCategory(category);
        item.setUser(loginBean.getUser());
        itemEjb.add(item);
        item = new Item();
        RequestContext.getCurrentInstance().execute("PF('addItem').hide()");
        loadData();
    }

    public void updateItem() {
        itemEjb.update(selectedItem);
        RequestContext.getCurrentInstance().execute("PF('editItem').hide()");
        loadData();
    }

    public void deleteItem(Item item) {
        itemEjb.delete(item);
        loadData();
    }

    public void copyItem(Item item) {
        item.setId(0);
        itemEjb.add(item);
        loadData();
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }



}

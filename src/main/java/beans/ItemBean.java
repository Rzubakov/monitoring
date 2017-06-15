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
import org.primefaces.event.NodeExpandEvent;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "itemBean")
@ViewScoped
public class ItemBean implements Serializable {

    @ManagedProperty("#{loginBean}")
    private LoginBean loginBean;
    private Item item;
    private List<Item> items;
    private List<Item> filteredItems;
    private TreeNode selectedNode;
    private Item selectedItem;
    private static final long serialVersionUID = -1605493260723628519L;
    @EJB
    private ItemEjb itemEjb;

    @PostConstruct
    public void ini() {
        item = new Item();
        items = new ArrayList<>();
    }

    public void loadData() {
        items.clear();
        items.addAll(itemEjb.getByCategory((Category) selectedNode.getData(), loginBean.getUser()));
    }

    public void addItem() {
        item.setCategory((Category) selectedNode.getData());
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

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }

    public List<Item> getFilteredItems() {
        return filteredItems;
    }

    public void setFilteredItems(List<Item> filteredItems) {
        this.filteredItems = filteredItems;
    }

}

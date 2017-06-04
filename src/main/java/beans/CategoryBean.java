package beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.primefaces.model.*;
import entitys.Category;
import entitys.Item;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import ejb.CategoryEjb;
import ejb.ItemEjb;
import org.primefaces.context.RequestContext;
import org.primefaces.event.NodeSelectEvent;

@ManagedBean(name = "categoryBean")
@ViewScoped
public class CategoryBean implements Serializable {

    private static final long serialVersionUID = 2745513588634191629L;

    private TreeNode root;
    private TreeNode selectedNode;
    private Category category;
    private Item item;
    private List<Item> items;
    private List<Item> filteredItems;
    private Item selectedItem;

    @ManagedProperty("#{loginBean}")
    private LoginBean loginBean;

    @EJB
    private CategoryEjb categoryEjb;
    @EJB
    private ItemEjb itemEjb;

    @PostConstruct
    public void ini() {
        category = new Category();
        item = new Item();
        items = new ArrayList<>();
        root = new DefaultTreeNode("root", null);
        root.getChildren().add(new DefaultTreeNode(categoryEjb.getRoot(loginBean.getUser()), root));
    }

    public void addItem() {
        item.setCategory((Category) selectedNode.getData());
        item.setUser(loginBean.getUser());
        itemEjb.add(item);
        item = new Item();
        RequestContext.getCurrentInstance().execute("PF('addItem').hide()");
        loadItems();
    }

    public void updateItem() {
        itemEjb.update(selectedItem);
        RequestContext.getCurrentInstance().execute("PF('editItem').hide()");
        loadItems();
    }

    public void deleteItem() {
        itemEjb.delete(selectedItem);
        loadItems();
        selectedItem=null;
    }

    public void copyItem() {
        selectedItem.setId(0);
        itemEjb.add(selectedItem);
        loadItems();
    }

    
    public void addCategory() {
        category.setParent((Category) selectedNode.getData());
        category.setUser(loginBean.getUser());
        categoryEjb.add(category);
        category = new Category();
        RequestContext.getCurrentInstance().execute("PF('addCategory').hide()");
    }

    public void deleteCategory() {
        categoryEjb.delete((Category) selectedNode.getData());
    }

    public void loadCategory() {
        selectedNode.getChildren().clear();
        categoryEjb.getCategories((Category) selectedNode.getData()).forEach((Category cat) -> {
            selectedNode.getChildren().add(new DefaultTreeNode(cat));    
        });
    }

    public void loadItems() {
        items.clear();
        items.addAll(itemEjb.getByCategory((Category) selectedNode.getData(), loginBean.getUser()));
    }

    public TreeNode getRoot() {
        return root;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public void onNodeSelect(NodeSelectEvent event) {
        loadItems();
    }

}

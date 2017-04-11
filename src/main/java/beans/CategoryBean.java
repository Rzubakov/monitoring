package beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import org.primefaces.model.*;
import entitys.Category;
import entitys.Item;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import jpa.CategoryServiceJpa;
import jpa.ItemServiceJpa;

@ManagedBean(name = "categoryBean")
@ViewScoped
public class CategoryBean implements Serializable {

    private static final long serialVersionUID = 2745513588634191629L;

    private TreeNode root;
    private TreeNode selectedNode;
    private Item selectedItem;
    private Category category;
    private Item item;
    private List<Item> items;
    private List<Item> filteredItems;

    @ManagedProperty("#{loginBean}")
    private LoginBean loginBean;

    @EJB
    private CategoryServiceJpa categoryServiceJpa;
    @EJB
    private ItemServiceJpa itemServiceJpa;

    @PostConstruct
    public void ini() {
        category = new Category();
        item = new Item();
        root = new DefaultTreeNode("root", null);
        root.getChildren().add(new DefaultTreeNode(categoryServiceJpa.getRoot(loginBean.getUser()), root));
        loadNodes((Category) root.getChildren().get(0).getData(), root.getChildren().get(0));
    }

    public void addItem() {
        item.setCategory((Category) selectedNode.getData());
        item.setUser(loginBean.getUser());
        itemServiceJpa.add(item);
        item = new Item();
    }

    public void addCategory() {
        category.setParent((Category) selectedNode.getData());
        category.setUser(loginBean.getUser());
        categoryServiceJpa.add(category);
        category = new Category();
        loadNodes((Category) selectedNode.getData(), selectedNode);
    }

    public void deleteCategory() {
        categoryServiceJpa.delete((Category) selectedNode.getData());
        loadNodes((Category) selectedNode.getParent().getData(), selectedNode.getParent());
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

    private void loadNodes(Category root, TreeNode node) {
        node.getChildren().clear();
        categoryServiceJpa.getCategories(root).forEach((Category cat) -> {
            loadNodes(cat, new DefaultTreeNode(cat+"(t)", node));
        });
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

}

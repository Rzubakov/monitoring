package beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.primefaces.model.*;
import entitys.Category;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import ejb.CategoryEjb;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@ManagedBean(name = "categoryBean")
@ViewScoped
public class CategoryBean implements Serializable {

    private static final long serialVersionUID = 2745513588634191629L;

    private TreeNode root;
    private TreeNode selected;
    private Category category;
    @ManagedProperty("#{loginBean}")
    private LoginBean loginBean;

    @EJB
    private CategoryEjb categoryEjb;

    @PostConstruct
    public void ini() {
        category = new Category();
        root = new DefaultTreeNode("root", null);
        root.getChildren().add(buildTree(categoryEjb.getRoot(loginBean.getUser())));
    }

    public void add() throws InterruptedException {
        category.setParent((Category) selected.getData());
        category.setUser(loginBean.getUser());
        selected.getChildren().add(new DefaultTreeNode(categoryEjb.add(category)));
        category = new Category();
        Thread.sleep(2000);
    }

    public void update() {
        categoryEjb.update(category);
    }

    public void delete() {
        categoryEjb.delete((Category) selected.getData());
        selected.getParent().getChildren().remove(selected);
    }

    public TreeNode getRoot() {
        return root;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public TreeNode getSelected() {
        return selected;
    }

    public void setSelected(TreeNode selected) {
        this.selected = selected;
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    private TreeNode buildTree(Category parent) {
        System.out.println("Get childs for: " + parent);
        TreeNode node = new DefaultTreeNode(parent);
        categoryEjb.getChild(parent).forEach(child -> {
            node.getChildren().add(buildTree(child));
        });
        return node;
    }

}

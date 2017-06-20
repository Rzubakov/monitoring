package beans;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import org.primefaces.model.*;
import entitys.Category;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import ejb.CategoryEjb;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "categoryBean")
@ViewScoped
public class CategoryBean implements Serializable {

    private static final long serialVersionUID = 2745513588634191629L;

    private TreeNode root;
    private TreeNode selectedNode;
    private Category category;
    private List<Category> categories;
    @ManagedProperty("#{loginBean}")
    private LoginBean loginBean;

    @EJB
    private CategoryEjb categoryEjb;

    @PostConstruct
    public void ini() {
        category = new Category();
        root = new DefaultTreeNode("root", null);
        categories = new ArrayList<>();
        loadData();
    }

    public void addCategory() {
        category.setParent((Category) selectedNode.getData());
        category.setUser(loginBean.getUser());
        categoryEjb.add(category);
        category = new Category();
        loadData();
        RequestContext.getCurrentInstance().execute("PF('addCategory').hide()");
    }

    public void deleteCategory(Category category) {
        categoryEjb.delete(category);
        loadData();
    }

    public void copyCategory(Category category) {
        category.setId(0);
        categoryEjb.add(category);
        loadData();
    }

    public TreeNode getRoot() {
        return root;
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

    private void loadData() {
        root.getChildren().clear();
        categories.clear();
        categories.addAll(categoryEjb.getAll(loginBean.getUser()));
        root.getChildren().add(buildTree(categories, categories.get(0)));
    }

    private TreeNode buildTree(List<Category> cats, Category category) {
        TreeNode temp = new DefaultTreeNode(category);
        cats.forEach(c -> {
            if (c.getParent().getId() == category.getId()) {
                temp.getChildren().add(buildTree(cats, c));
            }
        });
        return temp;
    }

}

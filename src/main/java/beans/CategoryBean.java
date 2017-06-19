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
    private List<Category> allCategory;

    @ManagedProperty("#{loginBean}")
    private LoginBean loginBean;

    @EJB
    private CategoryEjb categoryEjb;

    @PostConstruct
    public void ini() {
        category = new Category();
        allCategory = new ArrayList();
        root = new DefaultTreeNode("root", null);
        allCategory.addAll(categoryEjb.getAll(loginBean.getUser()));
        root.getChildren().add(buildTree(allCategory, allCategory.get(0)));
    }

    public void addCategory() {
        category.setParent((Category) selectedNode.getData());
        category.setUser(loginBean.getUser());
        categoryEjb.add(category);
        category = new Category();
        RequestContext.getCurrentInstance().execute("PF('addCategory').hide()");
    }

    public void deleteCategory(Category category) {
        categoryEjb.delete(category);
    }

    public void copyCategory(Category category) {
        category.setId(0);
        categoryEjb.add(category);
    }

    public void loadCategory() {
        selectedNode.getChildren().clear();
        categoryEjb.getCategories((Category) selectedNode.getData()).forEach((Category cat) -> {
            selectedNode.getChildren().add(new DefaultTreeNode(cat));
        });
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

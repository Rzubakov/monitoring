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

@ManagedBean(name = "categoryBean")
@ViewScoped
public class CategoryBean implements Serializable {

    private static final long serialVersionUID = 2745513588634191629L;

    private TreeNode root;
    private Category category;
    private TreeNode selectedNode;
    
    @ManagedProperty("#{loginBean}")
    private LoginBean loginBean;

    @EJB
    private CategoryEjb categoryEjb;

    @PostConstruct
    public void ini() {
        root = new DefaultTreeNode("root", null);
        category = new Category();
        root.getChildren().add(buildTree(categoryEjb.getRoot(loginBean.getUser())));
    }

    public void addCategory() {
        category.setParent((Category) selectedNode.getData());
        category.setUser(loginBean.getUser());
        selectedNode.getChildren().add(new DefaultTreeNode(categoryEjb.add(category)));
        RequestContext.getCurrentInstance().execute("PF('addCategory').hide()");
        category = new Category();
    }

    public void delete() {
        categoryEjb.delete((Category) selectedNode.getData());
        selectedNode.getParent().getChildren().remove(selectedNode);
    }

    public void update() {
        categoryEjb.update((Category) selectedNode.getData());
        RequestContext.getCurrentInstance().execute("PF('editCategory').hide()");
    }

    public void copy() {
        category = (Category) selectedNode.getData();
        category.setId(0);
        selectedNode.getParent().getChildren().add(new DefaultTreeNode(categoryEjb.add(category)));
        category = new Category();
    }

    public TreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(TreeNode selectedNode) {
        this.selectedNode = selectedNode;
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

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    private TreeNode buildTree(Category root) {
        TreeNode node = new DefaultTreeNode(root);
        root.getChildCategories().forEach(cat->{
            node.getChildren().add(new DefaultTreeNode(cat));
            buildTree(cat);
        });
        return node;
    }
 
}

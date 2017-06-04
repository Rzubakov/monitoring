package entitys;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({
    @NamedQuery(name = "Category.getRoot", query = "select c from Category c WHERE c.user=:user and c.parent=1"),
    @NamedQuery(name = "Category.getChild", query = "select c from Category c WHERE c.parent=:parent"),
    @NamedQuery(name = "Category.getByUser", query = "select c from Category c WHERE c.user=:user order by c.id"),})
@Table(name = "Categories")
public class Category extends EntityModel  {

    private static final long serialVersionUID = 2106988008207440715L;
    public static final String BYUSER = "Category.getByUser";
    public static final String GETCHILD = "Category.getChild";
    public static final String GETROOT = "Category.getRoot";

    public Category() {
        super();
    }

    public Category(String name, String description) {
        super();
        this.name = name;
    }

    @NotNull
    private String name;

    @ManyToOne
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Category> childCategories;

    public List<Category> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<Category> childCategories) {
        this.childCategories = childCategories;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private List<Item> items;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "userid")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void deleteItem(Item item) {
        items.remove(item);
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return getId()+" "+getName();
    }
}

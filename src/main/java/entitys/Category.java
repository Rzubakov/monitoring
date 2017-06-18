package entitys;

import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@NamedQueries({
    @NamedQuery(name = "Category.getChild", query = "select c from Category c WHERE c.parent=:parent")
    ,
    @NamedQuery(name = "Category.getAll", query = "select c from Category c  WHERE  c.user=:user")
    ,
    @NamedQuery(name = "Category.getByUser", query = "select c from Category c WHERE c.user=:user order by c.id"),})
@Table(name = "Categories")
public class Category extends EntityModel {

    public static final String BYUSER = "Category.getByUser";
    public static final String GETCHILD = "Category.getChild";
    public static final String GETALL = "Category.getAll";
    private static final long serialVersionUID = 4121663523061263937L;

    public Category() {
        super();
    }

    public Category(String name, String description, User user) {
        super();
        this.name = name;
        this.description = description;
        this.user = user;
    }

    public Category(String name) {
        super();
        this.name = name;
    }

    @NotNull
    private String name;
    private String description;

    @ManyToOne
    @NotNull
    private Category parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Category> childCategories;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Item> items;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "user_id")
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<Category> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<Category> childCategories) {
        this.childCategories = childCategories;
    }

    @Override
    public String toString() {
        return this.name + "(" + this.items.size() + ")";
    }
}

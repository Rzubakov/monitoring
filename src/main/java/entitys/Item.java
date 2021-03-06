package entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
    @NamedQuery(name = "Item.getItemsByCategory", query = "select i from Item i WHERE i.category=:category and i.user=:user"),
    @NamedQuery(name = "Item.getByUser", query = "select i from Item i WHERE i.user=:user"),
    @NamedQuery(name = "Item.getCount", query = "select count(*) from Item i WHERE i.category=:category")})
@Table(name = "Items")
public class Item extends EntityModel {

    public static final String BYUSER = "Item.getByUser";
    public static final String BYCATEGORY = "Item.getItemsByCategory";
    public static final String GETCOUNT = "Item.getCount";
    private static final long serialVersionUID = 7076621016413789647L;

    public Item() {
        super();
    }

    public Item(String name, String url, String path) {
        super();
        this.name = name;
        this.url = url;
        this.path = path;
    }

    @NotNull
    private String name;
    @NotNull
    private String url;
    @NotNull
    private String path;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "category_id")
    private Category category;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String shop) {
        this.name = shop;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

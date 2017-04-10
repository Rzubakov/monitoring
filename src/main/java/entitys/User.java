package entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
    @NamedQuery(name = "User.getUsers", query = "select u from User u"),
    @NamedQuery(name = "User.getUserByName", query = "select u from User u WHERE u.email=:email"),     
    @NamedQuery(name = "User.getUsersByActive", query = "select u from User u  WHERE u.active LIKE :active"),})
@Table(name = "Users")
public class User extends EntityModel {

    private static final long serialVersionUID = 4223092205992504237L;
    public static final String GETALL = "User.getUsers";
    public static final String GETBYNAME = "User.getUserByName";
    public static final String GETACTIVE = "User.getUsersByActive";

    public User() {
        super();
    }

    public User(String email, String password, String active, Integer itemCount) {
        super();
        this.email = email;
        this.password = password;
        this.active = active;
        this.itemCount = itemCount;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Item> items;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Category> category;

    @NotNull
    private String email;

    @JsonIgnore
    @NotNull
    private String password;

    @JsonIgnore
    @NotNull
    private String active;

    @JsonIgnore
    @NotNull
    private Integer itemCount;

    @JsonIgnore
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;    

    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }
    
    @PrePersist
    public void prePersist(){
        this.date = new Date();
    }
}

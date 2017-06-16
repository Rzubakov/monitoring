package entitys;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@NamedQueries({
    @NamedQuery(name = "User.getUsers", query = "select u from User u")
    ,
    @NamedQuery(name = "User.getUserByName", query = "select u from User u WHERE u.email=:email")
})
@Table(name = "Users")
public class User extends EntityModel {

    public static final String GETALL = "User.getUsers";
    public static final String GETBYNAME = "User.getUserByName";
    public static final String GETACTIVE = "User.getUsersByActive";
    private static final long serialVersionUID = -5398060189052359088L;

    public User() {
        super();
    }

    public User(String email, String password, String role, Profile profile) {
        super();
        this.email = email;
        this.password = password;
        this.role = role;
        this.profile = profile;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Item> items;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Category> category;

    @OneToOne(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Profile profile;
    
    
    @NotNull
    private String email;

    @JsonIgnore
    @NotNull
    private String password;

    @NotNull
    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}

package entitys;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Profiles")
public class Profile extends EntityModel {
    private static final long serialVersionUID = -6672076827652794004L;

    public Profile() {
        super();
    }

    public Profile(String phone, String firstname, String lastname) {
        super();
        this.phone = phone;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @NotNull
    private String phone;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    private String company;

    @OneToOne(mappedBy = "profile",fetch = FetchType.LAZY)
    @NotNull
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date dateCreate;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    @PrePersist
    public void prePersist() {
        this.dateCreate = new Date();
    }

}

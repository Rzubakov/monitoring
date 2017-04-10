/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entitys;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Rzubakov
 */
@Entity
@Table(name = "Roles")
public class Role extends EntityModel {
    public Role(){}
    public static String ADMIN = "ADMIN";
    public static String USER = "USER";
    public static String BOT = "USER";
 
    @NotNull
    private String email;
    
    @NotNull
    private String role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}

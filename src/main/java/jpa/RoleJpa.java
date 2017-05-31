package jpa;

import entitys.Role;
import javax.ejb.Stateless;

@Stateless
public class RoleJpa extends GenericJpa<Role> {

    public RoleJpa() {
        super();
    }

}

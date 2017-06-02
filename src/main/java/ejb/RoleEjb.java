package ejb;

import entitys.Role;
import javax.ejb.Stateless;

@Stateless
public class RoleEjb extends GenericEjb<Role> {

    public RoleEjb() {
        super(Role.class);
    }

}

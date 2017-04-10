package jpa;

import entitys.Role;
import interceptors.Logger;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors(Logger.class)
public class RoleServiceJpa extends AbstractService<Role> {

    public RoleServiceJpa() {
        super();
    }

}

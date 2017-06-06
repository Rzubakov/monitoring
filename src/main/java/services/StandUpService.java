package services;

import entitys.Category;
import entitys.Role;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entitys.User;

@Startup
@Singleton
public class StandUpService {

    @PersistenceContext
    protected EntityManager manager;

    @PostConstruct
    public void ini() {

        User user = new User("rzubakov@protonmail.com", "/gnplWM1A3X6t7sLuLjnOnqLZjAYewyEJLoq8+H6I4s=", "Y", 10);
        Role role = new Role();
        role.setRole(Role.ADMIN);
        role.setEmail(user.getEmail());
        manager.persist(role);
        manager.persist(user);
        manager.persist(new Category("GlobalRoot", "GlobalRoot", user));

    }

}

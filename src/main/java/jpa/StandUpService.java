package jpa;


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
	public void ini(){
		System.out.println("Create User");

                User user = new User("rzubakov@protonmail.com","/gnplWM1A3X6t7sLuLjnOnqLZjAYewyEJLoq8+H6I4s=","Y", 10);
                
                Role role = new Role();
                role.setRole(Role.ADMIN);
                role.setEmail(user.getEmail());
                Category category = new Category();
                category.setUser(user);
                category.setName("Root");
                manager.persist(role);
		manager.persist(user);
                manager.persist(category);
	 
	}	
}

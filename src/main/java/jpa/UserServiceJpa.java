package jpa;

import entitys.User;
import interceptors.Logger;
import java.util.List;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors(Logger.class)
public class UserServiceJpa extends AbstractService<User> {

    public UserServiceJpa() {
        super();
    }

    public User getUser(String email) {
        return (User) manager.createNamedQuery(User.GETBYNAME).setParameter("email", email).getSingleResult();
    }

    public List<User> getActiveUsers(String active) {
        return manager.createNamedQuery(User.GETACTIVE).setParameter("active", active).getResultList();
    }

    public List<User> getUsers() {
        return manager.createNamedQuery(User.GETALL).getResultList();
    }
    /*
	public String getPass(String pass) throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
    	md.update(pass.getBytes());
    	return Base64.encodeBase64String(md.digest());
	}*/
}

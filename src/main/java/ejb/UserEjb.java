package ejb;

import entitys.User;
import java.util.List;
import javax.ejb.Stateless;

@Stateless
public class UserEjb extends GenericEjb<User> {

    public UserEjb() {
        super(User.class);
    }

    public User getUser(String email) {
        return (User) manager.createNamedQuery(User.GETBYNAME).setParameter("email", email).getSingleResult();
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

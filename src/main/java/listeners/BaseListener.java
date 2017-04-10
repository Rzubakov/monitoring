package listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class BaseListener implements HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent se) {
            
		System.out.println("sessionCreated "+se.getSession().getServletContext().getContextPath());
                System.out.println(se.getSession().getServletContext().getServletContextName());
	} 
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
                System.out.println(se.getSession().getAttribute("user"));
		System.out.println("sessionDestroyed");
	}

}
 
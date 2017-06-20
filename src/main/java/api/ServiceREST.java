package api;

import ejb.*;
import entitys.User;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rs")
@Stateless
@RolesAllowed({"ROBOT"})
public class ServiceREST {

    @EJB
    private ItemEjb itemEjb;
    @EJB
    private UserEjb userEjb;

    @GET
    @Path("/test")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getString() {
        return Response.ok().entity("Good").build();
    }

    @PermitAll
    @GET
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    public void login(@QueryParam("login") String login, @QueryParam("password") String password, @Context HttpServletRequest request) {
        try {
            request.login(login, password);
        } catch (ServletException ex) {
            ex.printStackTrace();
            throw new SecurityException("User is unauthorized.");
        }
    }

    /* @GET
    @Path("/getallitem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems(@QueryParam("userid") Long userid) {
        return Response.ok().entity(itemEjb.getByUser(userEjb.getUser(userid))).build();
    }
     */
    @GET
    @Path("/getusers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getItems(@QueryParam("active") String active) {
        return userEjb.getUsers();
    }

}

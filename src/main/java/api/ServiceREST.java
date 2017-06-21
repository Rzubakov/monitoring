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
    @POST
    @Path("/login")
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(@FormParam("login") String login,@FormParam("password") String password, @Context HttpServletRequest request) {
        try {
            request.login(login, password);
        } catch (ServletException ex) {
            ex.printStackTrace();
            throw new SecurityException("User is unauthorized.");
        }
        return Response.ok().entity("Good").build();
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
    public List<User> getUsers() {
        return userEjb.getUsers();
    }

}

package api;

import ejb.*;
import entitys.User;
//import entitys.Element;
import interceptors.Logger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rs")
@Interceptors(Logger.class)
@Stateless
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

    @GET
    @Path("/getallitem")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getItems(@QueryParam("userid") Long userid) {
        return Response.ok().entity(itemEjb.getByUser(userEjb.getUser(userid))).build();
    }

    @GET
    @Path("/getusers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getItems(@QueryParam("active") String active) {
        return userEjb.getActiveUsers(active);
    }

}

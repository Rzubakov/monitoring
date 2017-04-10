/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private ItemServiceEjb itemServiceEjb;
    @EJB
    private UserServiceEjb userServiceEjb;

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
        return Response.ok().entity(itemServiceEjb.getByUser(userServiceEjb.getUser(userid))).build();
    }

    @GET
    @Path("/getusers")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getItems(@QueryParam("active") String active) {
        return userServiceEjb.getActiveUsers(active);
    }

}

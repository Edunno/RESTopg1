/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import dbfacades.PersonFacade;
import entity.Person;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utility.JSONConverter;

/**
 * REST Web Service
 *
 * @author Esben
 */
@Path("Person")
public class PersonResource {

    Gson gson = new Gson();
    @Context
    private UriInfo context;
    JSONConverter jc = new JSONConverter();

    /**
     * Creates a new instance of PersonResource
     */
    public PersonResource() {
    }

    /**
     * Retrieves representation of an instance of rest.PersonResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/person")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allPersons() {
        PersonFacade p = new PersonFacade();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        p.addEntityManagerFactory(emf);
        List<Person> persons = p.getAllPersons();
//        return JSONConverter.getJsonFromPersons(persons);
        return Response.ok().entity(jc.getJsonFromPersons(persons)).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonFromId(@PathParam("id") int id) {
        PersonFacade p = new PersonFacade();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        p.addEntityManagerFactory(emf);
        Person ps = p.getPerson(id);
        return Response.ok().entity(jc.getJsonFromPerson(ps)).build();
    }

    /**
     * PUT method for updating or creating an instance of PersonResource
     *
     * @param content representation for the resource
     */
    @PUT //Edit
    @Path("/person")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putPersonInfo(String content) {

    }
}

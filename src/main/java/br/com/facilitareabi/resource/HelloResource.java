package br.com.facilitareabi.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class HelloResource {

    //Método listar
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String hello(){
        return "{\"mensagem\": \"Bem-vindo ao FacilitaReabi!\"}";
    }
}

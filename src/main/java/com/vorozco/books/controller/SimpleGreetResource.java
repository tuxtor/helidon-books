
package com.vorozco.books.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hello")
@RequestScoped
public class SimpleGreetResource {

    String greeting;

    @Inject
    public SimpleGreetResource(@ConfigProperty(name = "app.greeting",
            defaultValue = "hello") String greeting){
        this.greeting = greeting;
    }

    @GET
    public String doHello(){
        return greeting;
    }

}

package com.myapp.resources;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("hello")
public class HelloWorldResource {
    @GET
    @Produces("text/plain")
    public String getHello() {
        return "hello world";
    }
}

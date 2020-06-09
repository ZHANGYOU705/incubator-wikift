package io.edurt.wikift.server.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path(value = "/")
public class HomeController {

    @GET
    public String home() {
        return "Welcome To Wikift System";
    }

}

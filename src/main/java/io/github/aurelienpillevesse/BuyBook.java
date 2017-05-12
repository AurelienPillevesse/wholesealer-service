package io.github.aurelienpillevesse;

import java.net.URI;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/buy")
public class BuyBook {
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public String buyOne() {		
		
		return "yo";
	}	
}
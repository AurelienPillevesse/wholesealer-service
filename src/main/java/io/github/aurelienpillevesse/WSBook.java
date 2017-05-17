package io.github.aurelienpillevesse;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import io.github.aurelienpillevesse.dao.BookDAO;
import io.github.aurelienpillevesse.dao.DAO;
import io.github.aurelienpillevesse.model.Book;
import io.github.aurelienpillevesse.model.CustomResponse;

import static java.lang.Math.toIntExact;

@Path("/ws")
public class WSBook {
	
	@PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response BuyWholesaler(String jsonRecevied)
	{		
		CustomResponse cr = new CustomResponse();
		JSONParser parser = new JSONParser();
		String isbn;
		int quantity;
		int stock;
		
		try{
			Object obj = parser.parse(jsonRecevied);
	        JSONObject obj2 = (JSONObject) obj;
	        
	        isbn = (String) obj2.get("isbn");
	        quantity = (int) (long) obj2.get("quantity");
	        stock = (int) (long) obj2.get("stock");
	    } catch(ParseException pe){
	    	cr.setData(null);
	    	cr.setMessage("error with json");
	    	
	    	return Response.status(400).entity(cr).build();			
	    }
	
		Client client = ClientBuilder.newClient();
    	WebTarget target = client.target("https://stock-service-p2017.herokuapp.com/updatestock");
    	
    	String input = "{\"quantity\":"+ quantity + ",\"stock\":\"" + stock + ",\"isbn\":\"" + isbn + "\"}";
    	Response r = target.request().put(Entity.json(input));
		
		if(r.getStatus() == 400) {
			return Response.status(r.getStatus()).entity(r.readEntity(CustomResponse.class).getMessage()).build();
		}
		
		cr.setData(null);
		cr.setMessage("wholesale order sent");
		return Response.status(200).entity(cr).build();
	}	
}
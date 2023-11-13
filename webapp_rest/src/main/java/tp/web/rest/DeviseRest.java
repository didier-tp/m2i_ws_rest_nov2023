package tp.web.rest;


import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import tp.web.dto.Devise;

@Path("my-api/devise")
@Produces("application/json")
//@Named
public class DeviseRest {
	
	
	@GET
	@Path("/{code}")
	//http://localhost:8080/webapp_rest/rest/my-api/devise/EUR
	public Devise getDevise(@PathParam("code") String code) {
	   return new Devise(1L,code,"...",1.123);
	}

}

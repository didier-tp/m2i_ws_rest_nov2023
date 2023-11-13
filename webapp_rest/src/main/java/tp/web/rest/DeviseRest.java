package tp.web.rest;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tp.web.dto.Devise;
import tp.web.service.ServiceDevise;

@Path("my-api/devise")
@Produces("application/json")
//@Named
public class DeviseRest {
	
	//service interne (ex: EJB)/ simulation persistance en base
	private ServiceDevise serviceDevise = new ServiceDevise();

	//@Inject
	//private ServiceDevise serviceDevise;
	
	@GET
	@Path("/{code}")
	//RECHERCHE UNIQUE RETOURNANT EVENTUELLEMENT NOT_FOUND
	//http://localhost:8080/webapp_rest/rest/my-api/devise/EUR
	
	/*
	public Devise getDeviseByCode(@PathParam("code") String code) {
	   return serviceDevise.getDeviseByCode(code);
	}
	*/
	public Response getDeviseByCode(@PathParam("code") String code) {
		   Devise devise = serviceDevise.getDeviseByCode(code);
		   if(devise==null)
			   return Response.status(Status.NOT_FOUND).build();
		   else
		       return Response.status(Status.OK).entity(devise).build();
		}
	
	
	
	@GET
	@Path("")
	//RECHERCHE MULTIPLE AVEC CRITERES DE RECHERCHE FACULTATIF 
	//RETOURNANT LISTE VIDE OU PAS
	//http://localhost:8080/webapp_rest/rest/my-api/devise  retournant toutes les devises
	//http://localhost:8080/webapp_rest/rest/my-api/devise?changeMini=1.01 retournant toutes les devises avec changeMini=1.01
	public List<Devise> getDevisesByCriteria(@QueryParam(value="changeMini") Double changeMini) {
	  if(changeMini==null)
		  return serviceDevise.getAllDevises();
	  else
		  return serviceDevise.geDevisesWithChangeMini(changeMini);
	}
	
	@POST
	@Path("")
	//POST (AJOUT VERS LE SERVEUR)
	//http://localhost:8080/webapp_rest/rest/my-api/devise 
	//avec dans la partie invisible "body" de la requête
	//{ "id" : null , "code" : "MS1" , "nom" : "MonnaieSinge1" , "change" : 1234567.6 }
	//{ "code" : "MS1" , "nom" : "MonnaieSinge1" , "change" : 1234567.6 }
	@Consumes("application/json")
	public Devise postDevise(Devise devise) {
		return serviceDevise.insertDevise(devise);
	}
	
	@PUT
	@Path("")
	//PUT (MISE A JOUR VERS LE SERVEUR)
	//http://localhost:8080/webapp_rest/rest/my-api/devise 
	//avec dans la partie invisible "body" de la requête
	//{ "id" : 5 , "code" : "MS1" , "nom" : "MonnaieSinge1" , "change" : 1234567.6 }
	@Consumes("application/json")
	public Devise putDevise(Devise devise) {
		return serviceDevise.updateDevise(devise);
	}
	
	@DELETE
	@Path("/{id}")
	//SUPPRESSION VERS SERVEUR
	//http://localhost:8080/webapp_rest/rest/my-api/devise/5
	public Response deleteDeviseById(@PathParam("id") Long id) {
		return null;//....
	}
	
	

}

package tp.web.rest;


import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Singleton;
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
import tp.web.rest.util.JWTTokenNeeded;
import tp.web.service.ServiceDevise;

//api-bank/compte
//        /virement
//api-finance/devise
//api-xxx/produit
//       /category
//api-yyy/client


@Path("my-api/devise")
@Produces("application/json")
@Singleton //javax.inject.Singleton
public class DeviseRest {
	
	//service interne (ex: EJB)/ simulation persistance en base
	@Inject
	private ServiceDevise serviceDevise;
	
	@PostConstruct
	public void init() {
		System.out.println("DeviseRest , init() serviceDevise="+serviceDevise);
	}
	
	@GET
	@Path("/{codeOrId}")
	//RECHERCHE UNIQUE RETOURNANT EVENTUELLEMENT NOT_FOUND
	//http://localhost:8080/webapp_rest/rest/my-api/devise/EUR
	//http://localhost:8080/webapp_rest/rest/my-api/devise/1
	public /*Response*/ Devise getDeviseByCodeOrId(@PathParam("codeOrId") String codeOrId) {
		   Devise devise = null;
		   try {
			   Long id=Long.parseLong(codeOrId);
				devise = serviceDevise.getDeviseById(id);
		   } catch (NumberFormatException e) {
			   devise = serviceDevise.getDeviseByCode(codeOrId);
		   }
		   return devise; 
		   //NB if MyNotFoundException , automatic MyNotFoundExceptionMapper translate it 
		   //into Response with dto.ApiError and Http Status 404
		}
	
	
	@GET
	@Path("/enXml")
	@Produces("text/xml")
	public List<Devise> getDevisesXml(){
		return serviceDevise.getAllDevises();
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
	//@JWTTokenNeeded
	public Devise postDevise(Devise devise) {
		return serviceDevise.insertDevise(devise);
		//NB if MyConflictException , automatic MyConflictExceptionMapper translate it 
		//into Response with dto.ApiError and Http Status 409
	}
	
	@PUT
	@Path("/{id}")
	//PUT (MISE A JOUR COTE SERVEUR)
	//http://localhost:8080/webapp_rest/rest/my-api/devise/5
	//avec dans la partie invisible "body" de la requête
	//{ "id" : 5 , "code" : "MS1" , "nom" : "Monnaie_Singe1" , "change" : 3434567.6 }
	@Consumes("application/json")
	public Response putDevise(@PathParam("id") Long id,Devise devise) {
		if(id!=null) {
			devise.setId(id);
		}
		Devise deviseModifiee = serviceDevise.updateDevise(devise);
	    //NB if MyNotFoundException , automatic MyNotFoundExceptionMapper translate it 
		//into Response with dto.ApiError and Http Status 404
		
		//return Response.status(Status.NO_CONTENT).build();  //OK sans details
		return Response.status(Status.OK).entity(deviseModifiee).build(); //OK avec details
	}
	
	@DELETE
	@Path("/{id}")
	@JWTTokenNeeded
	//SUPPRESSION COTE SERVEUR
	//http://localhost:8080/webapp_rest/rest/my-api/devise/5
	public Response deleteDeviseById(@PathParam("id") Long id) {
		    serviceDevise.removeDevise(id);
		    //NB if MyNotFoundException , automatic MyNotFoundExceptionMapper translate it 
			//into Response with dto.ApiError and Http Status 404
		    
			return Response.status(Status.NO_CONTENT).build();//suppression OK sans détails 
	}
	
	

}

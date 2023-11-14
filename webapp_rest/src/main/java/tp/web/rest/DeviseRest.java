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
import tp.web.service.ServiceDevise;
import tp.web.util.JWTTokenNeeded;

//api-bank/compte
//        /virement
//api-finance/devise
//api-xxx/produit
//        /category
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
	@Path("/{code}")
	//RECHERCHE UNIQUE RETOURNANT EVENTUELLEMENT NOT_FOUND
	//http://localhost:8080/webapp_rest/rest/my-api/devise/EUR
	//http://localhost:8080/webapp_rest/rest/my-api/devise/1
	
	/*
	public Devise getDeviseByCode(@PathParam("code") String code) {
	   return serviceDevise.getDeviseByCode(code);
	}
	*/
	public Response getDeviseByCodeOrId(@PathParam("code") String code) {
		   Devise devise = null;
		   //avec code ici au sens "codeOrId"
		   try {
			   Long id=Long.parseLong(code);
				devise = serviceDevise.getDeviseById(id);
		   } catch (NumberFormatException e) {
			   devise = serviceDevise.getDeviseByCode(code);
		   }
		   
		   if(devise==null)
			   return Response.status(Status.NOT_FOUND).build();
		   else
		       return Response.status(Status.OK).entity(devise).build();
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
	@JWTTokenNeeded
	public Devise postDevise(Devise devise) {
		return serviceDevise.insertDevise(devise);
	}
	
	@PUT
	@Path("/{id}")
	//PUT (MISE A JOUR VERS LE SERVEUR)
	//http://localhost:8080/webapp_rest/rest/my-api/devise/5
	//avec dans la partie invisible "body" de la requête
	//{ "id" : 5 , "code" : "MS1" , "nom" : "Monnaie_Singe1" , "change" : 3434567.6 }
	@Consumes("application/json")
	public Response putDevise(@PathParam("id") Long id,Devise devise) {
		if(id!=null) {
			devise.setId(id);
		}
		Devise deviseAModifier = serviceDevise.getDeviseById(id);
		if(deviseAModifier==null)
			return Response.status(Status.NOT_FOUND).build(); //pas trouver devise à modifier
		Devise deviseModifiee = serviceDevise.updateDevise(devise);
		//return Response.status(Status.NO_CONTENT).build();  //OK sans details
		return Response.status(Status.OK).entity(deviseModifiee).build(); //OK avec details
	}
	
	@DELETE
	@Path("/{id}")
	@JWTTokenNeeded
	//SUPPRESSION VERS SERVEUR
	//http://localhost:8080/webapp_rest/rest/my-api/devise/5
	public Response deleteDeviseById(@PathParam("id") Long id) {
		Devise deviseAsupprimer = serviceDevise.getDeviseById(id);
		if(deviseAsupprimer==null)
			return Response.status(Status.NOT_FOUND).build(); //pas trouver devise à supprimer
		else
		{
			try {
				serviceDevise.removeDevise(id);
				return Response.status(Status.NO_CONTENT).build();//suppression OK sans détails 
			} catch (Exception e) {
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();//erreur technique ou ...
			}
		}
	}
	
	

}

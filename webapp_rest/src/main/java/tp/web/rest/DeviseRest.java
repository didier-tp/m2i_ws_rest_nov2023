package tp.web.rest;


import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

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
	public Devise getDeviseByCode(@PathParam("code") String code) {
	   return serviceDevise.getDeviseByCode(code);
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

}

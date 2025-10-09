package tp.springJersey.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import tp.springJersey.dto.Produit;

//@Component pas nécessaire si register(ProduitRestController) dans classe héritant de ResourceConfig
@Path("/api-produits/v1/produits") //partie de l'url liée àà l'ensemble de la classe
@Produces("application/json") //pour transformer java en json sur réponses fabriquées
public class ProduitRestController {
	
	private List<Produit> listeProduits = new ArrayList<>();
	
	public ProduitRestController() {
		listeProduits.add(new Produit(1L,"stylo",3.3));
		listeProduits.add(new Produit(2L,"gomme",4.3));
		listeProduits.add(new Produit(3L,"classeur",5.3));
		System.out.println("ProduitRestController instance:" + this.toString() );
	}
	
	@GET
	@Path("/{id}")
	//URL d'appel : http://localhost:8080/appliRest/api-produits/v1/produits/1
	public Response getProduitByNum(@PathParam("id")Long numero) {
		Optional<Produit> optionalProd = listeProduits.stream()
				        .filter((p)->p.getNum()==numero)
				        .findFirst();
		if(optionalProd.isEmpty()) {
			//renvoyer message d'erreur avec code 404 NOT_FOUND
			return Response.status(Status.NOT_FOUND).entity("pas trouvé").build();
		}else {
			//renvoyer produit trouvé avec code 200 OK
			return Response.status(Status.OK).entity(optionalProd.get()).build();
		}
	}
	
	@GET
	@Path("/all")
	//URL d'appel : http://localhost:8080/appliRest/api-produits/v1/produits/all
	//ou            http://localhost:8080/appliRest/produits/all
	public List<Produit> getAllProduits() {
		    return listeProduits;
	}
	
	@GET
	@Path("")
	//URL d'appel : http://localhost:8080/appliRest/api-produits/v1/produits
	//ou            http://localhost:8080/appliRest/produits
	//ou            http://localhost:8080/appliRest/produits?prixMaxi=4.5
	//ou            http://localhost:8080/appliRest/produits?prixMini=2
	//ou            http://localhost:8080/appliRest/produits?prixMaxi=4.5&prixMini=2
	public List<Produit> getProduitsByCriteria(@QueryParam("prixMaxi") Double prixMaxi,
			                                   @QueryParam("prixMini") Double prixMini) {
		/* if(prixMaxi==null && prixMini==null)
		    return listeProduits;
		 
		else { */
			final double prixMax=prixMaxi!=null?prixMaxi:999999999999.0;
			final double prixMin=prixMini!=null?prixMini:0.0;
			return listeProduits.stream()
			        .filter((p)-> p.getPrix()<=prixMax && p.getPrix() >=prixMin)
			        .collect(Collectors.toList()); //ou bien .toList() direct avec jdk17
		/* } */
		
	}
	

}

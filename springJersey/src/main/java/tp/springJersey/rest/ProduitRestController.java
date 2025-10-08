package tp.springJersey.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
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
	public Produit getProduitByNum(@PathParam("id")Long numero) {
		return listeProduits.stream()
				        .filter((p)->p.getNum()==numero)
				        .findFirst().get();
	}
	
	@GET
	@Path("")
	//URL d'appel : http://localhost:8080/appliRest/api-produits/v1/produits
	public List<Produit> getProduitsByCriteria() {
		return listeProduits;
	}
	

}

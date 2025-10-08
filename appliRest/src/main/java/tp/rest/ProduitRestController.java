package tp.rest;

import java.util.ArrayList;
import java.util.List;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import tp.dto.Produit;

@Path("/produits") //partie de l'url
@Produces("application/json") //pour transformer java en json sur réponses fabriquées
public class ProduitRestController {
	
	private List<Produit> listeProduits = new ArrayList<>();
	
	public ProduitRestController() {
		listeProduits.add(new Produit(1L,"stylo",3.3));
		listeProduits.add(new Produit(2L,"gomme",4.3));
		listeProduits.add(new Produit(3L,"classeur",5.3));
	}
	
	@GET
	@Path("/{id}")
	//URL d'appel : http://localhost:8080/appliRest/produits/1
	public Produit getProduitByNum(@PathParam("id")Long numero) {
		return listeProduits.get(numero.intValue() -1);//en Tp !!!!
	}
	

}

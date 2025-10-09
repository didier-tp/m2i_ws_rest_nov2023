package tp.springJersey.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
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
	
	private static List<Produit> listeProduits = new ArrayList<>();
	
	public ProduitRestController() {
		if(listeProduits.isEmpty()) {
			listeProduits.add(new Produit(1L,"stylo",3.3));
			listeProduits.add(new Produit(2L,"gomme",4.3));
			listeProduits.add(new Produit(3L,"classeur",5.3));
		}
		System.out.println("ProduitRestController instance:" + this.toString() );
	}
	
	@GET
	@Path("/{id}")
	//URL d'appel : http://localhost:8080/appliRest/api-produits/v1/produits/1
	public Produit getProduitByNum(@PathParam("id")Long numero) {
		return listeProduits.stream()
				        .filter((p)->p.getNum()==numero)
				        .findFirst().get();  
		//NB: si .get() remonte une exception de type NoSuchElementException
		//et qu'un exceptionMapper a été enregistré,
		//une exception pas rattrapée explicitement par try/catch sera automatiquement transformée en 
		//Respone avec bon status et bon message
	}
	
	/*
	@GET
	@Path("/{id}")
	//URL d'appel : http://localhost:8080/appliRest/api-produits/v1/produits/1
	public Response getProduitByNum(@PathParam("id")Long numero) {
		Optional<Produit> optionalProd = listeProduits.stream()
				        .filter((p)->p.getNum()==numero)
				        .findFirst();
		if(optionalProd.isEmpty()) {
			//renvoyer message d'erreur avec code 404 NOT_FOUND
			return Response.status(Status.NOT_FOUND)
					.entity(new ApiError("produit pas trouvé pour id="+numero,
							             Status.NOT_FOUND.getStatusCode()))
					.build();
		}else {
			//renvoyer produit trouvé avec code 200 OK
			return Response.status(Status.OK).entity(optionalProd.get()).build();
		}
	}
	*/
	
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
	
	/*
	@POST
	@Path("")
	@Consumes("application/json")
	//    { "num" : 7 , "label" : "crayon" , "prix" : 2.8 } très rarement
	// ou { "num" : null , "label" : "crayon" , "prix" : 2.8 }
	// ou { "label" : "crayon" , "prix" : 2.8 }
	public Produit postProduit(Produit p){
		
		//simuler auto-incrémentation:
		int nouveauNum = this.listeProduits.size() + 1;
		p.setNum((long)nouveauNum);
		
		this.listeProduits.add(p);
		//dans une appli plus évoluée , save() et auto-incr
		return p; //on peut retourner l'entitée sauvegardée avec un id/num auto-incrémenté
	}
	*/
	
	@POST
	@Path("")
	@Consumes("application/json")
	//    { "num" : 7 , "label" : "crayon" , "prix" : 2.8 } très rarement
	// ou { "num" : null , "label" : "crayon" , "prix" : 2.8 }
	// ou { "label" : "crayon" , "prix" : 2.8 }
	public Response postProduit(Produit p){
		
		//simuler auto-incrémentation:
		int nouveauNum = this.listeProduits.size() + 1;
		p.setNum((long)nouveauNum);
		
		this.listeProduits.add(p);
		//dans une appli plus évoluée , save() et auto-incr
		
		//on peut retourner l'entitée sauvegardée avec un id/num auto-incrémenté
		return Response.status(Status.CREATED).entity(p).build(); 
		//NB: 201/CREATED un peu plus précis que 200/OK
	}
	
	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	//URL d'appel : http://localhost:8080/..../produits/7
	//    { "num" : 7 , "label" : "crayon noir" , "prix" : 12.8 } 
	public Response putProduit(@PathParam("id") Long id, Produit p){
        //.... mise à jour / update sur liste ou en base
		//retourner le code 204/NO_CONTENT ou bien 200/OK plus copie des données mises à jour
		return Response.status(Status.NO_CONTENT).build(); 
	}
	
	@DELETE
	@Path("/{id}")
	//URL d'appel : http://localhost:8080/..../produits/7
	public Response deleteProduit(@PathParam("id") Long id){
        //.... suppression sur liste ou en base
		//retourner le code 204/NO_CONTENT ou bien 200/OK plus un message "suppression bien effectuée"
		return Response.status(Status.NO_CONTENT).build();
	}
	

}

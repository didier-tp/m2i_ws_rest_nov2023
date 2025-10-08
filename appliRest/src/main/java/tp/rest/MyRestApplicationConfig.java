package tp.rest;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

//@ApplicationPath("/rest") 
@ApplicationPath("/api-produits/v1")
//partie "milieu" des URLs menant aux WS REST
//ex: http://localhost:8080/appliRest/rest/produits/1
// ou http://localhost:8080/appliRest/api-produits/v1/produits/1
public class MyRestApplicationConfig extends Application {

}

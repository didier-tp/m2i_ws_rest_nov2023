package tp.web.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;



@ApplicationPath("/rest")  //partie de l'URL qui mène aux WS REST
//après http://localhost:8080/webapp_rest et avant @Path des classes java
public class MyWSRestConfig extends Application {
    //classes des WS REST  sont quelquefois automatiquement découvertes avec JBoss récent


	
}

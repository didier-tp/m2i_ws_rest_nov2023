package tp.web.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest") //partie de l'URL qui mène aux WS REST
//après http://localhost:8080/webapp_rest et avant @Path des classes java
public class MyWSRestConfig extends Application {
    //classes des WS REST découvertes automatiquement avec JBoss récent
}

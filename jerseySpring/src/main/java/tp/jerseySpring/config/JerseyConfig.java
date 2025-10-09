package tp.jerseySpring.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.ApplicationPath;

@Configuration //indispensable
//@ApplicationPath("/rest") //part of url (in the middle), facultatif
public class JerseyConfig extends ResourceConfig {

    @PostConstruct
    public void init() {
        //register(ProduitRestController.class); //indispensable sauf si packages(...)
        //register(GenericExceptionMapper.class); //facultatif
        
        packages("tp.jerseySpring.rest");
        //register(OpenApiResource.class); //pour swagger
    }
}

package tp.springJersey.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

//import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.ApplicationPath;
import tp.springJersey.provider.GenericExceptionMapper;

@Configuration //indispensable
@ApplicationPath("/rest") //part of url (in the middle), facultatif
public class JerseyConfiguration extends ResourceConfig {

    @PostConstruct
    public void init() {
        //register(ProduitRestController.class); //indispensable sauf si packages(...)
        register(GenericExceptionMapper.class); //facultatif
        
        packages("tp.springJersey.rest");
        //register(OpenApiResource.class);
    }
}

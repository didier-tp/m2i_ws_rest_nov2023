package tp.springJersey.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.ApplicationPath;
import tp.springJersey.provider.NoSuchElementExceptionMapper;

@Configuration //indispensable
@ApplicationPath("/rest") //part of url (in the middle), facultatif
public class JerseyConfiguration extends ResourceConfig {
	

    @PostConstruct
    public void init() {
        //register(ProduitRestController.class); //indispensable sauf si packages(...)
        register(NoSuchElementExceptionMapper.class); //facultatif
        
        packages("tp.springJersey.rest");
        
        register(OpenApiResource.class);
        //OpenApiResource oar = new OpenApiResource();
        //register(oar);
        
        //expose API doc at openapi.json endpoint
        //http://localhost:8080/springJersey/rest/openapi.json
        //to explore: /springJersey/rest/openapi.json
        // with http://localhost:8080/springJersey/webjars/swagger-ui/index.html
        
        
    }
    
}

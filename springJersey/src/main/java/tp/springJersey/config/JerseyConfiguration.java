package tp.springJersey.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;
import jakarta.ws.rs.ApplicationPath;
import tp.springJersey.dto.provider.NoSuchElementExceptionMapper;

@Configuration //indispensable
@ApplicationPath("/rest") //part of url (in the middle), facultatif
public class JerseyConfiguration extends ResourceConfig {

    @PostConstruct
    public void init() {
        //register(ProduitRestController.class); //indispensable sauf si packages(...)
        register(NoSuchElementExceptionMapper.class); //facultatif
        
        packages("tp.springJersey.rest");
        //register(OpenApiResource.class);
    }
}

package tp.web.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import io.swagger.jaxrs.config.BeanConfig;

@ApplicationPath("/rest") //partie de l'URL qui mène aux WS REST
//après http://localhost:8080/webapp_rest et avant @Path des classes java
public class MyWSRestConfig extends Application {
    //classes des WS REST  sont quelquefois automatiquement découvertes avec JBoss récent
	
	
	public MyWSRestConfig() {
		  BeanConfig beanConfig = new BeanConfig();
		  beanConfig.setVersion("1.0.0");
		  beanConfig.setBasePath("/webapp_rest/rest/my-api");
		  beanConfig.setResourcePackage("io.swagger.resources,tp.web.rest");
		  beanConfig.setScan(true); 
	}

	
	/*
	@Override
	public Set<Class<?>> getClasses() {
	    Set<Class<?>> resources = new HashSet();
	    resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
	    resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);
	    return resources;
	    }
	    */
	
	
}

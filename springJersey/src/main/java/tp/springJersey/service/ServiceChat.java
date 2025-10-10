package tp.springJersey.service;

import org.springframework.stereotype.Service;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import tp.springJersey.dto.CatFact;

@Service
public class ServiceChat {
	
	Client jaxrs2client = ClientBuilder.newClient();
	
	public String getServiceName() {
		return "ServiceName=ServiceProduit";
	}
	
	public CatFact getRecentCatFact() {
		String catfactUrl = "https://catfact.ninja/fact";
		WebTarget catFactTarget = jaxrs2client.target(catfactUrl);
		
		String jsonStringRes = catFactTarget.request(MediaType.APPLICATION_JSON)
		             .get().readEntity(String.class);
		System.out.println("reponse au format json_string:" + jsonStringRes);
		
		CatFact javaRes = catFactTarget.request(MediaType.APPLICATION_JSON)
	             .get().readEntity(CatFact.class);
		
		return javaRes;
	}

}

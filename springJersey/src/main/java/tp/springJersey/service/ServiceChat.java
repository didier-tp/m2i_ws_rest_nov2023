package tp.springJersey.service;

import org.springframework.stereotype.Service;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;

@Service
public class ServiceChat {
	
	Client jaxrs2client = ClientBuilder.newClient();
	
	public String getServiceName() {
		return "ServiceName=ServiceProduit";
	}

}

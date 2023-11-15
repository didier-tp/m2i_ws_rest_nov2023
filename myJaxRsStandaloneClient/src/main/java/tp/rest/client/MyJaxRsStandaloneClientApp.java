package tp.rest.client;

import java.util.Arrays;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tp.web.dto.Devise;

public class MyJaxRsStandaloneClientApp {
	
	public static Client jaxrsClient = ClientBuilder.newClient();

	public static void main(String[] args) {
		simplePostCall();
		simpleGetCalls();
	}
	
	public static void simpleGetCalls() {
		String wsBaseUrl = "http://localhost:8080/webapp_rest/rest/my-api/devise";
		WebTarget euroTarget  = jaxrsClient.target(wsBaseUrl).path("EUR");
		String deviseEuroAsJsonString = euroTarget.request(MediaType.APPLICATION_JSON)
				                                  .get().readEntity(String.class);
        System.out.println("deviseEuroAsJsonString="+deviseEuroAsJsonString);	
        //résultat: deviseEuroAsJsonString={"id":1,"code":"EUR","nom":"Euro","change":1.0}
        
        Devise deviseEuro = euroTarget.request(MediaType.APPLICATION_JSON)
                .get().readEntity(Devise.class);
        System.out.println("deviseEuro="+deviseEuro.toString());	
        //résultat: deviseEuro=Devise [id=1, code=EUR, nom=Euro, change=1.0]
        
        WebTarget withChangeMiniTarget  = jaxrsClient.target(wsBaseUrl).queryParam("changeMini",1.01);
		String devisesAsJsonString = withChangeMiniTarget.request(MediaType.APPLICATION_JSON)
				                                         .get().readEntity(String.class);
        System.out.println("devisesAsJsonString="+devisesAsJsonString);
        
        Devise[] devisesArray = withChangeMiniTarget.request(MediaType.APPLICATION_JSON)
                                             .get(Devise[].class);
        System.out.println("devises="+  Arrays.asList(devisesArray).toString());
	}
	
	public static void simplePostCall() {
		
		Devise newDevise=new Devise(null,"MS","MonnaieSinge",123456.789);
		String wsBaseUrl = "http://localhost:8080/webapp_rest/rest/my-api/devise";
		WebTarget deviseTarget  = jaxrsClient.target(wsBaseUrl);
		Response resp = deviseTarget.request(MediaType.APPLICATION_JSON)
				                           .post(Entity.entity(newDevise, MediaType.APPLICATION_JSON_TYPE));
		if(resp.getStatus()==200/*OK*/) {
			Devise savedDevise=resp.readEntity(Devise.class);
			System.out.println("savedDevise="+savedDevise);
		}else {
			System.err.println(resp);
		}
	}

}

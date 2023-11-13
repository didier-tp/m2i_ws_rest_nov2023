package tp.web.rest;


import java.util.Arrays;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import tp.web.dto.LoginRequest;
import tp.web.dto.LoginResponse;
import tp.web.util.JwtUtil;

@Path("my-api/login")
@Produces("application/json")
@Singleton //javax.inject.Singleton
public class LoginRest {
	
	@POST
	@Path("")
	//http://localhost:8080/webapp_rest/rest/my-api/login 
	//avec dans la partie invisible "body" de la requête
	//{  "username" : "user1" , "password" : "pwduser1" 
	//{  "username" : "user1" , "password" : "wrongpwd"
	//{  "username" : "admin1" , "password" : "pwdadmin1" }
	@Consumes("application/json")
	public LoginResponse postDevise(LoginRequest loginRequest) {
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setUsername(loginRequest.getUsername());
		if(loginRequest.getPassword().equals("pwd"+loginRequest.getUsername())) {
			loginResponse.setStatus(true);
			loginResponse.setMessage("sucessfull login");
			
			String role = loginRequest.getUsername().startsWith("admin")?"admin":"user";
			loginResponse.setRole(role);
		    String jwtSecret = JwtUtil.DEFAULT_JWT_SECRET;
			String jwtToken = JwtUtil.buildToken(loginRequest.getUsername(), 
					                             60*15*1000, 
					                             jwtSecret, Arrays.asList(role));
			loginResponse.setToken(jwtToken);
		}else {
			loginResponse.setStatus(false);
			loginResponse.setMessage("login fail");
		}
		return loginResponse;
	}
	

}

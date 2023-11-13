package tp.web.dto;

public class LoginResponse {
	private String username;
	private String role;
	private Boolean status;
	private String message;
	private String token;
	
	public LoginResponse() {
		super();
	}
	

	public LoginResponse(String username, String role, Boolean status, String message, String token) {
		super();
		this.username = username;
		this.role = role;
		this.status = status;
		this.message = message;
		this.token = token;
	}


	@Override
	public String toString() {
		return "LoginResponse [username=" + username + ", role=" + role + ", status=" + status + ", message=" + message
				+ ", token=" + token + "]";
	}



	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}

package tp.springJersey.dto;

import java.util.Date;

public class ApiError {
	private String message;
	private Integer status;
	private String details;
	private String timestamp;
	
	public ApiError(String message, Integer status, String details) {
	this.message = message; this.status = status;
	this.details = details; this.timestamp = (new Date()).toString();
	}
	
	public ApiError(String message, Integer status) {this(message,status,null);}
	public ApiError(String message) {this(message,500); }
	public ApiError() { this("Internal Server Error"); }
	
	@Override
	public String toString() {
	return "ApiError [message=" + message + ", status=" + status + ", details=" + details + ", timestamp="+ timestamp + "]";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	
}

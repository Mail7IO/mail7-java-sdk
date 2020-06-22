package io.mail7.sdk.util;

public class ErrorResponse {
	private String message;
	private String status;

	/**
	 * 
	 * @return The message
	 */
	public String getStatus() {
		return status == null ? status : "";
	}

	/**
	 * 
	 * @param message The Message
	 */
	public void setStatus(final String status) {
		this.status = status;
	}
	
	/**
	 * 
	 * @return The message
	 */
	public String getMessage() {
		return message == null ? message : "";
	}

	/**
	 * 
	 * @param message The Message
	 */
	public void setMessage(final String message) {
		this.message = message;
	}
}

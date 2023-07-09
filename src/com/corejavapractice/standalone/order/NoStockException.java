package com.corejavapractice.standalone.order;

public class NoStockException extends Exception {

	private String errorCode;
	private String message;
	public NoStockException(String errorCode, String message) {
		super();
		this.errorCode = errorCode;
		this.message = message;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}

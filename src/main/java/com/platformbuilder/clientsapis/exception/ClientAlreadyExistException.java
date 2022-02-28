package com.platformbuilder.clientsapis.exception;

/**
 * 
 * Exception when trying to save an existing client -with the same clientId
 * 
 * @author Martin Dymenstein
 *
 */
@SuppressWarnings("serial")
public class ClientAlreadyExistException extends BaseClassException {

	private final String clientId;
	
	public ClientAlreadyExistException(String clientId) {
		this.clientId = clientId;
	}
	
	@Override
	protected String getCode() {
		return "CP-1";
	}

	public String getClientId() {
		return clientId;
	}

}

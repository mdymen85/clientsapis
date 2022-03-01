package com.platformbuilder.clientsapis.exception;

@SuppressWarnings("serial")
public class ClientNotFoundException extends BaseClassException  {

	private final String clientId;
	
	public ClientNotFoundException(String clientId) {
		this.clientId = clientId;
	}
	
	@Override
	protected String getCode() {		
		return "CP-2";
	}
	
	public String getClientId() {
		return this.clientId;
	}

}

package com.platformbuilder.clientsapis.exception;

@SuppressWarnings("serial")
public abstract class BaseClassException extends RuntimeException {

	protected abstract String getCode();
	
}

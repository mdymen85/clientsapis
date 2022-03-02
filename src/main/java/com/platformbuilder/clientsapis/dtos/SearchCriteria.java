package com.platformbuilder.clientsapis.dtos;

import lombok.Data;

@Data
public class SearchCriteria {

	private String name;
	private String clientId;
	private AgeCriteria ageCriteria;
	
	public boolean isNameNull() {
		return name == null;
	}
	
	public boolean isClientIdNull() {
		return clientId == null;
	}
	
	public boolean isAgeCriteriaNull() {
		return ageCriteria == null;
	}
	
}

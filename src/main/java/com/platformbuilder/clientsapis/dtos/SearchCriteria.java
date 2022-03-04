package com.platformbuilder.clientsapis.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class SearchCriteria {

	private String name;
	private String clientId;
	private final Integer age;
	private final Operation operation;
	
	public boolean isNameNull() {
		return name == null;
	}
	
	public boolean isClientIdNull() {
		return clientId == null;
	}
	
	public boolean isAgeCriteriaNull() {
		return age == null;
	}
	
}

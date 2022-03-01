package com.platformbuilder.clientsapis.dtos;

import lombok.Data;

@Data
public class SearchCriteria {

	private String name;
	private String clientId;
	private int age;
	private Operation operation;
	
}

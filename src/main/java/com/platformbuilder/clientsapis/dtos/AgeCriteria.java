package com.platformbuilder.clientsapis.dtos;

import lombok.Data;

@Data
public class AgeCriteria {

	private final int age;
	private final Operation operation;
	
	public AgeCriteria(int age, Operation operation) {
		this.age = age;
		this.operation = operation;
	}
	
 }

package com.platformbuilder.clientsapis.domain;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
public class ClientDomain {
	
	@Autowired
	private List<IDomainValidations> validations;

	private final String name;
	private final String clientId;
	private final Integer age;
	
	public ClientDomain() {
		this.name = null;
		this.clientId = null;
		this.age = null;
	}
	
	@Builder
	public ClientDomain(String name, String clientId, Integer age) {
		this.name = name;
		this.clientId = clientId;
		this.age = age;
		
		validations.stream().forEach(v -> v.validation(this));
	}

}

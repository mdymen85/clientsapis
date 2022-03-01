package com.platformbuilder.clientsapis.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {
	
	@NotNull
	private String clientId;
	
	@NotNull
	private String name;
	
	@NotNull
	@Max(121)
	private Integer age;
	
}

package com.platformbuilder.clientsapis.dtos;

import java.time.Instant;

import lombok.Data;

@Data
public class ResponseClientDTO extends ClientDTO {

	private Long id;
	
	private Instant created;		
	
}

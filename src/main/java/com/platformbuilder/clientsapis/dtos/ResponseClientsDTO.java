package com.platformbuilder.clientsapis.dtos;

import java.util.List;

import lombok.Data;

@Data
public class ResponseClientsDTO {

	private final List<ResponseClientDTO> clients;
	
	public ResponseClientsDTO(List<ResponseClientDTO> clients) {
		this.clients = clients;
	}
	
}

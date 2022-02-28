package com.platformbuilder.clientsapis.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.platformbuilder.clientsapis.dtos.ClientDTO;
import com.platformbuilder.clientsapis.service.IClientService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class ClientController {

	private final IClientService clientService;
	
	@RequestMapping(value = "/v1/client", method = RequestMethod.POST)
	public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO clientDTO) {
		
		var clientResponse = this.clientService.create(clientDTO);
		
		return new ResponseEntity<ClientDTO>(clientResponse, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/v1/client/{clientId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(String clientId, @RequestBody ClientDTO clientDTO) {
		
		this.clientService.update(clientId, clientDTO);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
 	
	@RequestMapping(value = "/v1/client/{clientId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(String clientId) {
		
		this.clientService.delete(clientId);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}

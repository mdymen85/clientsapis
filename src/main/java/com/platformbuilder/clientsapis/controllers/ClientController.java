package com.platformbuilder.clientsapis.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.platformbuilder.clientsapis.dtos.ClientDTO;
import com.platformbuilder.clientsapis.dtos.ResponseClientDTO;
import com.platformbuilder.clientsapis.dtos.ResponseClientsDTO;
import com.platformbuilder.clientsapis.service.interfaces.IClientService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
//http://localhost:8080/swagger-ui/#/
public class ClientController {

	private final IClientService clientService;
	
	@RequestMapping(value = "/v1/client", method = RequestMethod.POST)
	public ResponseEntity<ResponseClientDTO> create(@RequestBody ClientDTO clientDTO) {
		
		log.info("Starting client creation {}.", clientDTO);
		
		var clientResponse = this.clientService.create(clientDTO);
		
		log.info("Client {} created.", clientResponse);
		
		return new ResponseEntity<ResponseClientDTO>(clientResponse, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/v1/client/{clientId}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseClientDTO> update(@PathVariable String clientId, @RequestBody ClientDTO clientDTO) {
		
		log.info("Starting update for client {}.", clientDTO);
		
		var clientResponse = this.clientService.update(clientId, clientDTO);
		
		log.info("Update for client {} done successfully.", clientResponse);
		
		return new ResponseEntity<ResponseClientDTO>(clientResponse, HttpStatus.OK);
	}
 	
	@RequestMapping(value = "/v1/client/{clientId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String clientId) {
		
		log.info("Starting delete for client {}.", clientId);
		
		this.clientService.delete(clientId);
		
		log.info("Client {} deleted successfully.", clientId);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/v1/client", method = RequestMethod.GET)
	public ResponseEntity<ResponseClientsDTO> get() {
		
		log.info("Loading all clients...");
		
		ResponseClientsDTO clients = this.clientService.load();
		
		return new ResponseEntity<ResponseClientsDTO>(clients, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/v1/client/page/{page}", method = RequestMethod.GET)
	public ResponseEntity<ResponseClientsDTO> getByPage(@PathVariable Integer page) {
		
		log.info("Loading all clients from the page {}.", page);
		
		ResponseClientsDTO clients = this.clientService.load(page);
		
		return new ResponseEntity<ResponseClientsDTO>(clients, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/v1/client/{clientId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseClientDTO> get(@PathVariable String clientId) {
		
		log.info("Loading client {}", clientId);
		
		var client = this.clientService.load(clientId);
		
		log.info("Client {} loaded successfully.", client);
		
		return new ResponseEntity<ResponseClientDTO>(client, HttpStatus.OK);
	}
}

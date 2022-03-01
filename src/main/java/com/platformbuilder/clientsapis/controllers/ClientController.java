package com.platformbuilder.clientsapis.controllers;

import java.util.List;

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
import com.platformbuilder.clientsapis.dtos.SearchCriteria;
import com.platformbuilder.clientsapis.service.interfaces.IClientService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class ClientController {

	private final IClientService clientService;
	
	@RequestMapping(value = "/v1/client", method = RequestMethod.POST)
	public ResponseEntity<ResponseClientDTO> create(@RequestBody ClientDTO clientDTO) {
		
		var clientResponse = this.clientService.create(clientDTO);
		
		return new ResponseEntity<ResponseClientDTO>(clientResponse, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/v1/client/{clientId}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable String clientId, @RequestBody ClientDTO clientDTO) {
		
		this.clientService.update(clientId, clientDTO);
		
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
 	
	@RequestMapping(value = "/v1/client/{clientId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable String clientId) {
		
		this.clientService.delete(clientId);
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/v1/client", method = RequestMethod.GET)
	public ResponseEntity<ResponseClientsDTO> get() {
		
		ResponseClientsDTO clients = this.clientService.load();
		
		return new ResponseEntity<ResponseClientsDTO>(clients, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/v1/client/{clientId}", method = RequestMethod.GET)
	public ResponseEntity<ResponseClientDTO> get(@PathVariable String clientId) {
		
		var client = this.clientService.load(clientId);
		
		return new ResponseEntity<ResponseClientDTO>(client, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/v1/client/{clientId}/criteria", method = RequestMethod.GET) 
	public ResponseEntity<ResponseClientsDTO> get(@PathVariable String clientId, @RequestBody SearchCriteria criteria) {
		
		var clients = this.clientService.load(clientId, criteria);
		
		return new ResponseEntity<ResponseClientsDTO>(clients, HttpStatus.OK);
		
	}
}

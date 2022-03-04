package com.platformbuilder.clientsapis.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.platformbuilder.clientsapis.dtos.ResponseClientsDTO;
import com.platformbuilder.clientsapis.dtos.SearchCriteria;
import com.platformbuilder.clientsapis.service.interfaces.IClientCriteriaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class ClientCriteriaController {

	private final IClientCriteriaService clientCriteriaService;
	
	@RequestMapping(value = "/v1/client/{clientId}/criteria", method = RequestMethod.GET) 
	public ResponseEntity<ResponseClientsDTO> get(@PathVariable String clientId, @RequestBody SearchCriteria criteria) {
		
		log.info("Searching for information by criteria {} for the client {}.", criteria, clientId);
		
		var clients = this.clientCriteriaService.load(clientId, criteria);
		
		log.info("Returning clients information with the criteria {} for the client {}.", criteria, clientId);
		
		return new ResponseEntity<ResponseClientsDTO>(clients, HttpStatus.OK);
		
	}
	
}

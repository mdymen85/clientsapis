package com.platformbuilder.clientsapis.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.platformbuilder.clientsapis.dtos.ResponseClientDTO;
import com.platformbuilder.clientsapis.dtos.ResponseClientsDTO;
import com.platformbuilder.clientsapis.dtos.SearchCriteria;
import com.platformbuilder.clientsapis.entities.Client;
import com.platformbuilder.clientsapis.repository.ClientRepository;
import com.platformbuilder.clientsapis.service.interfaces.IClientCriteriaService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientCriteriaService implements IClientCriteriaService {
	
	private final ClientRepository clientRepository;
	private final MapperService mapperService;
	
	@Override
	public ResponseClientsDTO load(String clientId, SearchCriteria criteria) {
		QuerySpecifications query = new QuerySpecifications(criteria);
		
		List<Client> clients = this.clientRepository.findAll(query);
		
		List<ResponseClientDTO> responseClients = clients.stream()
				.map(c -> this.mapperService.toResponseClientDTO(c))
				.collect(Collectors.toList());
		
		return new ResponseClientsDTO(responseClients);
	}

}

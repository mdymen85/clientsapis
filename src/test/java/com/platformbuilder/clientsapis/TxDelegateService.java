package com.platformbuilder.clientsapis;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.platformbuilder.clientsapis.dtos.ClientDTO;
import com.platformbuilder.clientsapis.dtos.ResponseClientDTO;
import com.platformbuilder.clientsapis.entities.Client;
import com.platformbuilder.clientsapis.repository.ClientRepository;
import com.platformbuilder.clientsapis.service.MapperService;

import lombok.RequiredArgsConstructor;

/**
 * 
 * A delegate class that opens a new transaction to create new clients.
 * The purpose is to commit after leaving the {@code create()} method
 * in order to use that client inside the test method without having
 * transaction issues.
 * 
 * @author Martin Dymenstein
 *
 */
@Component
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TxDelegateService {

	private final ClientRepository clientRepository;
	
	public Client create(Client clientEntity) {
		return this.clientRepository.save(clientEntity);
	}

	public Client changeName(String name, ResponseClientDTO response) {
		var client = this.clientRepository.findByClientId(response.getClientId()).get();
		client.setName(name);
		return this.clientRepository.save(client);		
	}
	
}

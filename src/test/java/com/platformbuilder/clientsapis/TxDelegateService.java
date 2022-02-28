package com.platformbuilder.clientsapis;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.platformbuilder.clientsapis.repository.ClientRepository;
import com.platformbuilder.clientsapis.repository.entities.ClientEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TxDelegateService {

	private final ClientRepository clientRepository;
	
	public ClientEntity create(ClientEntity clientEntity) {
		return this.clientRepository.save(clientEntity);
	}
	
}

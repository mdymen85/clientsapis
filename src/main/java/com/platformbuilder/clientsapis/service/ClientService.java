package com.platformbuilder.clientsapis.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.platformbuilder.clientsapis.domain.ClientDomain;
import com.platformbuilder.clientsapis.dtos.ClientDTO;
import com.platformbuilder.clientsapis.exception.ClientAlreadyExistException;
import com.platformbuilder.clientsapis.repository.ClientRepository;
import com.platformbuilder.clientsapis.repository.entities.ClientEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class ClientService implements IClientService {

	private final ModelMapper mapper;
	private final ClientRepository clientRepository;
	
	/**
	 * Create a new client. If the client already exists with the same values, will return it.
	 * 
	 * @param clientDTO DTO with the client information.
	 * @exception ClientAlreadyExistException if the clientId already exists but with different data.
	 */
	@Override
	public ClientDTO create(ClientDTO clientDTO) {
		
		var clientDomain = mapper.map(clientDTO, ClientDomain.class);	
		
		var clientEntity = mapper.map(clientDomain, ClientEntity.class);		
		
		var optClientSaved = clientRepository.findByClientId(clientDTO.getClientId());
		
		if (existsAndIsEquals(optClientSaved, clientEntity)) {
			throw new ClientAlreadyExistException(clientDTO.getClientId());
		}
		
		//idempotency
		else if (optClientSaved.isPresent()) {
			return mapper.map(optClientSaved.get(), ClientDTO.class);
		}
		
		var createdClientEntity = this.save(clientDomain);
		
		clientDTO.setId(createdClientEntity.getId());
		
		return clientDTO;
		
	}
	
	@Override
	public void delete(String clientId) {
		
		clientRepository.deleteByClientId(clientId);
		
	}


	@Override
	public ClientDTO update(String clientId, ClientDTO clientDTO) {
		
		var optionalClient = this.clientRepository.findByClientId(clientId);
		
		if (optionalClient.isEmpty()) {
			
		}
		
		var clientEntity = optionalClient.get();
		
		
		return null;
		
	}


	@Override
	public ClientDTO load(String number) {
		return mapper.map(this.clientRepository.findByClientId(number).get(), ClientDTO.class);
		
	}
	
	private boolean existsAndIsEquals(Optional<ClientEntity> optClientEntityLoaded, ClientEntity clientEntityForSave) {
		return optClientEntityLoaded.isPresent() && !optClientEntityLoaded.get().equals(clientEntityForSave);
	}


	private ClientEntity save(ClientDomain clientDomain) {
		
		var clientEntity = mapper.map(clientDomain, ClientEntity.class);		
		return clientRepository.save(clientEntity);
		
	}


}

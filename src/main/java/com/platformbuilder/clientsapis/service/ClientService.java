package com.platformbuilder.clientsapis.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.platformbuilder.clientsapis.domain.ClientDomain;
import com.platformbuilder.clientsapis.dtos.ClientDTO;
import com.platformbuilder.clientsapis.exception.ClientAlreadyExistException;
import com.platformbuilder.clientsapis.exception.ClientNotFoundException;
import com.platformbuilder.clientsapis.repository.ClientRepository;
import com.platformbuilder.clientsapis.repository.entities.ClientEntity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RequiredArgsConstructor
public class ClientService implements IClientService {

	private final ClientRepository clientRepository;
	private final IMapperService mapperService;
	private final ModelMapper mapper;
	
	@Override
	public ClientDTO create(ClientDTO clientDTO) {
		
		var clientDomain = mapperService.toClientDomain(clientDTO);
						
		var clientEntity = mapperService.toClientEntity(clientDomain);
		
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
		
		this.findByClientIdOrError(clientId);
		clientRepository.deleteByClientId(clientId);
		
	}


	@Override
	public ClientDTO update(String clientId, ClientDTO clientDTO) {
		
//		var clientEntity = this.findByClientIdOrError(clientId);
		
		//clientEntity
		
		
		return null;
		
	}


	@Override
	public ClientDTO load(String clientId) {
//		var clientEntity = this.findByClientIdOrError(clientId);
//		return mapper.map(clientEntity, ClientDTO.class);
		return null;
	}
	
	private boolean existsAndIsEquals(Optional<ClientEntity> optClientEntityLoaded, ClientEntity clientEntityForSave) {
		return optClientEntityLoaded.isPresent() && !optClientEntityLoaded.get().equals(clientEntityForSave);
	}


	private ClientEntity save(ClientDomain clientDomain) {
		
//		var clientEntity = mapper.map(clientDomain, ClientEntity.class);		
		return null;
//		clientRepository.save(clientEntity);
		
	}
	
	/**
	 * Return the {@link ClientEntity} Client database Entity by clientId
	 * 
	 * @param clientId client unique identificator
	 * @return the Client database Entity by clientId
	 * @exception ClientNotFoundException
	 */
	private ClientEntity findByClientIdOrError(String clientId) {
//		var optClientEntity = this.clientRepository.findByClientId(clientId);
//		
//		if (optClientEntity.isEmpty()) {
//			throw new ClientNotFoundException(clientId);
//		}
//		
//		return optClientEntity.get();
		
		return null;
	}
	


}

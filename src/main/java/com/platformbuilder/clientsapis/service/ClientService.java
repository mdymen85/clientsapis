package com.platformbuilder.clientsapis.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.platformbuilder.clientsapis.dtos.ClientDTO;
import com.platformbuilder.clientsapis.dtos.ResponseClientDTO;
import com.platformbuilder.clientsapis.dtos.ResponseClientsDTO;
import com.platformbuilder.clientsapis.entities.Client;
import com.platformbuilder.clientsapis.exception.ClientAlreadyExistException;
import com.platformbuilder.clientsapis.exception.ClientNotFoundException;
import com.platformbuilder.clientsapis.repository.ClientRepository;
import com.platformbuilder.clientsapis.service.interfaces.IClientService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RequiredArgsConstructor
public class ClientService implements IClientService {

	@Value("${application.page-size:5}")
	private int pageSizeClientRegister;
	
	private final ClientRepository clientRepository;
	private final MapperService mapperService;
	
	@Override
	public ResponseClientDTO create(ClientDTO clientDTO) {
		
		var client = mapperService.toClientEntity(clientDTO);					
		
		var optClientSaved = clientRepository.findByClientId(clientDTO.getClientId());
		
		if (existsAndIsNotEquals(optClientSaved, client)) {
			throw new ClientAlreadyExistException(clientDTO.getClientId());
		}
		
		//idempotency
		else if (optClientSaved.isPresent()) {
			return mapperService.toResponseClientDTO(optClientSaved.get());
		}
		
		var createdClientEntity = this.save(client);
		
		var response = this.mapperService.toResponseClientDTO(createdClientEntity);
		
		return response;

		
	}
	
	@Override
	public void delete(String clientId) {
		
		this.findByClientIdOrError(clientId);
		clientRepository.deleteByClientId(clientId);
		
	}


	@CacheEvict(value = "client", key = "#clientId")
	@Override
	public ResponseClientDTO update(String clientId, ClientDTO clientDTO) {
		
		var clientEntity = this.findByClientIdOrError(clientId);
		
		clientEntity.setAge(clientDTO.getAge());
		clientEntity.setClientId(clientDTO.getClientId());
		clientEntity.setName(clientDTO.getName());
		
		var newClient = this.clientRepository.save(clientEntity);								
		
		return mapperService.toResponseClientDTO(newClient);
		
	}


	@Cacheable(value = "client", key = "#clientId")
	@Override
	public ResponseClientDTO load(String clientId) {
		var clientEntity = this.findByClientIdOrError(clientId);		
		return this.mapperService.toResponseClientDTO(clientEntity);
	}
	
	private boolean existsAndIsNotEquals(Optional<Client> optClientEntityLoaded, Client clientEntityForSave) {
		return optClientEntityLoaded.isPresent() && !optClientEntityLoaded.get().basicEquals(clientEntityForSave);
	}


	private Client save(Client client) {

		return clientRepository.save(client);
		
	}
	
	/**
	 * Return the {@link Client} database Entity by clientId
	 * 
	 * @param clientId client unique identification
	 * @return the Client database Entity by clientId
	 * @exception ClientNotFoundException
	 */
	private Client findByClientIdOrError(String clientId) {
		var optClientEntity = this.clientRepository.findByClientId(clientId);
		
		if (optClientEntity.isEmpty()) {
			throw new ClientNotFoundException(clientId);
		}
		
		return optClientEntity.get();

	}

	@Override
	public ResponseClientsDTO load() {
		List<Client> clients = this.clientRepository.findAll();
		
		return this.toResponseFromList(clients);	
	}

	@Override
	public ResponseClientsDTO load(Integer page) {
		List<Client> clients = this.clientRepository.findAll(PageRequest.of(page, pageSizeClientRegister));
		
		return this.toResponseFromList(clients);
	}
	
	private ResponseClientsDTO toResponseFromList(List<Client> clients) {
		List<ResponseClientDTO> responseClients = clients.stream()
				.map(c -> this.mapperService.toResponseClientDTO(c))
				.collect(Collectors.toList());
		
		return new ResponseClientsDTO(responseClients);
	}


}

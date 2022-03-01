package com.platformbuilder.clientsapis.service;

import com.platformbuilder.clientsapis.dtos.ClientDTO;
import com.platformbuilder.clientsapis.exception.ClientAlreadyExistException;
import com.platformbuilder.clientsapis.exception.ClientNotFoundException;

public interface IClientService {

	
	/**
	 * Create a new client. If the client already exists with the same values, will return it.
	 * 
	 * @param clientDTO DTO with the client information.
	 * @exception {@link ClientAlreadyExistException} Client if the clientId already exists but with different data.
	 */
	public ClientDTO create(ClientDTO clientDTO);
	
	/**
	 * Remove the client by his id
	 * 
	 * @param clientId 
	 * @exception {@link ClientNotFoundException}
	 */
	public void delete(String clientId);
	
	public ClientDTO update(String clientId, ClientDTO clientDTO);
	
	/**
	 * Load a client by his id
	 * 
	 * @param clientId
	 * @return {@link ClientNotFoundException}
	 */
	public ClientDTO load(String clientId);
	
	
}

package com.platformbuilder.clientsapis.service.interfaces;

import com.platformbuilder.clientsapis.dtos.ClientDTO;
import com.platformbuilder.clientsapis.dtos.ResponseClientDTO;
import com.platformbuilder.clientsapis.dtos.ResponseClientsDTO;
import com.platformbuilder.clientsapis.exception.ClientAlreadyExistException;
import com.platformbuilder.clientsapis.exception.ClientNotFoundException;

/**
 * 
 * Interface to communicate with controllers layer. Expose the methods for outside the application domian
 * and implements those interfaces methods inside de domain.
 * 
 * @author Martin Dymenstein
 *
 */
public interface IClientService {
	
	/**
	 * Create a new client. If the client already exists with the same values, will return it.
	 * 
	 * @param clientDTO DTO with the client information.
	 * @exception {@link ClientAlreadyExistException} Client if the clientId already exists but with different data.
	 */
	public ResponseClientDTO create(ClientDTO clientDTO);
	
	/**
	 * Remove the client by his id
	 * 
	 * @param clientId 
	 * @exception {@link ClientNotFoundException}
	 */
	public void delete(String clientId);
	
	/**
	 * Update a client by his clientId
	 * 
	 * @param clientId
	 * @param clientDTO
	 * @return The client updated
	 * @exception {@link ClientNotFoundException}
	 */
	public ResponseClientDTO update(String clientId, ClientDTO clientDTO);
	
	/**
	 * Load a client by his id
	 * 
	 * @param clientId
	 * @return 
	 * @exception {@link ClientNotFoundException}
	 */
	public ResponseClientDTO load(String clientId);

	/**
	 * Load all clients without any paging
	 * @return list of clients
	 */
	public ResponseClientsDTO load();

	/**
	 * Load clients by the page
	 * 
	 * @param page number
	 * @return list of clients at specific page
	 */
	public ResponseClientsDTO load(Integer page);
	
	
}

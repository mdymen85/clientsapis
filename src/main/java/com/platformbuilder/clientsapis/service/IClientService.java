package com.platformbuilder.clientsapis.service;

import com.platformbuilder.clientsapis.dtos.ClientDTO;

public interface IClientService {

	public ClientDTO create(ClientDTO clientDTO);
	public void delete(String clientId);
	public ClientDTO update(String clientId, ClientDTO clientDTO);
	public ClientDTO load(String clientId);
	
	
}

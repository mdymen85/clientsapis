package com.platformbuilder.clientsapis.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.platformbuilder.clientsapis.dtos.ClientDTO;
import com.platformbuilder.clientsapis.dtos.ResponseClientDTO;
import com.platformbuilder.clientsapis.entities.Client;

@Mapper(componentModel = "spring")
public abstract class MapperService {

	@Mapping(target = "name", source = "name")
	@Mapping(target = "age", source = "age")
	@Mapping(target = "clientId", source = "clientId")
	public abstract Client toClientEntity(ClientDTO clientDTO);

	@Mapping(target = "name", source = "name")
	@Mapping(target = "age", source = "age")
	@Mapping(target = "clientId", source = "clientId")
	@Mapping(target = "created", source = "created")
	@Mapping(target = "id", source = "id")
	public abstract ResponseClientDTO toResponseClientDTO(Client client);
	
}

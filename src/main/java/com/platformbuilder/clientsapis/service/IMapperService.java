package com.platformbuilder.clientsapis.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.platformbuilder.clientsapis.domain.ClientDomain;
import com.platformbuilder.clientsapis.dtos.ClientDTO;
import com.platformbuilder.clientsapis.repository.entities.ClientEntity;

@Mapper(componentModel = "spring")
public abstract class IMapperService {

	@Mapping(target = "name", source = "name")
	@Mapping(target = "age", source = "age")
	@Mapping(target = "clientId", source = "clientId")
	public abstract ClientDomain toClientDomain(ClientDTO clientDTO);

	@Mapping(target = "name", source = "name")
	@Mapping(target = "age", source = "age")
	@Mapping(target = "clientId", source = "clientId")
	public abstract ClientEntity toClientEntity(ClientDomain clientDomain);
	
}

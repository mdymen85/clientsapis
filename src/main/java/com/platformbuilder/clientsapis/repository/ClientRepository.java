package com.platformbuilder.clientsapis.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.platformbuilder.clientsapis.repository.entities.ClientEntity;

public interface ClientRepository extends CrudRepository<ClientEntity, Long> {

	void deleteByClientId(String clientI);

	Optional<ClientEntity> findByClientId(String clientId);

}

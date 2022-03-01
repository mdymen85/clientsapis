package com.platformbuilder.clientsapis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.platformbuilder.clientsapis.entities.Client;

public interface ClientRepository extends CrudRepository<Client, Long>, JpaSpecificationExecutor<Client> {

	void deleteByClientId(String clientI);

	Optional<Client> findByClientId(String clientId);
	
	List<Client> findAll(Pageable pageable);
	
	List<Client> findAll();
	
	

}

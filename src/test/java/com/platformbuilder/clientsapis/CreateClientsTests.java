package com.platformbuilder.clientsapis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.platformbuilder.clientsapis.dtos.ClientDTO;
import com.platformbuilder.clientsapis.exception.ClientAlreadyExistException;
import com.platformbuilder.clientsapis.repository.entities.ClientEntity;


@SpringBootTest
class CreateClientsTests extends MysqlContainerService {
    
	/**
	 * Scenario: creating new client.
	 * Expected: new client created with success 
	 */
	@Test
	void createClient() {
		
		var newClient = ClientEntity.builder()
				.age(25)
				.name("Martin")
				.clientId("XX1234")
				.build();
		
		var clientEntity = txDelegateService.create(newClient);
		
		var client = this.clientService.load(clientEntity.getClientId());
		
		assertEquals(newClient.getAge(), client.getAge());
		assertEquals(newClient.getClientId(), client.getClientId());
		assertEquals(newClient.getName(), client.getName());
		assertEquals(clientEntity.getId(), client.getId());
		
	}
	
	/**
	 * Scenario: Trying to create the same client, with the same clientId and different information, twice
	 * 
	 * Expected: {@link ClientAlreadyExistException}
	 */
	@Test
	void idempotentClientCreationError() {
		
		var newClient = ClientEntity.builder()
				.age(25)
				.name("Martin")
				.clientId("XX1234")
				.build();
		
		txDelegateService.create(newClient);
		
		try {
		
			this.clientService.create(mapper.map(newClient, ClientDTO.class));
			fail();
			
		}
		catch (ClientAlreadyExistException e) {
			assertTrue(true);
		}
		
	}
	
	/**
	 * Scenario: Trying to create the same client, with the same clientId and information, twice
	 * 
	 * Excepted: to receive the same object that already exists and not try to duplicate the client in the database
	 */
	@Test
	void idempotentClientCreationSuccess() {
		
		var newClient = ClientEntity.builder()
				.age(25)
				.name("Martin")
				.clientId("XX1237")
				.build();
		
		var clientEntity = txDelegateService.create(newClient);
		
		var clientDTO = this.clientService.create(mapper.map(newClient, ClientDTO.class));
		
		assertEquals(clientEntity.getAge(), clientDTO.getAge());
		assertEquals(clientEntity.getClientId(), clientDTO.getClientId());
		assertEquals(clientEntity.getName(), clientDTO.getName());
		assertEquals(clientEntity.getId(), clientDTO.getId());
		
	}
	
	

}

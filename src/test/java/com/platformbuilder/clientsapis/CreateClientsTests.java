package com.platformbuilder.clientsapis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.platformbuilder.clientsapis.exception.ClientAlreadyExistException;
import com.platformbuilder.clientsapis.service.MapperService;


@SpringBootTest
class CreateClientsTests extends AbstractClassTests {
	
	@Autowired
	private MapperService mapperService;
    
	/**
	 * Scenario: creating new client.
	 * Expected: new client created with success 
	 */
	@Test
	void createClient() {
		
		var newClient = ClientFactory.getClient();
		
		var clientEntity = txDelegateService.create(newClient);
		
		var client = this.clientService.load(clientEntity.getClientId());
		
		this.assertValidations(newClient, client);
		
	}
	
	/**
	 * Scenario: Trying to create the same client, with the same clientId and different information.
	 * 
	 * Expected: {@link ClientAlreadyExistException}
	 */
	@Test
	void idempotentClientCreationError() {
		
		var newClient = ClientFactory.getClient();
		
		txDelegateService.create(newClient);
		
		try {
		
			var client = mapperService.toResponseClientDTO(newClient);
			client.setName("Peloton");
			
			this.clientService.create(client);
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
		
		var newClient = ClientFactory.getClient();
		
		var clientEntity = txDelegateService.create(newClient);
		
		var clientDTO = this.clientService.create(mapperService.toResponseClientDTO(clientEntity));
		
		this.assertValidations(clientEntity, clientDTO);
		
	}
	
	

}

package com.platformbuilder.clientsapis;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.platformbuilder.clientsapis.exception.ClientNotFoundException;
import com.platformbuilder.clientsapis.repository.entities.ClientEntity;

@SpringBootTest
public class DeleteClientsTests extends MysqlContainerService {

	/**
	 * Scenario: create and then delete a client.
	 * 
	 * Expected: after deleting the client, try to load it and receive and exception
	 * confirming that the client is not longer exists.
	 */
	@Test
	public void deleteClient() {
		var newClient = ClientEntity.builder()
				.age(25)
				.name("Martin")
				.clientId("XX1235")
				.build();
		
		this.txDelegateService.create(newClient);
		
		this.clientService.delete(newClient.getClientId());
		
		try {
		
			this.clientService.load(newClient.getClientId());
			fail();
		
		} catch (ClientNotFoundException e) {
			assertTrue(true);
		}
	}
		
	@Test
	public void deleteUnexistingClient() {
		
		try {
			
			this.clientService.delete("X1");
			fail();
		
		} catch (ClientNotFoundException e) {
			assertTrue(true);
		}
		
	}
	
}

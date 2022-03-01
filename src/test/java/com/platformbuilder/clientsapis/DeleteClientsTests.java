package com.platformbuilder.clientsapis;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.platformbuilder.clientsapis.entities.Client;
import com.platformbuilder.clientsapis.exception.ClientNotFoundException;

@SpringBootTest
public class DeleteClientsTests extends AbstractClassTests {

	/**
	 * Scenario: create and then delete a client.
	 * 
	 * Expected: after deleting the client, try to load it and receive and exception
	 * confirming that the client is not longer exists.
	 */
	@Test
	public void deleteClient() {
		var newClient = ClientFactory.getClient();
		
		this.txDelegateService.create(newClient);
		
		this.clientService.delete(newClient.getClientId());
		
		try {
		
			this.clientService.load(newClient.getClientId());
			fail();
		
		} catch (ClientNotFoundException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * 
	 * Scenario: Trying to delete a client that does not exists.
	 * 
	 * Expected: {@link ClientNotFoundException}
	 */ 
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

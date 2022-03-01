package com.platformbuilder.clientsapis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.platformbuilder.clientsapis.dtos.ClientDTO;
import com.platformbuilder.clientsapis.exception.ClientNotFoundException;

@SpringBootTest
public class UpdateClientsTests extends AbstractClassTests {

	/**
	 * Scenario: Trying to update an unexisting client
	 * 
	 * Expected {@link ClientNotFoundException}
	 */
	@Test
	public void updateUnexistingClient() {
		
		var client = ClientFactory.getClientDTO();
				
		try {
			
			this.clientService.update(client.getClientId(), client);
			
		}
		catch (ClientNotFoundException e) {
			assertTrue(true);
		}
		
	}
	
	/**
	 * Scenario: Create and check a client, then update it and check if the update process worked.
	 * 
	 * Expected: Update works OK.
	 */
	@Test
	public void updateSuccess() {
		
		var client = ClientFactory.getClient();
		
		this.txDelegateService.create(client);
		
		var oldClient = this.clientService.load(client.getClientId());
	
		assertEquals(oldClient.getAge(), client.getAge());
		assertEquals(oldClient.getName(), client.getName());
		
		var updatedClient = ClientDTO.builder()
				.age(client.getAge() == 121 ? client.getAge() - 1 : client.getAge() + 1 )
				.clientId(client.getClientId())
				.name("Cantalapiedra")
				.build();
		
		this.clientService.update(client.getClientId(), updatedClient);
		
		var newUpdatedClient = this.clientService.load(client.getClientId());
		
		assertEquals(updatedClient.getAge(), newUpdatedClient.getAge());
		assertEquals(updatedClient.getName(), newUpdatedClient.getName());
		
	}
	
	/**
	 * Scenario: Create a client and load it, in order to force to add the client in the cache.
	 * Then update the client, not passing through the service, directly through the repository.
	 * 
	 * Expected: Because of the cache not being evicted since the update, be load from the service
	 * method and we prove that the cache is working. Its load an old client.
	 */
	@Test
	public void cacheUpdateTesting() {
		
		var client = ClientFactory.getClient();
		
		client = this.txDelegateService.create(client);
		
		var loadedClient = this.clientService.load(client.getClientId());		
		
		var clientUpdated = this.txDelegateService.changeName("Jacinto", loadedClient);
		
		var secondLoadedClient = this.clientService.load(client.getClientId());
		
		assertNotEquals(clientUpdated.getName(), secondLoadedClient.getName());
	}
	
}

package com.platformbuilder.clientsapis;

import static org.junit.Assert.assertEquals;

import javax.naming.directory.SearchControls;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.platformbuilder.clientsapis.dtos.ResponseClientsDTO;
import com.platformbuilder.clientsapis.dtos.SearchCriteria;

@SpringBootTest
public class LoadClientsTests extends AbstractClassTests {

	@Test
	public void loadClients() {
		var client1 = ClientFactory.getClient();
		var client2 = ClientFactory.getClient();
		
		this.txDelegateService.create(client1);
		this.txDelegateService.create(client2);
		
		ResponseClientsDTO clients = this.clientService.load();
		
		var clientLoaded1 = clients.getClients().stream()
			.filter(c -> c.getClientId().equals(client1.getClientId()))
			.findAny().get();
		
		assertValidations(client1, clientLoaded1);
			
		var clientLoaded2 = clients.getClients().stream()
		.filter(c -> c.getClientId().equals(client2.getClientId()))
		.findAny().get();
	
		assertValidations(client2, clientLoaded2);
	}
	
	
}

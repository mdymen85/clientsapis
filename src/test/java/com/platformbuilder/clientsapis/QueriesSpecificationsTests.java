package com.platformbuilder.clientsapis;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.platformbuilder.clientsapis.dtos.SearchCriteria;

@SpringBootTest
public class QueriesSpecificationsTests extends AbstractClassTests  {

	@Test
	public void loadByName() {
		var client1 = ClientFactory.getClient();
		
		this.txDelegateService.create(client1);
		
		var criteria = new SearchCriteria();
		criteria.setName(client1.getName());
		
		var response = this.clientCriteriaService.load(client1.getClientId(), criteria);
		
		assertValidations(client1, response.getClients().get(0));
		
	}
	
	@Test
	public void loadByClientId() {
		var client1 = ClientFactory.getClient();
		
		this.txDelegateService.create(client1);
		
		var criteria = new SearchCriteria();
		criteria.setClientId(client1.getClientId());
		
		var response = this.clientCriteriaService.load(client1.getClientId(), criteria);
		
		assertValidations(client1, response.getClients().get(0));
		
	}
	
	@Test
	public void loadByNameDoesntExists() {
		var client1 = ClientFactory.getClient();
		
		this.txDelegateService.create(client1);
		
		var criteria = new SearchCriteria();
		criteria.setName("Doesnt Exists");
		
		var response = this.clientCriteriaService.load(client1.getClientId(), criteria);
		
		assertTrue(response.getClients().size() == 0);
		
	}
	
	
}

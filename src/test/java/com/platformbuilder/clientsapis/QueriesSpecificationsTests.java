package com.platformbuilder.clientsapis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

import com.platformbuilder.clientsapis.dtos.AgeCriteria;
import com.platformbuilder.clientsapis.dtos.Operation;
import com.platformbuilder.clientsapis.dtos.SearchCriteria;

@SpringBootTest
public class QueriesSpecificationsTests extends AbstractClassTests  {

//	@Test
//	public void loadByName() {
//		var client1 = ClientFactory.getClient();
//		
//		this.txDelegateService.create(client1);
//		
//		var criteria = SearchCriteria.builder()
//				.name(client1.getName())
//				.build();
//		
//		var response = this.clientCriteriaService.load(client1.getClientId(), criteria);
//		
//		assertValidations(client1, response.getClients().get(0));
//		
//	}
//	
//	@Test
//	public void loadByClientId() {
//		var client1 = ClientFactory.getClient();
//		
//		this.txDelegateService.create(client1);
//		
//		var criteria = SearchCriteria.builder()
//				.clientId(client1.getClientId())
//				.build();
//		
//		var response = this.clientCriteriaService.load(client1.getClientId(), criteria);
//		
//		assertValidations(client1, response.getClients().get(0));
//		
//	}
//	
//	@Test
//	public void loadByNameDoesntExists() {
//		var client1 = ClientFactory.getClient();
//		
//		this.txDelegateService.create(client1);
//		
//		var criteria = SearchCriteria.builder()
//				.name("Doesnt Exists")
//				.build();
//		
//		var response = this.clientCriteriaService.load(client1.getClientId(), criteria);
//		
//		assertTrue(response.getClients().size() == 0);
//		
//	}
//	
//	@Test
//	public void loadByNameDoesntExistsButClientYes() {
//		var client1 = ClientFactory.getClient();
//		
//		this.txDelegateService.create(client1);
//		
//		var criteria = SearchCriteria.builder()
//				.name("Doesnt Exists")
//				.clientId(client1.getClientId())
//				.build();
//		
//		var response = this.clientCriteriaService.load(client1.getClientId(), criteria);
//		
//		assertTrue(response.getClients().size() == 0);
//		
//	}
//	
//	@Test
//	public void loadByUnder() {
//		var client1 = ClientFactory.getClient();
//		
//		this.txDelegateService.create(client1);
//		
//		var criteria = SearchCriteria.builder()
//				.age(130)
//				.clientId(client1.getClientId())
//				.operation(Operation.LESSTHAN)
//				.build();
//		
//		var response = this.clientCriteriaService.load(client1.getClientId(), criteria);
//			
//		assertTrue(response.getClients().size() == 1);
//		assertEquals(response.getClients().get(0).getClientId(), response.getClients().get(0).getClientId());
//	}
//	
//	@Test
//	public void loadByGreater() {
//		var client1 = ClientFactory.getClient();
//		
//		this.txDelegateService.create(client1);
//		
//		var criteria = SearchCriteria.builder()
//				.age(0)
//				.clientId(client1.getClientId())
//				.operation(Operation.GREATERTHAN)
//				.build();
//		
//		var response = this.clientCriteriaService.load(client1.getClientId(), criteria);
//			
//		assertTrue(response.getClients().size() == 1);
//		assertEquals(response.getClients().get(0).getClientId(), response.getClients().get(0).getClientId());
//	}
//	
//	@Test
//	public void loadByEquals() {
//		var client1 = ClientFactory.getClient();
//		
//		this.txDelegateService.create(client1);
//		
//		var criteria = SearchCriteria.builder()
//				.age(client1.getAge())
//				.clientId(client1.getClientId())
//				.operation(Operation.EQUALTO)
//				.build();
//		
//		var response = this.clientCriteriaService.load(client1.getClientId(), criteria);
//			
//		assertTrue(response.getClients().size() == 1);
//		assertEquals(response.getClients().get(0).getClientId(), response.getClients().get(0).getClientId());
//	}
	
	
}

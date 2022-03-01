package com.platformbuilder.clientsapis;

import static org.junit.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;

import com.platformbuilder.clientsapis.dtos.ClientDTO;
import com.platformbuilder.clientsapis.dtos.ResponseClientDTO;
import com.platformbuilder.clientsapis.entities.Client;
import com.platformbuilder.clientsapis.service.interfaces.IClientCriteriaService;
import com.platformbuilder.clientsapis.service.interfaces.IClientService;

public class AbstractClassTests extends MysqlContainerService {

	@Autowired
	protected IClientService clientService;
	
	@Autowired
	protected IClientCriteriaService clientCriteriaService;
	
	@Autowired
	protected TxDelegateService txDelegateService;
	
	protected void assertValidations(Client expected, ClientDTO target) {
		assertEquals(target.getAge(), expected.getAge());
		assertEquals(target.getClientId(), expected.getClientId());
		assertEquals(target.getName(), expected.getName());
	}
	
	protected void assertValidations(Client expected, ResponseClientDTO target) {
		assertEquals(target.getAge(), expected.getAge());
		assertEquals(target.getClientId(), expected.getClientId());
		assertEquals(target.getName(), expected.getName());
		assertEquals(target.getId(), expected.getId());
	}
	
	protected void assertValidations(Client target, Client expected) {
		assertEquals(target.getAge(), expected.getAge());
		assertEquals(target.getClientId(), expected.getClientId());
		assertEquals(target.getName(), expected.getName());
		assertEquals(target.getId(), expected.getId());
	}
	
	protected void assertValidations(ResponseClientDTO target, ResponseClientDTO expected) {
		assertEquals(target.getAge(), expected.getAge());
		assertEquals(target.getClientId(), expected.getClientId());
		assertEquals(target.getName(), expected.getName());
		assertEquals(target.getId(), expected.getId());
	}
	
}

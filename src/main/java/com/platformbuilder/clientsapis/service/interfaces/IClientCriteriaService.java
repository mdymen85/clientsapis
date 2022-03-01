package com.platformbuilder.clientsapis.service.interfaces;

import com.platformbuilder.clientsapis.dtos.ResponseClientsDTO;
import com.platformbuilder.clientsapis.dtos.SearchCriteria;

public interface IClientCriteriaService {

	public ResponseClientsDTO load(String clientId, SearchCriteria criteria);
	
}

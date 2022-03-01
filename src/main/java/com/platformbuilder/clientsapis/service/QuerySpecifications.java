package com.platformbuilder.clientsapis.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.platformbuilder.clientsapis.dtos.SearchCriteria;
import com.platformbuilder.clientsapis.entities.Client;

@SuppressWarnings("serial")
public class QuerySpecifications implements Specification<Client> {

	private final SearchCriteria criteria;
	
	public QuerySpecifications(SearchCriteria criteria) {
		this.criteria = criteria;
	}
	
	@Override
	public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		
		Predicate predicate1 = builder.greaterThan(root.get("age"), criteria.getAge());
		
		Predicate finalPredicate = builder.or(predicate1, builder.equal(root.get("name"), criteria.getName()));
		
		return finalPredicate;
	}	 

}

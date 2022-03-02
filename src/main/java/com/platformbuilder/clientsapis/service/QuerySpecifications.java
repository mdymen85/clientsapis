package com.platformbuilder.clientsapis.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.platformbuilder.clientsapis.dtos.SearchCriteria;
import com.platformbuilder.clientsapis.entities.Client;

/**
 * 
 * Spec for more advanced queries in database
 * 
 * @author Martin Dymenstein
 *
 */
@SuppressWarnings("serial")
public class QuerySpecifications implements Specification<Client> {

	private final SearchCriteria criteria;
	
	public QuerySpecifications(SearchCriteria criteria) {
		this.criteria = criteria;
	}
	
	@Override
	public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		
		Predicate initialPredicate = null;
		
		if (!criteria.isClientIdNull()) {
			
			constructClientIdPredicate(root, builder, initialPredicate);
			
		}
		
		else if (!criteria.isNameNull()) {
			
			constructNamePredicate(root, builder, initialPredicate);
			
		}
		
		else if (!criteria.isAgeCriteriaNull()) {
			
			constructAgeCriteriaPredicate(root, builder, initialPredicate);
			
		}
		
		return builder.conjunction();
	}	 
	
	private void constructAgeCriteriaPredicate(Root<Client> root, CriteriaBuilder builder, Predicate initialPredicate) {
		
		switch (criteria.getAgeCriteria().getOperation()) {
		
		case LESSTHAN:
			
			builder.lessThan(root.get("ageCriteria.age"), criteria.getAgeCriteria().getAge());
			
			break;
			
		case GREATERTHAN:
			
			builder.greaterThan(root.get("ageCriteria.age"), criteria.getAgeCriteria().getAge());
			
			break;
			
		case EQUALTO:
			
			builder.equal(root.get("ageCriteria.age"), criteria.getAgeCriteria().getAge());
			
			break;
			
		default:
			
			throw new IllegalStateException();
		
		}
		
		
	}

	private void constructNamePredicate(Root<Client> root, CriteriaBuilder builder, Predicate initialPredicate) {
		var temporalPredicate = builder.equal(root.get("name"), criteria.getName());
		
		if (initialPredicate != null) {
			builder.and(initialPredicate, temporalPredicate);
		}
		
	}

	private void constructClientIdPredicate(Root<Client> root, CriteriaBuilder builder, Predicate initialPredicate) {
		
		var temporalPredicate = builder.equal(root.get("clientId"), criteria.getClientId());
		
		if (initialPredicate != null) {
			builder.and(initialPredicate, temporalPredicate);
		}
		
	}

}

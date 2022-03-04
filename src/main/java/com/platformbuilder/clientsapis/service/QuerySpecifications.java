package com.platformbuilder.clientsapis.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.platformbuilder.clientsapis.dtos.SearchCriteria;
import com.platformbuilder.clientsapis.entities.Client;

import lombok.Data;

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
		
		List<Predicate> predicates = new ArrayList<Predicate>(5);
		
		if (!criteria.isClientIdNull()) {
			
			constructClientIdPredicate(root, builder, predicates);
			
		}
		
		if (!criteria.isNameNull()) {
			
			constructNamePredicate(root, builder, predicates);
			
		}
		
		if (!criteria.isAgeCriteriaNull()) {
			
			constructAgeCriteriaPredicate(root, builder, predicates);
			
		}
				
		Predicate predicate = null;
		
		for (Predicate p : predicates) {
			predicate = builder.and(p);
		}
		
		return predicate;
	}	 
	
	private void constructAgeCriteriaPredicate(Root<Client> root, CriteriaBuilder builder, List<Predicate> predicates) {
		
		switch (criteria.getOperation()) {
		
		case LESSTHAN:
			
			predicates.add(builder.lessThan(root.get("age"), criteria.getAge()));
			break;
			
		case GREATERTHAN:
			
			predicates.add(builder.greaterThan(root.get("age"), criteria.getAge()));
			
			break;
			
		case EQUALTO:
			
			predicates.add(builder.equal(root.get("age"), criteria.getAge()));
			
			break;
			
		default:
			
			throw new IllegalStateException();
		
		}
		
		
	}

	private void constructNamePredicate(Root<Client> root, CriteriaBuilder builder, List<Predicate> predicates) {
		
		predicates.add(builder.equal(root.get("name"), criteria.getName()));
		
	}

	private void constructClientIdPredicate(Root<Client> root, CriteriaBuilder builder, List<Predicate> predicates) {
		
		predicates.add(builder.equal(root.get("clientId"), criteria.getClientId()));
		
	}

}

package com.yebelo.urlshortener.repository;

import org.springframework.stereotype.Repository;

import com.yebelo.urlshortener.entity.UrlDetails;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class UrlShortenerRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	public Long saveUrlDetails(UrlDetails urlDetails) {
		if (null == urlDetails.getId()) {
			em.persist(urlDetails);
		}
		else {
			em.merge(urlDetails);
		}
		return urlDetails.getId();
		
	}
	
	public UrlDetails findUrlDetailsById(Long id) {
		
		return em.find(UrlDetails.class, id);
		
	}

}

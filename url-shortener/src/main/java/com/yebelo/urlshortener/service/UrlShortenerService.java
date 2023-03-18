package com.yebelo.urlshortener.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.yebelo.urlshortener.entity.UrlDetails;
import com.yebelo.urlshortener.repository.UrlShortenerRepository;
import com.yebelo.urlshortener.util.IdConversionAlgo;

import jakarta.transaction.Transactional;


@Service
public class UrlShortenerService {
	
	@Autowired
	UrlShortenerRepository repository;
	

	@Transactional
	public String getShortenedUrl(UrlDetails urlDetails, String callingUrl) {
		
		Long dbKey = repository.saveUrlDetails(urlDetails);

		String uniqueId = IdConversionAlgo.INSTANCE.createUniqueID(dbKey);
		
		String baseUrl = formatLocalURLFromShortener(callingUrl);
		
		String shortenedURL = baseUrl + uniqueId;
		
		urlDetails.setShortUrl(shortenedURL);
		
		repository.saveUrlDetails(urlDetails);
		
		return shortenedURL;
	}
	
	 private String formatLocalURLFromShortener(String callingUrl) {
	        String[] callingUrlComponents = callingUrl.split("/");
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < callingUrlComponents.length - 1; ++i) {
	            sb.append(callingUrlComponents[i]);
	        }
	        sb.append('/');
	        return sb.toString();
	    }
	 
	 @Cacheable("GetOriginalUrlCache")
	public String getOriginalURLFromID(String id) {
		Long dbKey = IdConversionAlgo.INSTANCE.getDBKeyFromUrlIdUsingBase62Map(id);
		UrlDetails urlDetails = repository.findUrlDetailsById(dbKey);
		//String originalUrl=
		return urlDetails.getUrl();
	}

}

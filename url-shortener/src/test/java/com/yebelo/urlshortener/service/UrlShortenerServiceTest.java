package com.yebelo.urlshortener.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.yebelo.urlshortener.entity.UrlDetails;
import com.yebelo.urlshortener.repository.UrlShortenerRepository;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class UrlShortenerServiceTest {
	
	@Mock
	private UrlShortenerRepository repository;
	
	@InjectMocks
	private UrlShortenerService urlShortenerService;
	
	private UrlDetails urlDetails;
	
	private String callingUrl;
	private String originalUrl = "www.somedummyurl.com";
	
	@BeforeAll
	void testSetUp() {
		urlDetails = new UrlDetails();
		urlDetails.setId(1L);
		
		urlDetails.setUrl(originalUrl);
		callingUrl="www.callingurl.com";
	}
	
	
	@Test
	void getShortenedUrlTest() {
		
		when(repository.saveUrlDetails(Mockito.any(UrlDetails.class))).thenReturn(urlDetails.getId());
		assertEquals("/b", urlShortenerService.getShortenedUrl(urlDetails, callingUrl));
		
	}
	
	@Test
	void getOriginalURLFromID() {
		when(repository.findUrlDetailsById(Mockito.anyLong())).thenReturn(urlDetails);
		assertEquals(originalUrl, urlShortenerService.getOriginalURLFromID("1"));
	}

}

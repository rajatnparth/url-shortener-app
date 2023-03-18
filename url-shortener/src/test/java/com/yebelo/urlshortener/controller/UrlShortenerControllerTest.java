package com.yebelo.urlshortener.controller;

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
import com.yebelo.urlshortener.service.UrlShortenerService;

import jakarta.servlet.http.HttpServletRequest;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
class UrlShortenerControllerTest {

	@Mock
	UrlShortenerService urlShortenerService;
	
	@InjectMocks
	UrlShortenerController urlShortenerController;
	
	UrlDetails urlDetails;
	
	private StringBuffer callingUrl;
	private String originalUrl = "www.somedummyurl.com";
	

	@Mock
	private HttpServletRequest request;
	
	@BeforeAll
	void testSetUp() {
		urlDetails = new UrlDetails();
		urlDetails.setId(1L);
		
		urlDetails.setUrl(originalUrl);
		callingUrl = new StringBuffer("www.callingurl.com");
	}
	
	
	@Test
	void getShortenedUrlTest() throws Exception {
		
		when(urlShortenerService.getShortenedUrl(Mockito.any(UrlDetails.class), Mockito.anyString())).thenReturn(callingUrl.append("/b").toString());
		when(request.getRequestURL()).thenReturn(callingUrl);
		assertEquals("www.callingurl.com/b", urlShortenerController.shortenUrl(urlDetails, request));
		
	}
	
	@Test
	void getOriginalURLTest() {
		when(urlShortenerService.getOriginalURLFromID(Mockito.anyString())).thenReturn(originalUrl);
		assertEquals(originalUrl, urlShortenerService.getOriginalURLFromID("1"));
	}

	

}

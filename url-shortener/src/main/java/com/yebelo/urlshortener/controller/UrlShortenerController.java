package com.yebelo.urlshortener.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yebelo.urlshortener.entity.UrlDetails;
import com.yebelo.urlshortener.service.UrlShortenerService;
import com.yebelo.urlshortener.util.UrlValidator;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class UrlShortenerController {

	Logger logger = LoggerFactory.getLogger(UrlShortenerController.class);

	@Autowired
	UrlShortenerService urlShortenerService;

	@GetMapping("/{id}")
	public String getOriginalURL(@PathVariable String id, HttpServletRequest request) {

		logger.info("Original URL retreival request received for URLId::{}", id);
		String originalUrl = urlShortenerService.getOriginalURLFromID(id);
		return originalUrl;

	}

	@PostMapping("/shortenUrl")
	public String shortenUrl(@RequestBody UrlDetails urlDetails, HttpServletRequest request) throws Exception{

		logger.info("URL shortening request received for URL::{}", urlDetails);
		if (UrlValidator.INSTANCE.validateURL(urlDetails.getUrl())) {
		String callingURL = request.getRequestURL().toString();

		logger.info("URL shortening request received for URL::{}", callingURL);

		String shortenedUrl = urlShortenerService.getShortenedUrl(urlDetails, callingURL);
		return shortenedUrl;
		}
		throw new Exception("URL is not valid. Please enter correct URL and try again");

	}

}

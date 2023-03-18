package com.yebelo.urlshortener.entity;

import javax.persistence.Cacheable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
@Cacheable
public class UrlDetails {
	
	@Id
	@GeneratedValue
	Long id;
	String url;
	String shortUrl;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getShortUrl() {
		return shortUrl;
	}
	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}
	public UrlDetails() {
		
	}
	public UrlDetails(Long id, String url, String shortUrl) {
		super();
		this.id = id;
		this.url = url;
	}
	@Override
	public String toString() {
		return "UrlDetails [id=" + id + ", url=" + url + ", shortUrl=" + shortUrl + "]";
	}
	

}

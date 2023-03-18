# Read Me First

The application uses the following tech stack in the backend-
1. Java 17
2. Spring Boot 3
3. Spring 6
4. JPA/Hibernate 
5. Docker Image for MySQL
6. JUnit
7. JMeter

Please Note-

1. The application is implemented as Restful WebServices
2. The database details used are available in application.properties
3. The DB script is present in db.sql
4. To make things easy and quick, I have configured DB table creation on app startup. This will create/replace the table on app startup.
5. jmx file included - /url-shortener/src/main/resources/UrlShortenerPerformanceTestPlan.jmx
6. You can check the screenshots of the project in the doc file - URL-Shortener-screenshots.docx



# Getting Started
1. Prerequisite - MySQL database needs to be up with the details present in 
					application.properties
					
2. The Rest API for shortening the original URL- 
	URL - localhost:8080/shortenUrl
	Request Body - {  "url":"somedummyurl.com/anylongdummy/url" }
	Header - Content-Type : application/json
	
3. The Rest API for fetching the original URL from shortened URL- 
	URL - localhost:8080/{id}
	Here Id is the Id returned from Rest API 1




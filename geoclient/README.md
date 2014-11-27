### Geo Project

1.  A spreadsheet with the above answers for the original 10,000 entries.
==> Please see attchment 

2.  Source code for the solution.  
==> Gitlab

Build : mvn clean install 

Choice of tech :
I have primarily used Spring framework and JavaEE for most of my work, Dropwizard (http://dropwizard.io) has been my recent choice for building backend REST services such as this. Your ask for WAR kind of was not truly aligned with my choice here. I choose Dropwizard it for high productity given the time limitation. 

	* Health check : 
	* Metric :

3.  The mechanism by which you populated the 10,000 entries.
==> Since you did not mention a lauguage choice, I took the liberty to use shell script.

4.  WAR file that we can deploy to Tomcat to test functionality.
==> mvn clean install will build a WAR file in target/
Also available at :

5.  DB schema
I used MySQL. 
DDL : create table geoloc(id int NOT NULL AUTO_INCREMENT, lat decimal(18,14), lon decimal(18,14), PRIMARY KEY (id), unique latlon(lat, lon)); 

6.  Seed data for the DB.
Git lab location 
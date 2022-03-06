# Exercise for Platform Builders

## Clients APIs

### Introduction

This was an excercise from the company Platform Builders, but isnt 100% complete because i already spent many hours, and then i decided to stop because with the code and technologies that i used, i think that i prove the point that i know about coding.

## Technologies

 - Spring Boot
 - Lombok
 - Mapstruct
 - Swagger-Ui
 - Controller Advice
 - Cache
 - Testcontainer
 - Spring Specification
 - Docker
 - Mysql

### Spring Boot

The application was developed using JAVA Spring Boot

### Lombok

I used lombok in order to simplify class and left the code less verbose without any boilerplate code:

```
	<dependency>
	    <groupId>org.projectlombok</groupId>
	    <artifactId>lombok</artifactId>
	    <scope>provided</scope>
	</dependency>	
```

### Mapstruct

Mapstruct is a technology to convert one type of object to an other. A simple way to turn a DTO to a Domain object. The dependencies of mapstruct conflicts with lombok, so i needed to add some other plugins to determinate the order that spring needs to register all beans in the container, to avoid those conflits. To add mapstruct you will need to add this to the pom file:

```
	<dependency>
	    <groupId>org.mapstruct</groupId>
	    <artifactId>mapstruct</artifactId>
	    <version>1.4.2.Final</version> 
	</dependency>		
			
	<dependency>
	    <groupId>org.projectlombok</groupId>
	    <artifactId>lombok-mapstruct-binding</artifactId>
	    <version>0.1.0</version>
	</dependency>
```

### Swagger-Ui

In order to document the swagger, i used Swagger-ui. To access to this documentation, you will need to run the application, and then access the following URL: http://localhost:8080/swagger-ui/#/


```

	<dependency>
	    <groupId>io.springfox</groupId>
	    <artifactId>springfox-boot-starter</artifactId>
	    <version>3.0.0</version>
	</dependency>

	<dependency>
	    <groupId>io.springfox</groupId>
	    <artifactId>springfox-swagger-ui</artifactId>
	    <version>3.0.0</version>
	</dependency>
		
```


### Controller Advice

To throw exception i used a Controller Advice. So when some exception is captured, the advice will handle and throw the exception properly, with all the information needed.

### Cache

Just to show some cache simple use, i annotated a method with a Cache, and then, after update this cached object, i evict the cache to get the object again an refresh the cache. I also developed a test to show how the cache doesnt go to the database to load a cached object.

### Testcontainer

For testing purpose i develop a testcontainer to run a docker with the mysql database, so in every test session, it will lunch a new mysql container and test the application using that database.

```
	<dependency>
	    <groupId>org.testcontainers</groupId>
	    <artifactId>junit-jupiter</artifactId>
	    <version>1.16.2</version>
	    <scope>test</scope>
	</dependency>	
	
	<dependency>
	    <groupId>org.testcontainers</groupId>
	    <artifactId>mysql</artifactId>
	    <version>1.16.3</version>
	    <scope>test</scope>
	</dependency>
```


### Spring Specification

I develop a criteria search, using spring specification, giving to the user the possibility to search clients with a specific criteria. In this case in didnt invest much time, but it can be noticed just the way it can be used.

### Docker

First of all the user will need to create a network such as: 

```
 docker network create clientsapi-network
```

I develop a docker-compose to run the application and the database at the same time. In the root directory with just the following sentence, the user might run the application:


```
 docker-compose up -d
```

### Mysql

There is a file called **data.sql** that has the sql statements to create the database, use it and create the table that the application use.



## Conclutions

In order to develop this test properly, and show a good qualitiy of code, i needed to invest a lot of time, so i didnt finish. In other terms, i feel that i proved the point that i know about technology, how to code, and how to develop software.

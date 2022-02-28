package com.platformbuilder.clientsapis;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.mysql.cj.jdbc.MysqlDataSource;

@Testcontainers
public class MysqlContainerService {
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public static MySQLContainer mysql = (MySQLContainer) new MySQLContainer<>()
			.withInitScript("data.sql")
			.withUsername("root")
			.withDatabaseName("clientsapis_db")
			.withPassword("mdymen_pass");
	
	@ComponentScan(basePackages = "com.platformbuilder.clientsapis.*")
	@Configuration
	static class MySqlConfiguration {
		
		@Bean(name = "mysqlDataSource")
		public DataSource createDataSource() {
			var datasource = new MysqlDataSource();
			datasource.setUrl("jdbc:mysql://localhost:" + mysql.getFirstMappedPort() + "/clientsapis_db");
			datasource.setUser("root");
			datasource.setPassword("mdymen_pass");
			
			return datasource;
		}
		
	}
			
	
}

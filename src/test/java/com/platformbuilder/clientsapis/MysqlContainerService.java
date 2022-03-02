package com.platformbuilder.clientsapis;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.mysql.cj.jdbc.MysqlDataSource;

/**
 * Class that configure a docker container with mysql for testing purpose
 * 
 * @author Martin Dymenstein
 *
 */
@Testcontainers
public class MysqlContainerService {
	
	private static String USERNAME = "root";
	private static String PASSWORD = "mdymen_pass";
	private static String INIT_SCRIPT = "data.sql";
	private static String DATABASE = "clientsapis_db";
	
	private static String CONNECTION_STRING = "jdbc:mysql://localhost:";
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	public static MySQLContainer mysql = (MySQLContainer) new MySQLContainer<>()			
			.withUsername(USERNAME)
			.withPassword(PASSWORD)
			.withDatabaseName(DATABASE)
			.withInitScript(INIT_SCRIPT);
	
	@ComponentScan(basePackages = "com.platformbuilder.clientsapis.*")
	@Configuration
	static class MySqlConfiguration {
		
		@Bean(name = "mysqlDataSource")
		public DataSource createDataSource() {		
			
			var datasource = new MysqlDataSource();
			datasource.setUrl(getConnectionString());
			datasource.setUser(USERNAME);
			datasource.setPassword(PASSWORD);
			
			return datasource;
		}
		
		private String getConnectionString() {
			StringBuilder str = new StringBuilder(CONNECTION_STRING);
			str.append(mysql.getFirstMappedPort());
			str.append("/");
			str.append(DATABASE);
			
			return str.toString();
		}
		
	}
	
	@BeforeAll
	public static void init() {
		mysql.start();
	}
			
	
}

package com.sumit.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zaxxer.hikari.HikariDataSource;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

@Configuration
public class MyRDSDBConfig {

	@Value("${rds_access_key_user}")
	private String accessKey;
	
	@Value("${rds_secret_key_user}")
	private String secretKey;
	
	@Bean
	public DataSource datasource() throws JsonMappingException, JsonProcessingException {
		String secret = getSecret();
		JsonNode secretNode = new ObjectMapper().readTree(secret);
		String userName = secretNode.get("username").asText();
		String pass = secretNode.get("password").asText();
		String host = secretNode.get("host").asText();
		String port = secretNode.get("port").asText();
		String dbName = secretNode.get("dbname").asText();
		
		//jdbc:mysql://devops-mysql-rds-3.cn6o8wea6t1u.ap-south-1.rds.amazonaws.com:3306/emp
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setJdbcUrl("jdbc:mysql://"+ host + ":" + port +"/" + dbName);
		hikariDataSource.setUsername(userName);
		hikariDataSource.setPassword(pass);
		return hikariDataSource;
	}
	
	public String getSecret() {

		String secretName = "devops-DB-secret";
		Region region = Region.of("ap-south-1");

		AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(accessKey, secretKey);
		
		SecretsManagerClient client = SecretsManagerClient.builder()
				.region(region)
				.credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
				.build();

		GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder().secretId(secretName).build();

		GetSecretValueResponse getSecretValueResponse;

		try {
			getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
		} catch (Exception e) {
			throw e;
		}

		return getSecretValueResponse.secretString();

		// Your code goes here.
	}
}

package com.sumit.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClientBuilder;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.sumit.repository")
public class DynamoDBConfig {

	@Value("${dynamo.access}")
	private String accessKey;
	@Value("${dynamo.secret}")
	private String secretKey;
	
	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		return AmazonDynamoDBAsyncClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("dynamodb.ap-south-1.amazonaws.com", "ap-south-1"))
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
				.build();
	}
}

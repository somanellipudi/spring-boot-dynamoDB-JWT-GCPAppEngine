package com.bcp.contentfree.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.accesskey}")
    private String amazonDynamoDBAccessKey;
    @Value("${amazon.dynamodb.secretkey}")
    private String amazonDynamoDBSecretKey;

    @Value("${amazon.end-point.url}")
    private String amazonDynamoEndPoint;

    @Value("${amazon.region}")
    private String amazonRegion;

    @Bean
    public DynamoDBMapper mapper() {
        return new DynamoDBMapper(amazonDynamoDBConfig());
    }

    public AmazonDynamoDB amazonDynamoDBConfig() {
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoEndPoint, amazonRegion))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(amazonDynamoDBAccessKey, amazonDynamoDBSecretKey)))
                .build();


    }


}

package com.zephyr.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.mongodb.client.MongoClient;

@Configuration
public class MongoConfig {

	@Value("${spring.data.mongodb.host}")
	private String host;
	@Value("${spring.data.mongodb.port}")
	private int port;
	
	@Value("${spring.data.mongodb.database}")
	private String dbName;
	
	@Bean
	public MongoTemplate mongoTemplate(MongoDatabaseFactory dbFactory) {
		return new MongoTemplate(dbFactory);
	}

	@Bean
	public MongoClientFactoryBean mongoClient() {
		MongoClientFactoryBean mongo = new MongoClientFactoryBean();
		mongo.setHost(host);
		mongo.setPort(port);
		return mongo;
	}
	
	@Bean
	public MongoDatabaseFactory mongoDBFactory(MongoClient client) {
		return new SimpleMongoClientDatabaseFactory(client, dbName);
	}
	


}

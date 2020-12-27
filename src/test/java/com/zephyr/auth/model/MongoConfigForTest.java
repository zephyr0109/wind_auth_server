package com.zephyr.auth.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.mongodb.client.MongoClient;

@Configuration("mongoConfigTest")
public class MongoConfigForTest {

	@Value("${test.mongodb.host}")
	private String host;
	@Value("${test.mongodb.port}")
	private int port;
	
	@Value("${test.mongodb.database}")
	private String dbName;
	
	@Bean("mongoTemplateTest")
	public MongoTemplate mongoTemplate(MongoDatabaseFactory dbFactory) {
		return new MongoTemplate(dbFactory);
	}

	@Bean("mongoClientFactoryBean")
	public MongoClientFactoryBean mongoClient() {
		MongoClientFactoryBean mongo = new MongoClientFactoryBean();
		mongo.setHost(host);
		mongo.setPort(port);
		return mongo;
	}
	
	@Bean("databaseFactoryTest")
	public MongoDatabaseFactory mongoDBFactory(MongoClient client) {
		return new SimpleMongoClientDatabaseFactory(client, dbName);
	}
	


}

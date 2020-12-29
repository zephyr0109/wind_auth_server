package com.zephyr.auth.model;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.client.MongoClient;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

@Configuration("mongoConfigTest")
@EnableMongoRepositories(basePackages = {"com.zephyr.auth.model"}, mongoTemplateRef = "mongoTemplate")
public class MongoConfigForTest {

	@Value("${test.mongodb.host}")
	private String host;
	
	private int port;

	@Value("${test.mongodb.database}")
	private String dbName;

	@Bean("mongoTemplate")
	public MongoTemplate mongoTemplate(MongoDatabaseFactory dbFactory) {
		return new MongoTemplate(dbFactory);
	}

	@Bean("mongoClientFactoryBean")
	public MongoClientFactoryBean mongoClient() throws IOException {
		mongodExecutable();
		MongoClientFactoryBean mongo = new MongoClientFactoryBean();
		mongo.setHost(host);
		mongo.setPort(port);
		return mongo;
	}

	@Bean("databaseFactoryTest")
	public MongoDatabaseFactory mongoDBFactory(MongoClient client) {
		return new SimpleMongoClientDatabaseFactory(client, dbName);
	}

	@Bean(initMethod = "start", destroyMethod = "stop")
	public MongodExecutable mongodExecutable() throws IOException {
		MongodStarter starter = MongodStarter.getDefaultInstance();
		port = Network.getFreeServerPort();
		System.out.println("port : " + port);
		IMongodConfig mongodConfig = new MongodConfigBuilder()
			    .version(Version.Main.PRODUCTION)
			    .net(new Net(port, Network.localhostIsIPv6()))
			    .build();		
		
		return starter.prepare(mongodConfig);  
	}

}

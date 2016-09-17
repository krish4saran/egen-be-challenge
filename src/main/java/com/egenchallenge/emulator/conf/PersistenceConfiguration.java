package com.egenchallenge.emulator.conf;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

/**
 * @author saranjithkrishnan
 *
 */
@Configuration
public class PersistenceConfiguration {
	
	@Value("${spring.data.mongodb.port}")
	private int port;
	
	@Value("${spring.data.mongodb.host}")
	private String host;
	
	@Value("${spring.data.mongodb.database}")
	private String database;
	
	private Morphia morphia;
	
	@Bean
	public MongoClient mongoClient() throws UnknownHostException{
		return new MongoClient(this.host, mongoClientOptions());
	}
	
	@Bean
	@ConfigurationProperties(prefix="spring.data.mongodb")
	public MongoClientOptions mongoClientOptions() {
		return new MongoClientOptions.Builder().build();

	}
	
	@Bean
	public Morphia getMorphia(){
		this.morphia = new Morphia();
		return morphia; 
	}
	
	@Bean
	@Primary
	public Datastore datastore() throws UnknownHostException {
		getMorphia().mapPackage("com.egenchallenge.emulator.domain");
	        Datastore datastore = morphia.createDatastore(mongoClient(), this.database);
	        datastore.ensureIndexes();
	        return datastore;
	 }
	 
	

}

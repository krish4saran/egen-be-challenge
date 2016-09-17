package com.egenchallenge.emulator.conf;

import java.net.UnknownHostException;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

/**This class is used for configuring the data tier of the application
 * Currently using {@link Morphia} as data layer access framework
 * Configuration are provided in application.properties file located under the classpath - 
 * which spring boot will read and create an {@link Environment}
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
	
	/**Create Mongo client for the datastore
	 * @return {@link MongoClient}
	 * @throws UnknownHostException
	 */
	@Bean
	public MongoClient mongoClient() throws UnknownHostException{
		return new MongoClient(this.host, mongoClientOptions());
	}
	
	/**MongoOption to help with building all necessary configuration
	 * for accessing db such as read time out, active sesssion etc.
	 * @return {@link MongoClientOptions}
	 */
	@Bean
	@ConfigurationProperties(prefix="spring.data.mongodb")
	public MongoClientOptions mongoClientOptions() {
		return new MongoClientOptions.Builder().build();

	}
	
	/**Instantiate new Morphia for performing repository operations
	 * @return {@link Morphia}
	 */
	@Bean
	public Morphia getMorphia(){
		this.morphia = new Morphia();
		return morphia; 
	}
	
	/**Create a datastore for the dbname provided in the properties
	 * @return {@link Datastore}
	 * @throws UnknownHostException
	 */
	@Bean
	@Primary
	public Datastore datastore() throws UnknownHostException {
		getMorphia().mapPackage("com.egenchallenge.emulator.domain");
	        Datastore datastore = morphia.createDatastore(mongoClient(), this.database);
	        datastore.ensureIndexes();
	        return datastore;
	 }
	 
	

}

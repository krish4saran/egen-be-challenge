package com.egenchallenge.emulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * This class will initiate spring boot to configure beans required &
 * work with spring mvc modules (from pom) to create microservices. 
 *
 */

@SpringBootApplication
@ComponentScan(basePackages="com.egenchallenge.emulator")
public class App 
{
	private static final Class<App> applicationClass = App.class;
    
	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}
	
	
}

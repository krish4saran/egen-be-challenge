package com.egenchallenge.emulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
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

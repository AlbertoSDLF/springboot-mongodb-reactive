package com.asitc.sbmongodbreactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class SbMongodbReactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(SbMongodbReactiveApplication.class, args);
	}

}

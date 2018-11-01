package com.asitc.sbmongodbreactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

//@SpringBootApplication(exclude = { MongoReactiveAutoConfiguration.class, MongoReactiveDataAutoConfiguration.class })
// @SpringBootApplication(exclude = { MongoReactiveAutoConfiguration.class,
// MongoReactiveDataAutoConfiguration.class })
@SpringBootApplication
@EnableReactiveMongoRepositories
// @AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
public class SbMongodbReactiveApplication /* extends AbstractReactiveMongoConfiguration */ {

	// @Autowired
	// private Environment environment;
	//
	// @Override
	// @Bean
	// @DependsOn("embeddedMongoServer")
	// public MongoClient reactiveMongoClient() {
	// int port = environment.getProperty("local.mongo.port", Integer.class);
	// return MongoClients.create(String.format("mongodb://localhost:%d", port));
	// }
	//
	// @Override
	// protected String getDatabaseName() {
	// return "reactive-mongodb";
	// }

	public static void main(String[] args) {
		SpringApplication.run(SbMongodbReactiveApplication.class, args);
	}

}

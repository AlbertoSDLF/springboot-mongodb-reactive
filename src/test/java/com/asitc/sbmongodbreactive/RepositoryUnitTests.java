package com.asitc.sbmongodbreactive;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.asitc.sbmongodbreactive.repository.user.ReactiveUserRepository;
import com.asitc.sbmongodbreactive.repository.user.entity.User;

import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryUnitTests {

	@Autowired
	ReactiveUserRepository repository;
	@Autowired
	ReactiveMongoOperations operations;

	@Before
	public void setUp() {
		operations.collectionExists(User.class)
				.flatMap(exists -> exists ? operations.dropCollection(User.class) : Mono.just(exists))
				.flatMap(o -> operations.createCollection(User.class)).then().block();
		final User user = new User();
		user.setName("User 1");
		user.setEmail("user1@domain.co.uk");
		operations.save(user).then().block();
	}

	@Test
	public void findByName() {
		List<User> users = repository.findByName("User 1").collectList().block();
		assertThat(users).hasSize(1);
	}

	@Test
	public void findByNameAndEmail() {
		User user = repository.findByNameAndEmail("User 1", "user1@domain.co.uk").block();
		assertThat(user).isNotNull();
	}

}

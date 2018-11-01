package com.asitc.sbmongodbreactive.repository.user;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.asitc.sbmongodbreactive.repository.user.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveUserRepository extends ReactiveCrudRepository<User, String> {

	Flux<User> findByName(String name);

	Flux<User> findByName(Mono<String> name);

	Mono<User> findByNameAndEmail(Mono<String> name, String email);

	@Query("{ 'name': ?0, 'email': ?1}")
	Mono<User> findByNameAndEmail(String name, String email);

}

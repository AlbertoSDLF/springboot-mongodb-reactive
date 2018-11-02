package com.asitc.sbmongodbreactive.repository.user;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.asitc.sbmongodbreactive.repository.user.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveUserRepository extends ReactiveCrudRepository<User, String> {

	Flux<User> findByName(String name);

	Mono<User> findByNameAndEmail(String name, String email);

}

package com.asitc.sbmongodbreactive.service;

import com.asitc.sbmongodbreactive.repository.user.entity.User;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveUserService {

	Flux<User> findUsersByName(String name);

	Mono<User> create(User user);

}

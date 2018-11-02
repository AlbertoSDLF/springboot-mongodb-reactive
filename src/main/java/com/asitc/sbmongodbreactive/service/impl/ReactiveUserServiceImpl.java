package com.asitc.sbmongodbreactive.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asitc.sbmongodbreactive.repository.user.ReactiveUserRepository;
import com.asitc.sbmongodbreactive.repository.user.entity.User;
import com.asitc.sbmongodbreactive.service.ReactiveUserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveUserServiceImpl implements ReactiveUserService {

	@Autowired
	private ReactiveUserRepository repository;

	@Override
	public Mono<User> findUserById(final String id) {
		return this.repository.findById(id);
	}

	@Override
	public Flux<User> findUsersByName(final String name) {
		return this.repository.findByName(name);
	}

	@Override
	public Mono<User> createUser(final Mono<User> user) {
		return this.repository.save(user.block());
	}

	@Override
	public Flux<User> createUsers(final Flux<User> user) {
		return this.repository.saveAll(user);
	}

}

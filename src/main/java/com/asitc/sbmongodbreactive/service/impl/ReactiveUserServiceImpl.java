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
	public Mono<User> create(final User user) {
		return this.repository.save(user);
	}

}

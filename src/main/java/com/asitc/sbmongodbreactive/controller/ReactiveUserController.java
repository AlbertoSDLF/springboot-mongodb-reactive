package com.asitc.sbmongodbreactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asitc.sbmongodbreactive.controller.dto.UserDTO;
import com.asitc.sbmongodbreactive.repository.user.entity.User;
import com.asitc.sbmongodbreactive.service.ReactiveUserService;

import ma.glasnost.orika.MapperFacade;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/reactive/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
class ReactiveUserController {

	@Autowired
	private ReactiveUserService service;
	@Autowired
	private MapperFacade mapper;

	@GetMapping
	Flux<UserDTO> findByNameReactive(final @RequestParam String name) {
		Flux<User> result = service.findUsersByName(name);
		return result.map(u -> this.mapper.map(u, UserDTO.class));
	}

	@PostMapping
	Mono<UserDTO> createReactive(final @RequestBody UserDTO userDto) {
		Mono<User> result = service.create(this.mapper.map(userDto, User.class));
		return result.map(u -> this.mapper.map(u, UserDTO.class));
	}

}

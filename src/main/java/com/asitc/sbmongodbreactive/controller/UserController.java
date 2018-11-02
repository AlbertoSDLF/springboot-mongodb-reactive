package com.asitc.sbmongodbreactive.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping(value = "/api/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
class UserController {

	@Autowired
	private ReactiveUserService service;
	@Autowired
	private MapperFacade mapper;

	@GetMapping("{id}")
	public ResponseEntity<UserDTO> findUserById(final @PathVariable String id) {
		Mono<User> result = service.findUserById(id);
		return new ResponseEntity<>(result.map(u -> this.mapper.map(u, UserDTO.class)).block(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Collection<UserDTO>> findUsersByName(final @RequestParam String name) {
		Flux<User> result = service.findUsersByName(name);
		return new ResponseEntity<>(result.map(u -> this.mapper.map(u, UserDTO.class)).collectList().block(),
				HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<UserDTO> createUsers(final @RequestBody UserDTO userDto) {
		Mono<User> result = service.createUser(Mono.just(this.mapper.map(userDto, User.class)));
		return new ResponseEntity<>(result.map(u -> this.mapper.map(u, UserDTO.class)).block(), HttpStatus.OK);
	}

}

package com.asitc.sbmongodbreactive.controller;

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
import org.springframework.web.reactive.function.server.ServerResponse;

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

	@GetMapping("/{id}")
	public ResponseEntity<Mono<UserDTO>> /*Mono<ServerResponse>*/ findById(final @PathVariable String id) {
		Mono<UserDTO> result = service.findUserById(id).switchIfEmpty(Mono.error(new Exception())).map(u -> this.mapper.map(u, UserDTO.class));
		return new ResponseEntity<>(result, HttpStatus.OK);
//        return ServerResponse.ok()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(result, UserDTO.class)
//                .switchIfEmpty(ServerResponse.notFound().build())
//                .onErrorResume(Exception.class, e -> ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
	}	
	
	@GetMapping
	Mono<ServerResponse> findByName(final @RequestParam String name) {
		Flux<UserDTO> result = service.findUsersByName(name).map(u -> this.mapper.map(u, UserDTO.class));
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result, UserDTO.class)
                .onErrorResume(Exception.class, e -> ServerResponse.notFound().build());
	}

	@PostMapping
	Mono<ServerResponse> create(final @RequestBody UserDTO userDto) {
		Mono<UserDTO> result = service.create(this.mapper.map(userDto, User.class)).map(u -> this.mapper.map(u, UserDTO.class));
		return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(result, UserDTO.class)
                .onErrorResume(Exception.class, e -> ServerResponse.notFound().build());
	}

}

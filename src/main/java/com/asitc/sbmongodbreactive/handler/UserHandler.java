package com.asitc.sbmongodbreactive.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.asitc.sbmongodbreactive.controller.dto.UserDTO;
import com.asitc.sbmongodbreactive.repository.user.entity.User;
import com.asitc.sbmongodbreactive.service.ReactiveUserService;

import ma.glasnost.orika.MapperFacade;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {

	@Autowired
	private ReactiveUserService service;
	@Autowired
	private MapperFacade mapper;

	public Mono<ServerResponse> findUserById(final ServerRequest request) {
		Mono<UserDTO> result = service.findUserById(request.pathVariable("id"))
				.switchIfEmpty(Mono.error(new Exception())).map(u -> this.mapper.map(u, UserDTO.class));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(result, UserDTO.class);
	}

	public Mono<ServerResponse> findUsersByName(final ServerRequest request) {
		Flux<UserDTO> result = service.findUsersByName(request.queryParam("name").get())
				.map(u -> this.mapper.map(u, UserDTO.class));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(result, UserDTO.class)
				.onErrorResume(Exception.class, e -> ServerResponse.notFound().build());
	}

	public Mono<ServerResponse> createUser(final ServerRequest request) {
		Mono<User> userBody = request.bodyToMono(UserDTO.class).map(udto -> this.mapper.map(udto, User.class));
		Mono<UserDTO> result = this.service.createUser(userBody).map(u -> this.mapper.map(u, UserDTO.class));
		return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(result, UserDTO.class)
				.onErrorResume(Exception.class, e -> ServerResponse.notFound().build());
	}

}

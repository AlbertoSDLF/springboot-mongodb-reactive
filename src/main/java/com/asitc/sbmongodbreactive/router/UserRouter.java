package com.asitc.sbmongodbreactive.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.asitc.sbmongodbreactive.handler.UserHandler;

@Configuration
public class UserRouter {

	@Bean
	public RouterFunction<ServerResponse> route(final UserHandler userHandler) {
		return RouterFunctions
				.route(RequestPredicates.GET("/api/reactive/user/{id}")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::findUserById)
				.andRoute(RequestPredicates.GET("/api/reactive/user")
						.and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), userHandler::findUsersByName)
				.andRoute(
						RequestPredicates.POST("/api/reactive/user")
								.and(RequestPredicates.accept(MediaType.APPLICATION_JSON))
								.and(RequestPredicates.contentType(MediaType.APPLICATION_JSON)),
						userHandler::createUser);
	}

}

package com.asitc.sbmongodbreactive.controller.dto;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class UserDTO {

	private ObjectId _id;
	private String name;
	private String email;

}

package com.asitc.sbmongodbreactive.repository.user.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
public class User {

	@Id
	private ObjectId _id;
	private String name;
	private String email;

}

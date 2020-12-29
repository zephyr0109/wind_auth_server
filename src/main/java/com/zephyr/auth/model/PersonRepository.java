package com.zephyr.auth.model;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String>{

	Optional<Person> findByIdAndPassword(String id, String oldPwd);

}

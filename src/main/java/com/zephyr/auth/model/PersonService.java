package com.zephyr.auth.model;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Date;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonService {

	private MongoTemplate mongoOps;

	public PersonService(MongoTemplate mongoOps) {
		this.mongoOps = mongoOps;
	}

	public Person insertPerson(Person p) {

		Person result = mongoOps.insert(p);
		log.debug("get person info, id : " + result.getId());

		return result;
	}

	public Person findPersonByName(String id) {
		Person result = mongoOps.findOne(new Query(where("name").is(id)), Person.class);
		log.info(result.toString());
		return result;
	}

}

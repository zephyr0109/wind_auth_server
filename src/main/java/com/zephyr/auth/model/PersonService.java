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
	
	/**
	 * 덮어 쓰는 용도의 업데이트 메소드
	 * @param id 문서 id
	 * @param newPerson 변경할 객체
	 * @return 변경한 객체
	 */
	public Person updatePerson(String id, Person newPerson) {
		
		Person p = mongoOps.findById(id, Person.class);
		if(p != null) {
			return mongoOps.save(newPerson);
		}
		return null;
	}

}

package com.zephyr.auth.model;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.time.LocalTime;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonService_bak {

	
	private MongoTemplate mongoOps;

	public PersonService_bak(MongoTemplate mongoOps) {
		this.mongoOps = mongoOps;
	}

	public Person insertPerson(Person p) {
		p.setRegisterDate(LocalTime.now());
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
	 * 
	 * @param id        문서 id
	 * @param newPerson 변경할 객체
	 * @return 변경한 객체
	 */
	public Person updatePerson(String id, Person newPerson) {

		Person p = mongoOps.findById(id, Person.class);
		if (p != null) {
			return mongoOps.save(newPerson);
		}
		return null;
	}

	/**
	 * 비밀번호 변경
	 */
	public Person changePassword(String id, String oldPwd, String newPwd) {
		final String key = "password";
		final String key2 = "id";
		Query criteria = new Query(Criteria.where(key2).is(id).and(key).is(oldPwd));
		Update update = new Update().set(key, newPwd);

		Person p = mongoOps.update(Person.class).matching(criteria).apply(update).findAndModifyValue();

		return p;
	}

	public Person findById(String id) {
		// TODO Auto-generated method stub
		return mongoOps.findById(id, Person.class);
	}

	public Person findByIdAndPassword(Person p) {

		Query query = new Query();
		Criteria c = Criteria.where("id").is(p.getId()).and("password").is(p.getPassword());
		query.addCriteria(c);
		
		return mongoOps.findOne(query, Person.class);
	}

	/**
	 * 로그아웃 함수
	 * @param string 로그아웃 id
	 * @return 성공적으로 로그아웃 했을 경우 true
	 */
	public boolean signOut(String string) {
		// TODO: 1. 로그인 히스토리 db 생성 시 내용 기록
		
		// TODO: 2. spring security 적용 후 인증토큰 생성 시 해당 토큰 파기
		return true;
	}

	public Person modifyPerson(String id, Person changePerson) {
		// TODO: 입력한 값이 null이 아닌 값만 변경해야 함.
		
		
		
		return null;
	}

}

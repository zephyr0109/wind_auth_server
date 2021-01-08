package com.zephyr.auth.model;

import java.time.LocalTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PersonService {

	private PersonRepository personRepository;
	
	private PasswordEncoder encoder;

	public PersonService(PersonRepository personRepository, PasswordEncoder encoder) {
		this.personRepository = personRepository;		
		this.encoder = encoder;		
	}

	/**
	 * 사용자 추가
	 * @param p  등록할 사용자 정보
	 * @return 등록한 사용자
	 */
	public Person insertPerson(Person p) {
		p.setRegisterDate(LocalTime.now());
		p.setPassword(encoder.encode(p.getPassword()));
		
		
		Person result = personRepository.insert(p);
		log.debug("get person info, id : " + result.getId());
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
		
		Person p = personRepository.findById(id).orElse(null);;
		if (p != null) {			
			return personRepository.save(newPerson);
		}
		return null;
	}

	/**
	 *  비밀번호 변경
	 * @param id	변경할 사용자 id
	 * @param oldPwd 기존 비밀번호. 유효성 확인
	 * @param newPwd 변경할 비밀번호
	 * @return
	 */
	public Person changePassword(String id, String oldPwd, String newPwd) {				
		Person p = personRepository.findById(id).orElseThrow();
		if(encoder.matches(oldPwd, p.getPassword())) {
			p.setPassword(encoder.encode(newPwd));
			return personRepository.save(p);
		}

		return p;
	}

	public Person findById(String id) {
		return personRepository.findById(id).orElse(null);
	}

	public Person findByIdAndPassword(Person p) {
		Person foundPerson = personRepository.findById(p.getId()).orElseThrow();
		log.info(foundPerson.toString());		
		if(encoder.matches(p.getPassword(), foundPerson.getPassword())) {
			return foundPerson;
		} else {
			// TODO : 전용 excpetion 생성할 것			
			throw new RuntimeException("not found excpetion");
		}
		
		
	}

	public Person modifyPerson(String id, Person changePerson) {
		// TODO: 입력한 값이 null이 아닌 값만 변경해야 함.
		
		return personRepository.save(changePerson);
	}

}

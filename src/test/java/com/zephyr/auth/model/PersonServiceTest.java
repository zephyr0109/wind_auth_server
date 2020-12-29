package com.zephyr.auth.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes = { PersonService.class })
@ContextConfiguration(classes = { MongoConfigForTest.class })
@ExtendWith(SpringExtension.class)
class PersonServiceTest {

	@Autowired
	PersonService personService;

	@Autowired
	MongoTemplate mongoOps;

	@Autowired
	PersonRepository personRepository;

	@Test
	void test() {
		// fail("Not yet implemented");
		log.info("init test");
		assertThat(personService).isNotNull();
	}

	@Test
	void insertPerseon() {
		mongoOps.dropCollection(Person.class);

		Person p = Person.builder().id("tuser").name("zep").email("star8076@hanmail.net").password("test1").build();
		Group group = new Group();
		group.setGroupId("tid1");
		group.setName("test group");
		p.setGroup(group);
		Person result = personService.insertPerson(p);
		assertThat(p.getName()).isEqualTo(result.getName());
		log.info(result.toString());

	}

	@Test
	void changePassword() {
		String newPwd = "test2";
		final String oldPwd = "test1";
		Person newPerson = personService.changePassword("tuser", oldPwd, newPwd);
		log.debug("old person : " + newPerson.toString());
		assertThat(newPerson.getPassword()).isEqualTo(newPwd);		
	}

	@Test
	void signIn() {
		Person p = Person.builder().id("tuser").password("test1").build();
		Person result = personService.findByIdAndPassword(p);
		assertThat(result).isNotNull();
		assertThat(result.getId()).isEqualTo(p.getId());
		p.setPassword("wrongPwd");
		result = personService.findByIdAndPassword(p);
		assertThat(result).isNull();
	}

	@Test
	void signOut() {

		boolean flag = personService.signOut("test1");
		assertThat(flag).isTrue();
	}

	@Test
	void modifyPerson() {
		Person changePerson = personService.findById("tuser");

		changePerson.setEmail("change@hanmail.net");
		Person newPerson = personService.modifyPerson("tuser", changePerson);
		assertThat(changePerson.getEmail()).isEqualTo(newPerson.getEmail());
		assertThat(changePerson.getId()).isEqualTo(newPerson.getId());

	}

}

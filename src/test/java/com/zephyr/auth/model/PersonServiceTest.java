package com.zephyr.auth.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class PersonServiceTest {

	@Autowired
	PersonService personService;

	@Test
	void test() {
		// fail("Not yet implemented");
		log.info("init test");
		assertNotNull(personService);
	}

	@Test
	void insertPerseon() {
		Person p = Person.builder().name("zep").email("star8076@hanmail.net").build();
		Person result = personService.insertPerson(p);
		assertThat(p.getName()).isEqualTo(result.getName());
		log.info(result.getId());

	}

}

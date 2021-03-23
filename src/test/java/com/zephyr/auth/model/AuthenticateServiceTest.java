package com.zephyr.auth.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes = { AuthenticateService.class })
@ContextConfiguration(classes = { MongoConfigForTest.class ,WebSecurityConfigForTest.class})
@ExtendWith(SpringExtension.class)
class AuthenticateServiceTest {

	@Autowired
	AuthenticateService authenticateService;
	
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	@Test
	void singIn() {
		Person p = Person.builder().id("star8076").password("test1234").build();		
		String jwt = authenticateService.signIn(p );
		log.info("jwt : " + jwt);		
	}
	
	
	@Test
	void signOut() {

		boolean flag = authenticateService.signOut("test1");
		assertThat(flag).isTrue();
	}

}

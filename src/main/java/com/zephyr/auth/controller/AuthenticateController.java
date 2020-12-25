package com.zephyr.auth.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zephyr.auth.model.Person;
import com.zephyr.auth.model.PersonService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/authenticate")
public class AuthenticateController {

	private PersonService personService;
	
	@Autowired
	public AuthenticateController(PersonService personService) {
		this.personService = personService;
	}
	
	

	@GetMapping("/test")
	public ResponseEntity<String> test() {
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<Person> signin(@RequestBody Map<String, Object> params){
		return null;
	}
	
	
	@GetMapping("/signout")
	public ResponseEntity<String> signout(@RequestParam("token") String token){
		log.info("signout call");
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
	
}

package com.zephyr.auth.model;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Person {

	@Id
	private String id;
	
	private String email;
	
	private String name;
	
	private Date birthday;
	
	private String phone;
	
	private String password;
	
	private Group group;
	
}

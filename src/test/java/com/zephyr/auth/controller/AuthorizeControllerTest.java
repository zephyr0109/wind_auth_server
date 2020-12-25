package com.zephyr.auth.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.zephyr.auth.model.PersonService;

@WebMvcTest(controllers = AuthorizeController.class)
class AuthorizeControllerTest {

	@Autowired
	private MockMvc mockMvc;


	@MockBean
	private PersonService personService;
	
	@Test
	void test() throws Exception {
		this.mockMvc.perform(get("/test")).andDo(print())
		.andExpect(status().isOk())
		.andExpect( content().string(containsString("test")));
	}

}

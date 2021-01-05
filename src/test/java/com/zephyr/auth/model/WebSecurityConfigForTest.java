package com.zephyr.auth.model;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;;

@EnableWebSecurity
public class WebSecurityConfigForTest extends WebSecurityConfigurerAdapter {

    
    @Bean
    public PasswordEncoder passwordEncoder() {    	
    	return new BCryptPasswordEncoder(16);
    }

}

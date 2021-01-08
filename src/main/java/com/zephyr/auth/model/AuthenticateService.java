package com.zephyr.auth.model;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class AuthenticateService {

	/**
	 * 로그아웃 함수
	 * 
	 * @param string 로그아웃 id
	 * @return 성공적으로 로그아웃 했을 경우 true
	 */
	public boolean signOut(String string) {
		// TODO: 1. 로그인 히스토리 db 생성 시 내용 기록

		// TODO: 2. spring security 적용 후 인증토큰 생성 시 해당 토큰 파기
		return true;
	}

	public String signIn(Person p) {

		Map<String, Object> headers = new HashMap<>();
		headers.put("typ", "JWT");
		headers.put("alg", "HS512");

		Map<String, Object> claims = new HashMap<>();
		claims.put("iss", "windauth");

		String jwt = Jwts.builder().setHeader(headers).setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, "testkeys")
				.compact();

		return jwt;
	}
}

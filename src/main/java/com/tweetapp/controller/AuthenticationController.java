package com.tweetapp.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.model.User;
import com.tweetapp.repository.UsersRepository;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthenticationController {

	@Autowired
	private UsersRepository usersRepository;

	@GetMapping("/authenticate")
	public HashMap<String, String> login(@RequestHeader(value = "Authorization") String loginString) {
		HashMap<String, String> response = new HashMap<>();
		String encoded = loginString.substring(6).toString();
		Base64.Decoder decoder = Base64.getMimeDecoder();
		String decodedValue = new String(decoder.decode(encoded));
		String token = generateJWT(decodedValue);
		String[] data = decodedValue.split(":");
		response.put("username", data[0]);
		User user = usersRepository.findByUsername(data[0]);
		if (user == null) {
			user = usersRepository.findByEmail(data[0]);
		}
		user.setLoggedin(true);
		usersRepository.save(user);
		response.put("role", "user");
		response.put("token", token);
		return response;
	}

	private String generateJWT(String decodedValue) {
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(decodedValue);
		builder.setIssuedAt(new Date());
		builder.setExpiration(new Date((new Date()).getTime() + 12000000));
		builder.signWith(SignatureAlgorithm.HS256, "secretKey");
		return builder.compact();
	}
}

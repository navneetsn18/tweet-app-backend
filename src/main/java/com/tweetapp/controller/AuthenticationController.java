package com.tweetapp.controller;

import static com.tweetapp.constants.Constants.INCORRECT_USERNAME_OR_PASS;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tweetapp.model.User;
import com.tweetapp.repository.UsersRepository;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin(origins = "*")
public class AuthenticationController {

	@Autowired
	private UsersRepository usersRepository;
	
	@GetMapping("/authenticate")
	public Map<String, String> authenticate(@RequestHeader(value = "Authorization") String authHeader) {
		HashMap<String, String> map = new HashMap<>();
		String user = getUser(authHeader);
		String[] data = user.split(":");
		User users = usersRepository.findByUsername(data[0]);
		if(users == null) {
			users = usersRepository.findByEmail(data[0]);
			if(users==null) {
				map.put("message",INCORRECT_USERNAME_OR_PASS);
			}
		}
		if(null != users && encoder().matches(data[1], users.getPassword())) {
			String token = generateJwt(user);
			users.setLoggedin(true);
			usersRepository.save(users);
			map.put("user", users.getUsername());
			map.put("Role", "user");
			map.put("token", token);
		}else {
			map.put("message",INCORRECT_USERNAME_OR_PASS);
		}
		return map;
	}

	private String getUser(String authHeader) {
		String encoded = authHeader.substring(6).toString();
		Base64.Decoder decoder = Base64.getMimeDecoder();
		String decoded = new String(decoder.decode(encoded));
		return decoded;
	}

	private String generateJwt(String user) {
		JwtBuilder builder = Jwts.builder();
		builder.setSubject(user);
		builder.setIssuedAt(new Date());
		builder.setExpiration(new Date((new Date()).getTime() + 6000000));
		builder.signWith(SignatureAlgorithm.HS256, "secretkey");
		String token = builder.compact();
		return token;

	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}

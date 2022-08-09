package com.tweetapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tweetapp.model.User;
import com.tweetapp.repository.UsersRepository;

@Service
public class AppUserService implements UserDetailsService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = usersRepository.findByUsername(username);
		if (user == null) {
			user = usersRepository.findByEmail(username);
			if (user == null) {
				throw new UsernameNotFoundException(username);
			} else {
				AppUser appUser = new AppUser(user);
				return appUser;
			}
		}
		throw new UsernameNotFoundException(username);
	}

}

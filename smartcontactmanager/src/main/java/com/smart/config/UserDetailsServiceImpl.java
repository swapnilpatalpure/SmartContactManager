package com.smart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.smart.dao.UserRepository;
import com.smart.entities.User;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//fetching user from database
		User user = userRepository.getUserByUserName(username);
		System.out.println(username);
		
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		if(user==null) {
			System.out.println("111111111111");
			throw new UsernameNotFoundException("Could Not Found User!!");
			
		}
		
		CustomUserDetails customUserDetails=new CustomUserDetails(user);
		return customUserDetails;
	}

}

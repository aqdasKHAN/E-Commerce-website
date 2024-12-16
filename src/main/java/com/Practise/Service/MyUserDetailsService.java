package com.Practise.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Practise.DAO.UserRepository;
import com.Practise.Model.MyUserDetails;
import com.Practise.Model.User;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		User usr= userRepository.findUserByEmail(email).get();
		System.out.println("Username is: "+usr.getUsername());
		if(usr==null) 
			throw new UsernameNotFoundException("User 404");
		return new MyUserDetails(usr);
		
	}

	
	
}

package com.Practise.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.Practise.DAO.RoleRepository;
import com.Practise.DAO.UserRepository;
import com.Practise.Model.Roles;
import com.Practise.Model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	private RedirectStrategy  redirectStrategy=new DefaultRedirectStrategy(); 
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, 
			Authentication authentication) throws IOException, ServletException {  
		
		OAuth2AuthenticationToken token=(OAuth2AuthenticationToken)authentication;
		
		String emailToken=token.getPrincipal().getAttributes().get("email").toString();
		System.out.println("Email logged in via google: "+emailToken);
		
		Optional<User> existingUser = userRepository.findUserByEmail(emailToken);

	    if (existingUser == null || existingUser.isEmpty()) {
	        // User does not exist; create a new user
	        User user = new User();
	        user.setUsername(token.getPrincipal().getAttributes().get("given_name").toString());
	        user.setEmail(emailToken);

	        List<Roles> roles = new ArrayList<>();
	        roles.add(roleRepository.findById(2).orElseThrow(() -> 
	            new RuntimeException("Default role not found!")
	        ));
	        user.setRoles(roles);

	        userRepository.save(user);
	        System.out.println("New user created: " + emailToken);
	    } else {
	        System.out.println("User already exists: " + emailToken); 
	    }

	    response.sendRedirect("/user/shop/viewproduct/14");
	}

	
	
	
}

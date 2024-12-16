package com.Practise.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.Practise.Service.MyUserDetailsService;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity 
public class AppSecurityConfig {
	
	@Autowired
	public GoogleOAuth2SuccessHandler googleOAuth2SuccessHandler;
	
	@Bean
	public UserDetailsService userDetailsService(){
		return new MyUserDetailsService();
	}
		
	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider provider =new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return provider;
	}
	
	@Bean
	public  SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
					.headers(headers -> headers.cacheControl(cache -> cache.disable()))
					.csrf(csrf ->csrf.disable())
					.authorizeHttpRequests(auth -> auth
							.requestMatchers("/user/shop").permitAll()
						    .requestMatchers("/user/shop/**").hasRole("USER")   // Only USER can access
						    .requestMatchers("/admin/**").hasRole("ADMIN") // Only ADMIN can access
						    .anyRequest().authenticated()
							)
					.formLogin(form-> form
									.loginPage("/login")
//									.defaultSuccessUrl("/home", true) // Redirect to /home after successful login
						            .failureUrl("/login?error=true")  // Redirect to /login with error flag on failure
						            .permitAll()              // Allow everyone to access the login page
						     )
					.oauth2Login(auth-> auth
									.loginPage("/login")
									.successHandler(googleOAuth2SuccessHandler)
							)
					.logout(loggingout->loggingout
									.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
									.logoutSuccessUrl("/login")
									.invalidateHttpSession(true)
									.deleteCookies("JSESSIONID")
									);
		
		
		return httpSecurity.build();

	}


	
}
 
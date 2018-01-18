package nl.thuis.tutorial.springsecurity.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Set up Spring Security With annotations
 * 1. Set up class that extends from AbstractSecurityWebApplicationInitializer to initialize SpringSecurity
 * 2. Set up class that extends from WebSecurityConfigurerAdapter to configure SpringSecurity. Also add the annotations: @Configuration and @EnableWebSecurity
 * 3. Override method configure(authenticationManagerBuilder) in Spring Configure Class
 * 4. Let Spring componentscan scan this class
 * @author ronald
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final String ROOT = "/";
	private static final String LEADERS_PAGE = "/leaders/**";
	private static final String ADMIN_PAGE = "/admin/**";
	private static final String ACCESS_DENIED_PAGE = "/access-denied";
	private static final String LOGIN_PAGE = "/showLogin";
	private static final String LOGIN_PROCESSING_URL = "/authenticate";
	
	private static final String ROLE_EMPLOYEE = "EMPLOYEE";
	private static final String ROLE_MANAGER = "MANAGER";
	private static final String ROLE_ADMIN = "ADMIN";

	/**
	 * This method is for configuring users (in memory, database, ldap, etc)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		
		// Since Spring 5 the PasswordEncoder must always be used. Users are build via the UserBuilder
		UserBuilder users = User.withDefaultPasswordEncoder();
		
		// Adding users to in-memory authentication
		auth.inMemoryAuthentication().withUser(users.username("Ronald").password("100292").roles(ROLE_EMPLOYEE, ROLE_ADMIN).build())
									 .withUser(users.username("Lars").password("100292").roles(ROLE_EMPLOYEE, ROLE_MANAGER).build())
									 .withUser(users.username("Michiel").password("100292").roles(ROLE_EMPLOYEE).build());
		
		// This is another alternative. Create password encoder and use this to encrypt password and add to the in-memory authentication
		PasswordEncoder pwe = new BCryptPasswordEncoder();
		String password = pwe.encode("100292");
		auth.inMemoryAuthentication().passwordEncoder(pwe).withUser("Jeroen").password(password).roles(ROLE_EMPLOYEE);
	}
	
	/**
	 * This method is for configuring security of web paths of the application (login, logout, etc)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers(ROOT).hasRole(ROLE_EMPLOYEE)			// give permission to pages for certain roles
				.antMatchers(LEADERS_PAGE).hasRole(ROLE_MANAGER)
				.antMatchers(ADMIN_PAGE).hasRole(ROLE_ADMIN)
			.and()
			.formLogin()
				.loginPage(LOGIN_PAGE)
				.loginProcessingUrl(LOGIN_PROCESSING_URL)
				.permitAll()					// login page is permitted for everyone
			.and()
			.logout().permitAll()				// logout page permitted for everyone
			.and()
			.exceptionHandling().accessDeniedPage(ACCESS_DENIED_PAGE);		// Adding access denied page
	}
	
}

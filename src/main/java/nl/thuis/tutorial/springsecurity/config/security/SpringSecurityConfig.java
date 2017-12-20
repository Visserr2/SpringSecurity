package nl.thuis.tutorial.springsecurity.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
	
	private static final String LOGIN_PAGE = "/showLogin";
	private static final String LOGIN_PROCESSING_URL = "/authenticate";

	/**
	 * This method is for configuring users (in memory, database, ldap, etc)
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// Add users for in-memory authentication
		auth.inMemoryAuthentication().withUser("Ronald").password("100292").roles("EMPLOYEE");		
		auth.inMemoryAuthentication().withUser("Lars").password("100292").roles("MANAGER");		
		auth.inMemoryAuthentication().withUser("Michiel").password("100292").roles("EMPLOYEE");
	}
	
	/**
	 * This method is for configuring security of web paths of the application (login, logout, etc)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage(LOGIN_PAGE)
				.loginProcessingUrl(LOGIN_PROCESSING_URL)
				.permitAll();
	}
	
}

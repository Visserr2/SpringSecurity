package nl.thuis.tutorial.springsecurity.config.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Set up Spring Security With annotations
 * 1. Set up class that extends from AbstractSecurityWebApplicationInitializer to initialize SpringSecurity
 * 2. Set up class that extends from WebSecurityConfigurerAdapter to configure SpringSecurity. Also add the annotations: @Configuration and @EnableWebSecurity
 * 3. Override method configure(authenticationManagerBuilder) in Spring Configure Class
 * 4. Let Spring component-scan scan this class
 * @author ronald
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}

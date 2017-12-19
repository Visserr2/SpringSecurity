package nl.thuis.tutorial.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration // Its a configuration class
@EnableWebMvc  // This annotation is equal to mvc:annotation-driven tag
@ComponentScan(basePackages= {"nl.thuis.tutorial.springsecurity.controller", "nl.thuis.tutorial.springsecurity.config.security" })
public class SpringConfig {

	private static final String RESOLVER_PREFIX = "/WEB-INF/view/";
	private static final String RESOLVER_SUFFIX = ".jsp";
	
	/**
	 * 	This bean is the view resolver that looks for the jsp-files
	 * @return ViewResolver-Object
	 */
	@Bean
	public ViewResolver viewResolver() {
		return new InternalResourceViewResolver(RESOLVER_PREFIX, RESOLVER_SUFFIX);
	}
}

package nl.thuis.tutorial.springsecurity.config;

import java.beans.PropertyVetoException;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration // Its a configuration class
@EnableWebMvc  // This annotation is equal to mvc:annotation-driven tag
@ComponentScan(basePackages= {"nl.thuis.tutorial.springsecurity.controller", "nl.thuis.tutorial.springsecurity.config.security" })
@PropertySource("classpath:db.properties") // This annotations reads the property file
public class SpringConfig {

	private static final String RESOLVER_PREFIX = "/WEB-INF/view/";
	private static final String RESOLVER_SUFFIX = ".jsp";
	
	private static final String JDBC_DRIVER =  "jdbc.driver";
	private static final String JDBC_URL = "jdbc.url";
	private static final String JDBC_USER = "jdbc.user";
	private static final String JDBC_PASSWORD =  "jdbc.password";
	
	private static final String CON_INIT_POOL_SIZE = "connection.pool.initialPoolSize";
	private static final String CON_MIN_POOL_SIZE = "connection.pool.minPoolSize";
	private static final String CON_MAX_POOL_SIZE = "connection.pool.maxPoolSize";
	private static final String CON_MAX_IDLE_TIME = "connection.pool.maxIdleTime";

	// Set variable that holds the properties from property file
	@Autowired
	private Environment env;
	
	// Set logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());
	
	/**
	 * 	This bean is the view resolver that looks for the jsp-files
	 * @return ViewResolver-Object
	 */
	@Bean
	public ViewResolver viewResolver() {
		return new InternalResourceViewResolver(RESOLVER_PREFIX, RESOLVER_SUFFIX);
	}
	
	/**
	 * This bean is the security datasource
	 */
	@Bean
	public DataSource securityDataSource() {
		// Create connection pool
		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		
		// set jdbc driver class
		try {
			securityDataSource.setDriverClass(env.getProperty(JDBC_DRIVER));
		} catch (PropertyVetoException e) {
			logger.warning("Cannot set the driver class!");
			throw new RuntimeException(e);
		}
		
		// log connections props
		logger.info(">>> jdbc.url=" + env.getProperty(JDBC_DRIVER));
		logger.info(">>> jdbc.url=" + env.getProperty(JDBC_USER));
		
		logger.info("Connecting to database");
		// set database connections props
		securityDataSource.setJdbcUrl(env.getProperty(JDBC_URL));		
		securityDataSource.setUser(env.getProperty(JDBC_USER));		
		securityDataSource.setPassword(env.getProperty(JDBC_PASSWORD));		
		
		logger.info("Set up connection pool");
		// set connection pool connection props
		securityDataSource.setInitialPoolSize(getIntProperty(CON_INIT_POOL_SIZE));
		securityDataSource.setMinPoolSize(getIntProperty(CON_MIN_POOL_SIZE));
		securityDataSource.setMaxPoolSize(getIntProperty(CON_MAX_POOL_SIZE));
		securityDataSource.setMaxIdleTime(getIntProperty(CON_MAX_IDLE_TIME));
		
		return securityDataSource;
	}
	
	
	/**
	 * A helper method to convert environment property to int
	 **/
	private int getIntProperty(String propName) {
		String propValue = env.getProperty(propName);
		return Integer.parseInt(propValue);
	}
}

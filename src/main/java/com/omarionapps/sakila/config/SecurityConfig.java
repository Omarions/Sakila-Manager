package com.omarionapps.sakila.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Customize the Web Security strategy
 * 
 * @author Omar
 *
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//create in memory authentication credentials with username 'admin' and password 'admin'
		auth
		.inMemoryAuthentication()
		.withUser("admin")
		.password("{noop}admin")
		.roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//customize authorization to authorize every request with role 'USER' and customized login form also implement logout process.
		http
		.authorizeRequests()
			.antMatchers("/**")
			.hasRole("USER")
			.anyRequest()
			.authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.failureUrl("/login?error=true")
			.defaultSuccessUrl("/home")
			.permitAll()
			.and()
		.logout()
			.logoutSuccessUrl("/login?logout")
			.permitAll();
	}	
	
	@Override
	public void configure(WebSecurity web) {
		//ignore these paths from authentication and authorization
		web
				.ignoring()
				.antMatchers("/resource/**", "/static/**", "/lib/**", "/plugins/**", "/css/**", "/js/**", "/img/**");
	}
	
	

}

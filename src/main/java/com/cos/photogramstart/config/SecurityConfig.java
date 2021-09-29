 package com.cos.photogramstart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.photogramstart.config.oauth.OAuth2DetailsService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	private final OAuth2DetailsService oAuth2DetailsService;
	
	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/", "/user/**", "/image/**", "/subscribe/**", ".comment/**","/api/**")
				.authenticated()
				.anyRequest()
				.permitAll()
				.and()
				.formLogin()
				.loginPage("/auth/signin") //GET
				.loginProcessingUrl("/auth/signin") //POST -> 스프링 시큐리티가 로그인 프로세스 진행
				.defaultSuccessUrl("/")
				.and()
				.oauth2Login() // form로그인도 하는데 , oauth2로그인도 할거임.
				.userInfoEndpoint() //oauth2 로그인을 하면 최종 응답을 회원정보로 바로 받을 수 있다.
				.userService(oAuth2DetailsService);

		
	}

}

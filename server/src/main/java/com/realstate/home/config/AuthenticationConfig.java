package com.realstate.home.config;

import com.realstate.home.config.filter.JwtTokenFilter;
import com.realstate.home.exception.CustomAuthenticationEntryPoint;
import com.realstate.home.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//비밀번호 암호화 BCrypt 때문에 security gradle 주석을 풀었음 > 그래서 작성해줌 > 어떤 유저든 허용되게 만들어줌
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;

    @Value("${jwt.secret-key}")
    private String key;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.csrf().disable()
               .authorizeRequests()
               .antMatchers("/api/*/members/signup", "/api/*/members/login").permitAll()
//               .antMatchers("/api/**").authenticated()
               .and()
               .sessionManagement()
               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and()
               .addFilterBefore(new JwtTokenFilter(key, memberService), UsernamePasswordAuthenticationFilter.class)
//               .exceptionHandling()
//               .authenticationEntryPoint(new CustomAuthenticationEntryPoint)
       ;

    }
}

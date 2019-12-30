package com.ditraacademy.travelagency.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final String [] PUBLIC_ENDPOINTS = {
         "/hotel**/**",
            "/voyage**/**",
            "/upload**/**",
            "/**"
    };
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http

                .cors()
                .and()
                .csrf().disable() // bloque par default tous les requette
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // we don't nees session because we gonna use JWT
                .and()
                .authorizeRequests() //  i m gonna give the routes and define id authorised or no
                .antMatchers("/auth**/**").permitAll()
                .antMatchers(HttpMethod.GET,PUBLIC_ENDPOINTS).permitAll() // give access
                .anyRequest().authenticated() // other request we need to be logged in
                .and()
                .addFilterBefore(authFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    @Bean
    protected AuthFilter authFilter(){return  new AuthFilter();}


}

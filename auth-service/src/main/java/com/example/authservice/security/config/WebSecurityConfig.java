package com.example.authservice.security.config;

import com.example.authservice.security.auth.TokenAuthenticationFilter;
import com.example.authservice.security.auth.RestAuthenticationEntryPoint;
import com.example.authservice.security.util.TokenUtils;
import com.example.authservice.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService, RestAuthenticationEntryPoint restAuthenticationEntryPoint, TokenUtils tokenUtils) {
        this.customUserDetailsService = customUserDetailsService;
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
        this.tokenUtils = tokenUtils;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    private final PasswordEncoder passwordEncoder;

    private final CustomUserDetailsService customUserDetailsService;

    private final RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    private final TokenUtils tokenUtils;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()

                .authorizeRequests().antMatchers("/api/v1/users/**").permitAll()
                .antMatchers("/api/v1/auth/**").permitAll()// /auth/**
                .antMatchers("/api/v1/users").permitAll()
                .antMatchers("/api/v1/merchant").permitAll()
                .antMatchers("/api/v1/payment-method").permitAll()
                .antMatchers("/api/v1/bank").permitAll()
                .antMatchers("/api/v1/payment-request").permitAll()
                .antMatchers("/api/v1/payment-request/**").permitAll()
                .antMatchers("/api/v1/credentials/payment-request/**").permitAll()
                .antMatchers("/api/v1/credentials/process-payment/**").permitAll()
                .antMatchers("/api/v1/subscription-request").permitAll()
                .antMatchers("/api/v1/subscription-request/**").permitAll()
                .anyRequest().authenticated().and()

                .cors().and()

                .addFilterBefore(new TokenAuthenticationFilter(tokenUtils, customUserDetailsService), BasicAuthenticationFilter.class);

        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers(HttpMethod.POST, "/api/v1/users");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/v1/users/login");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/v1/merchant");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/v1/payment-request");
        web.ignoring().antMatchers(HttpMethod.POST, "/api/v1/subscription-request");
        web.ignoring().antMatchers(HttpMethod.GET, "/api/v1/credentials/payment-request/**");
        web.ignoring().antMatchers(HttpMethod.GET, "/api/v1/credentials/process-payment/**");
        web.ignoring().antMatchers(HttpMethod.GET, "/api/v1/payment-method");
        web.ignoring().antMatchers(HttpMethod.GET, "/api/v1/bank");

        web.ignoring().antMatchers(HttpMethod.GET, "/", "/webjars/**", "/*.html", "favicon.ico", "/**/*.html",
                "/**/*.css", "/**/*.js");
    }
}

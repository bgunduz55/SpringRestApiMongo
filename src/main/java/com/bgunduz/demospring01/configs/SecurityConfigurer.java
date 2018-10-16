/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bgunduz.demospring01.configs;

import com.bgunduz.demospring01.services.CheckUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 *
 * @author bgunduz
 */

@Configuration
@EnableConfigurationProperties
@CrossOrigin(origins = "*")
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
  @Autowired
  CheckUserService userDetailsService;
  
    @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf().disable().cors().and()
      .authorizeRequests().antMatchers("/auth/*").permitAll()
            .anyRequest().authenticated()
      .and().httpBasic()
      .and().sessionManagement().disable();
  }
 
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
 
  @Override
  public void configure(AuthenticationManagerBuilder builder) throws Exception {
    builder.userDetailsService(userDetailsService);
  }
  
}

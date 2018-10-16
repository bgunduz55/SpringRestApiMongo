/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bgunduz.demospring01.controllers;

import com.bgunduz.demospring01.models.AuthUser;
import com.bgunduz.demospring01.repositories.AuthUserRepository;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author bgunduz
 */

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    @Autowired
    AuthUserRepository authUserRepository;
    
    @PostMapping("/login")
    public AuthUser login(@RequestParam("username") String username, @RequestParam("password") String password) {
        AuthUser user = authUserRepository.findByUsername(username);
        if(user == null) {
            throw new  UsernameNotFoundException("User not found");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if(passwordEncoder.matches(password, user.getPassword())) {
            user.setToken("Basic " + Base64.encode((user.getUsername() + ":" + password).getBytes()) );
            return user;
        } else {
            return null;
        }
    }
    
    @GetMapping("/test")
    public String test(){
        return "success";
    }
}

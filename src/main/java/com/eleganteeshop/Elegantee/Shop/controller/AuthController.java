package com.eleganteeshop.Elegantee.Shop.controller;

import com.eleganteeshop.Elegantee.Shop.Dto.LoginDto;
import com.eleganteeshop.Elegantee.Shop.service.JwtService;
import com.eleganteeshop.Elegantee.Shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthenticationManager authManager;
    private final UserService userService;
    private final JwtService jwtService;

    private final Logger loggerFactory = LoggerFactory.getLogger(this.getClass());
    public AuthController(AuthenticationManager authManager, UserService userService, JwtService jwtService) {
        this.authManager = authManager;
        this.userService = userService;
        this.jwtService = jwtService;
    }


    @PostMapping("/")
    public String authentication(@RequestBody LoginDto loginInfo){
        Authentication authenticate = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginInfo.username(), loginInfo.password()));

        if(authenticate.isAuthenticated()){
            UserDetails userDetails = userService.loadUserByUsername(loginInfo.username());
            return jwtService.generateToken(userDetails);
        }
        return loginInfo.username();
    }
}

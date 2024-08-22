package com.eleganteeshop.Elegantee.Shop.controller;

import com.eleganteeshop.Elegantee.Shop.entities.UserEntity;
import com.eleganteeshop.Elegantee.Shop.repository.UserRepository;
import com.eleganteeshop.Elegantee.Shop.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    public UserController(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @GetMapping("/all")
    public Collection<UserEntity> allUsers(){
        return userService.allUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<Object> createAccount(@RequestBody UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        UserEntity savedUser = userService.createNewUser(user);

        //provide location of the newly created user
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/user/{username}")
                .buildAndExpand(savedUser.getUsername())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping("/{username}")
    public Optional<UserEntity> userDetails(@PathVariable String username){
        return userService.findUserByUsername(username);
    }


}

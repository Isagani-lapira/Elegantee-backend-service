package com.eleganteeshop.Elegantee.Shop.controller;

import com.eleganteeshop.Elegantee.Shop.Dto.RegistrationBaseDTO;
import com.eleganteeshop.Elegantee.Shop.entities.UserEntity;
import com.eleganteeshop.Elegantee.Shop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

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
    public ResponseEntity<Object> createAccount(@Valid @RequestBody RegistrationBaseDTO newUser, BindingResult result){

        if(result.hasErrors()){
            //provide object of validation fields and its message to show the errors
            Map<String,String> fieldErrors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage
                    ));

            return ResponseEntity.badRequest().body(fieldErrors);
        }

        //encrypt password
        String encryptedPassword = passwordEncoder.encode(newUser.getUser().getPassword());
        newUser.getUser().setPassword(encryptedPassword);

        UserEntity savedUser = userService.createNewUser(newUser);

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

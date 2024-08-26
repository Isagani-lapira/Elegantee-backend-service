package com.eleganteeshop.Elegantee.Shop.controller;

import com.eleganteeshop.Elegantee.Shop.entities.AccountDetails;
import com.eleganteeshop.Elegantee.Shop.repository.AccountRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountDetailController {
    private final AccountRepository repository;

    public AccountDetailController(AccountRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/{username}")
    @PreAuthorize("#username == authentication.name")
    public AccountDetails accountDetails(@PathVariable String username){
        return repository.findByUsername(username);
    }

}

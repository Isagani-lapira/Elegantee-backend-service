package com.eleganteeshop.Elegantee.Shop.repository;

import com.eleganteeshop.Elegantee.Shop.entities.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<AccountDetails,Long> {
    AccountDetails findByUsername(String username);
}

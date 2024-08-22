package com.eleganteeshop.Elegantee.Shop.repository;

import com.eleganteeshop.Elegantee.Shop.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    Optional<UserEntity>findByUsername(String username);
}

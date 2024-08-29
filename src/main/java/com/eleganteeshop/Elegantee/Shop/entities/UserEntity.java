package com.eleganteeshop.Elegantee.Shop.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @NotEmpty
    @Size(min = 7, max = 55)
    private String username;
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$",
            message = "Password must be at least 8 characters long, contain at least one uppercase letter, one lowercase letter, one number, and one special character."
    )
    private String password;
    @NotNull
    private String roles;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private AccountDetails accountDetails;

    public UserEntity(){}
    public UserEntity(String username, String password, String roles, AccountDetails accountDetails) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.accountDetails = accountDetails;
    }
}

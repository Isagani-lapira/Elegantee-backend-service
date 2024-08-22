package com.eleganteeshop.Elegantee.Shop.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\\\d)(?=.*[@$!%*?&])[A-Za-z\\\\d@$!%*?&]{8,}$")
    private String password;
    @NotNull
    private String roles;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AccountDetails accountDetails;

    public UserEntity(){}
    public UserEntity(String username, String password, String roles, AccountDetails accountDetails) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.accountDetails = accountDetails;
    }
}

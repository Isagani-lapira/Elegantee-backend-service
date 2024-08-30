package com.eleganteeshop.Elegantee.Shop.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountDetailDTO {

    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;
    @Email
    private String emailAddress;
    @Past
    private LocalDate birthDate;
}

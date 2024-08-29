package com.eleganteeshop.Elegantee.Shop.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class AccountDetailDTO {
    private String firstname;
    private String lastname;
    private String emailAddress;
    private LocalDate birthDate;
}

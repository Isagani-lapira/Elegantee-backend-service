package com.eleganteeshop.Elegantee.Shop.Dto;

import com.eleganteeshop.Elegantee.Shop.entities.UserEntity;
import jakarta.validation.Valid;
import lombok.Data;


@Data
public class RegistrationBaseDTO {

    @Valid
    private UserEntity user;
    @Valid
    private AccountDetailDTO accountDetails;
}

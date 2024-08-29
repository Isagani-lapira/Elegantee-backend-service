package com.eleganteeshop.Elegantee.Shop.Dto;

import com.eleganteeshop.Elegantee.Shop.entities.UserEntity;
import lombok.Data;


@Data
public class RegistrationBaseDTO {

    private UserEntity user;
    private AccountDetailDTO accountDetails;
}

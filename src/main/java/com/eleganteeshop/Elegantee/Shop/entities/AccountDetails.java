package com.eleganteeshop.Elegantee.Shop.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class AccountDetails {

    @Id
    @NotEmpty
    @Size(min = 7, max = 55)
    private String username;

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @Email
    private String emailAddress;
    @Past
    private LocalDate birthDate;
    @Null
    private int houseNo;
    private String streetName;
    private String barangay;
    private String city;
    private String state;
    private int postalCode;
    private String gender;
    @Size(min = 5)
    private String contactNo;

    @OneToOne
    @MapsId
    @JoinColumn(name="username",referencedColumnName = "username")
    @JsonIgnore
    private UserEntity userEntity;

    public AccountDetails(){}
    public AccountDetails(String username, String firstName, String lastName, String emailAddress,
                          LocalDate birthDate, int houseNo, String streetName, String barangay,
                          String city, String state, int postalCode, String gender, String contactNo,
                          UserEntity userEntity) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
        this.houseNo = houseNo;
        this.streetName = streetName;
        this.barangay = barangay;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.gender = gender;
        this.contactNo = contactNo;
        this.userEntity = userEntity;
    }
}


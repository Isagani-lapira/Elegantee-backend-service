package com.eleganteeshop.Elegantee.Shop.entities;


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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private Long account_id;
    @NotEmpty
    private String username; // Add this field

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @Email
    private String emailAddress;
    @Past
    private LocalDate birthDate;


    private String gender;
    @Size(min = 5)
    private String contactNo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressEntity address;

    public AccountDetails(){}
    public AccountDetails(String firstName, String lastName, String emailAddress,
                          LocalDate birthDate, String gender, String contactNo,
                          AddressEntity address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.birthDate = birthDate;
        this.gender = gender;
        this.contactNo = contactNo;
        this.address = address;
    }
}


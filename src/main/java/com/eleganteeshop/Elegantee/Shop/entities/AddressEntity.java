package com.eleganteeshop.Elegantee.Shop.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.Data;


@Data
@Table(name = "address")
@Entity
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private Long addressId;
    private Integer houseNo;
    private String streetName;
    private String barangay;
    private String city;
    private String state;
    private Integer postalCode;


    public AddressEntity(){}
    public AddressEntity(Long addressId, int houseNo, String streetName, String barangay, String city, String state, Integer postalCode) {
        this.addressId = addressId;
        this.houseNo = houseNo;
        this.streetName = streetName;
        this.barangay = barangay;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }
}

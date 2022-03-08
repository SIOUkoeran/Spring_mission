package com.example.mission3_basic.Area.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class Address2 {

    private String city;
    private String street;
    private String country;

    public Address2(String city, String street, String country) {
        this.city = city;
        this.street = street;
        this.country = country;
    }

    @Override
    public String toString() {
        return "Address2{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}

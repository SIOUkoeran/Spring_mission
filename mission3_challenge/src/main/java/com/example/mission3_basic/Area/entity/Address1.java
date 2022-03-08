package com.example.mission3_basic.Area.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor
public class Address1 {
    private String province;
    private String municipal;

    public Address1(String province, String municipal) {
        this.province = province;
        this.municipal = municipal;
    }

    @Override
    public String toString() {
        return "Address1{" +
                "province='" + province + '\'' +
                ", municipal='" + municipal + '\'' +
                '}';
    }
}

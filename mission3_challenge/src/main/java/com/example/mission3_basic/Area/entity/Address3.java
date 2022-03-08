package com.example.mission3_basic.Area.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
public class Address3 {
        private String neighborhood;
        private String township;
        private String town;

        public Address3(String neighborhood, String township, String town) {
                this.neighborhood = neighborhood;
                this.township = township;
                this.town = town;
        }

        @Override
        public String toString() {
                return "Address3{" +
                        "neighborhood='" + neighborhood + '\'' +
                        ", township='" + township + '\'' +
                        ", town='" + town + '\'' +
                        '}';
        }
}

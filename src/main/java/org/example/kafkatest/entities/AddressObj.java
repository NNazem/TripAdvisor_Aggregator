package org.example.kafkatest.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressObj {
    private String street1;
    private String city;
    private String state;
    private String country;
    private String postalcode;
    private String address_string;
}

package org.example.kafkatest.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document
public class Location {

    @Id
    private String location_id;
    private String name;
    private String distance;
    private String bearing;
    private AddressObj address_obj;
}

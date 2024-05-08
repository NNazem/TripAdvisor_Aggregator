package org.example.kafkatest.aggregations;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.kafkatest.entities.AddressObj;

@Getter
@Setter
@ToString
public class LocationAggregationResult {
    private String _id;
    private String name;
    private AddressObj address_obj;
    private String address_short;
}

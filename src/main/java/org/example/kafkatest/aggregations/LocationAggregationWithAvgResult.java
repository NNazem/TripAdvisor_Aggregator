package org.example.kafkatest.aggregations;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.kafkatest.entities.AddressObj;

@Getter
@Setter
@ToString
@Entity
public class LocationAggregationWithAvgResult {
    @Id
    private String _id;
    private String locationName;
    private String address_string;
    private Double avgRating;
}

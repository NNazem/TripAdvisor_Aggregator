package org.example.kafkatest.aggregations;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.kafkatest.entities.Review;

import java.util.List;

@ToString
@Getter
@Setter
public class ReviewAggregationResult {
    private String location_id;
    private Double avg_rating;
    private List<Review> reviews;
}

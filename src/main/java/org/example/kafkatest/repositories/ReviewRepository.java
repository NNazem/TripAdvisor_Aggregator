package org.example.kafkatest.repositories;

import org.example.kafkatest.aggregations.ReviewAggregationResult;
import org.example.kafkatest.entities.Review;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {

    @Query("{ 'location_id' : ?0 }")
    public List<Review> findReviewByLocation_id(Integer location_id);

    @Aggregation(pipeline = {
            "{ $addFields: { 'review': { '_id': '$_id', 'rating': '$rating', 'username': '$user.username', 'subrating': '$subratings' } } }",
            "{ $group: { '_id': '$location_id', 'avg_rating': { $avg: '$rating' }, 'reviews': { $push: '$$ROOT' } } }",
    })
    AggregationResults<ReviewAggregationResult> aggregateReviews();
}

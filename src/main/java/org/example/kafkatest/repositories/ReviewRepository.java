package org.example.kafkatest.repositories;

import org.example.kafkatest.entities.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {

    @Query("{ 'location_id' : ?0 }")
    public List<Review> findReviewByLocation_id(Integer location_id);
}

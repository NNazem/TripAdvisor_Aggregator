package org.example.kafkatest.services;

import lombok.AllArgsConstructor;
import org.example.kafkatest.aggregations.ReviewAggregationResult;
import org.example.kafkatest.entities.ReviewPostgres;
import org.example.kafkatest.repositories.ReviewRepository;
import org.example.kafkatest.entities.Review;
import org.example.kafkatest.repositories.ReviewRepositoryPostGres;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;
    private ModelMapper modelMapper;
    private ReviewRepositoryPostGres reviewRepositoryPostGres;

    public boolean saveReview(Review review) {
        try {
            reviewRepository.save(review);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Review> getReviewsByLocationId(String locationId) {
        return reviewRepository.findReviewByLocation_id(Integer.valueOf(locationId));
    }

    public List<ReviewAggregationResult> getReviewAggregationResult() {
        return reviewRepository.aggregateReviews().getMappedResults();
    }

    public String loadToPostgres(){
        List<Review> reviews = reviewRepository.findAll();
        reviews.stream().forEach(x -> reviewRepositoryPostGres.save(modelMapper.map(x, ReviewPostgres.class)) );

        return "Ok";
    }
}

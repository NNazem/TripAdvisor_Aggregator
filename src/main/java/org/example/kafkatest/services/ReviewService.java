package org.example.kafkatest.services;

import lombok.AllArgsConstructor;
import org.example.kafkatest.repositories.ReviewRepository;
import org.example.kafkatest.entities.Review;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    private ReviewRepository reviewRepository;

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
}

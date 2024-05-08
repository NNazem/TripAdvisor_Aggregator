package org.example.kafkatest.consumers;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.example.kafkatest.entities.Review;
import org.example.kafkatest.entities.ReviewData;
import org.example.kafkatest.services.ReviewService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class KafkaConsumerReview {

    ReviewService service;

    @KafkaListener(topics = "review", groupId = "group_id")

    public void consume(String message) throws IOException {
        Gson gson = new Gson();
        ReviewData reviewData = gson.fromJson(message, ReviewData.class);
        List<Review> reviews = reviewData.getData();

        for (Review review : reviews) {
            boolean success = service.saveReview(review);
            if(!success){
                throw new IOException();
            }
        }
    }
}
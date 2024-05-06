package org.example.kafkatest.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private String username;
    private UserLocation user_location;
    private Integer review_count;
    private String reviewer_badge;
}

package org.example.kafkatest.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class ReviewData {
    public List<Review> data;
}

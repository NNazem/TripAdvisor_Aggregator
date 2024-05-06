package org.example.kafkatest.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OwnerResponse {
    private Integer id;
    private String lang;
    private String text;
    private String title;
    private String author;
    private String published_date;
}

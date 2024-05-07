package org.example.kafkatest.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@ToString
@Document
public class Review {
    private Integer id;
    private String lang;
    private Integer location_id;
    private String published_date;
    private Integer rating;
    private Integer helpfulVotes;
    private String ratingImageUrl;
    private String url;
    private String tripType;
    private String travelDate;
    private String text;
    private String title;
    private OwnerResponse owner_response;
    private Boolean isMachineTranslated;
    private User user;
    private Map<String, Subrating> subratings;

}

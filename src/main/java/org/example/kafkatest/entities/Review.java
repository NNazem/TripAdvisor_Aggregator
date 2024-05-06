package org.example.kafkatest.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private Integer helpful_votes;
    private String rating_image_url;
    private String url;
    private String trip_type;
    private String travel_date;
    private String text;
    private String title;
    private OwnerResponse owner_response;
    private Boolean is_machine_translated;
    private User user;
    private Map<String, Subrating> subratings;

}

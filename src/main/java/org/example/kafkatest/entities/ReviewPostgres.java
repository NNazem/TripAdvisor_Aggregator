package org.example.kafkatest.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
@Entity
public class ReviewPostgres {
    @Id
    private Integer id;
    private Integer location_id;
    private String published_date;
    private Integer rating;
    private String url;
    @Column(columnDefinition="TEXT")
    private String text;
}

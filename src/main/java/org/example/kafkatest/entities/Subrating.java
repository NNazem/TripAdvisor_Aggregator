package org.example.kafkatest.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Subrating {
    private String name;
    private String rating_image_url;
    private Integer value;
    private String localized_name;
}

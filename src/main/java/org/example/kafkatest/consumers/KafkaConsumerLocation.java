package org.example.kafkatest.consumers;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import org.example.kafkatest.entities.Location;
import org.example.kafkatest.entities.LocationData;
import org.example.kafkatest.services.LocationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@AllArgsConstructor
public class KafkaConsumerLocation {

    LocationService service;

    @KafkaListener(topics = "location", groupId = "group_id")

    public void consume(String message) throws IOException {
        Gson gson = new Gson();
        LocationData locationData = gson.fromJson(message, LocationData.class);
        List<Location> locations = locationData.getData();

        for (Location location : locations) {
            boolean success = service.saveLocation(location);
            if(!success){
                throw new IOException();
            }
        }
    }
}

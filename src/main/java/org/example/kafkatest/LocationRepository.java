package org.example.kafkatest;

import org.example.kafkatest.entities.Location;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface LocationRepository extends MongoRepository<Location, String> {
}

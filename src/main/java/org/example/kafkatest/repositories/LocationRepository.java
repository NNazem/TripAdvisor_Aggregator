package org.example.kafkatest.repositories;

import org.example.kafkatest.entities.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, String> {
}

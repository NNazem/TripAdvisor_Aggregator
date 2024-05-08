package org.example.kafkatest.repositories;

import org.example.kafkatest.aggregations.LocationAggregationResult;
import org.example.kafkatest.entities.Location;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, String> {

    @Aggregation(pipeline = {
            "{$addFields: {'address_short' : {$concat : ['$address_obj.city', ' ', '$address_obj.country', ' ', '$address_obj.postalcode']}}}",
            "{$project: {'bearing':  0, '_class' :  0, 'distance' :  0}}"
    })
    AggregationResults<LocationAggregationResult> aggregateLocations();
}

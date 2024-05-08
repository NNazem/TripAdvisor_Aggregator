package org.example.kafkatest.repositories;

import org.example.kafkatest.aggregations.LocationAggregationResult;
import org.example.kafkatest.aggregations.LocationAggregationWithAvgResult;
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

    @Aggregation(pipeline = {
            "{ $addFields: { address_short: { $concat: ['$address_obj.city', ' ', '$address_obj.country', ' ', '$address_obj.postalcode'] } } }",
            "{ $lookup: { from: 'review', let: { locationIdAsInt: { $toInt: '$_id' } }, pipeline: [ { $match: { $expr: { $eq: ['$location_id', '$$locationIdAsInt'] } } } ], as: 'reviews' } }",
            "{ $addFields: { avg_review_rate: { $avg: '$reviews.rating' } } }",
            "{ $group: { _id: '$_id', avgRating: { $first: '$avg_review_rate' }, reviews: { $push: '$$ROOT' } } }",
            "{ $project: { _id: 1, avgRating: 1, locationName: { $arrayElemAt: ['$reviews.name', 0] }, address_string: { $arrayElemAt: ['$reviews.address_short', 0] } } }"
    })
    AggregationResults<LocationAggregationWithAvgResult>aggregateLocationWithAvg();

}

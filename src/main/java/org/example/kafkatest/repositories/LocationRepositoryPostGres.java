package org.example.kafkatest.repositories;


import org.example.kafkatest.aggregations.LocationAggregationWithAvgResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepositoryPostGres extends JpaRepository<LocationAggregationWithAvgResult, String> {
}

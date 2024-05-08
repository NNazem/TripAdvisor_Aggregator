package org.example.kafkatest.services;

import lombok.AllArgsConstructor;
import org.example.kafkatest.aggregations.LocationAggregationResult;
import org.example.kafkatest.aggregations.LocationAggregationWithAvgResult;
import org.example.kafkatest.repositories.LocationRepository;
import org.example.kafkatest.entities.Location;
import org.example.kafkatest.repositories.LocationRepositoryPostGres;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {

    private LocationRepository locationRepository;
    private LocationRepositoryPostGres locationRepositoryPostGres;

    public boolean saveLocation(Location location) {
        try{
            locationRepository.save(location);
            String location_id = location.getLocation_id();
            String url = "http://localhost:8080/reviews/publish?location_id=" + location_id;

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("accept", "application/json")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.discarding());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<LocationAggregationResult> getLocationAggregated() {
        return locationRepository.aggregateLocations().getMappedResults();
    }

    public String loadToPostgres(){
        List<LocationAggregationWithAvgResult> locations = locationRepository.aggregateLocationWithAvg().getMappedResults();
        locations.stream().forEach(x -> locationRepositoryPostGres.save(x));

        return "Ok";

    }

}

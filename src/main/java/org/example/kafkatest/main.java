package org.example.kafkatest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public class main {

    public static void main(String[] args) {

        try {
            for(int i = 0; i<10;i++) {


                Random random = new Random();

                Double latitude = random.nextDouble(33, 34);
                Double longitude = random.nextDouble(117, 118);

                String url = "http://localhost:8080/locations/publish?latitude=" + latitude + "&longitude=" + -longitude;
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("accept", "application/json")
                        .method("GET", HttpRequest.BodyPublishers.noBody())
                        .build();

                HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.discarding());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

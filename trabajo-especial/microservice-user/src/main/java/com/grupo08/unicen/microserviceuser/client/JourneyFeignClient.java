package com.grupo08.unicen.microserviceuser.client;

import com.grupo08.unicen.microserviceuser.model.JourneyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "microservice-journey")
public interface JourneyFeignClient {
    @GetMapping("/api/journeys/")
    public ResponseEntity<List<JourneyDto>> getAllJourneys();

    @PostMapping("/api/journeys")
    public ResponseEntity<String> createJourney();

}

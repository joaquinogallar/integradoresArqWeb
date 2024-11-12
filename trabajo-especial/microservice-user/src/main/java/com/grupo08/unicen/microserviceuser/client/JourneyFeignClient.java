package com.grupo08.unicen.microserviceuser.client;

import com.grupo08.unicen.microserviceuser.model.JourneyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "microservice-journey")
public interface JourneyFeignClient {
    @GetMapping("/api/journeys/")
    List<JourneyDto> getAllJourneys();
}

package com.grupo08.unicen.microservicejourney.client;

import com.grupo08.unicen.microservicejourney.model.StopDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "microservice-stop")
public interface StopFeignClient {
    @GetMapping("/api/stops/{stopId}")
    public ResponseEntity<StopDto> getStopById(@PathVariable UUID stopId);

    @GetMapping("/api/stops/{x}/{y}")
    ResponseEntity<StopDto> getStopByXY(@PathVariable int x, @PathVariable int y);
}

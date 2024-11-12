package com.grupo08.unicen.microserviceuser.client;

import com.grupo08.unicen.microservicemonopatin.entity.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient("MICROSERVICE-MONOPATIN")
public interface MonopatinFeignClient {
    @GetMapping("api/{monopatinId}")
    public ResponseEntity<Monopatin> getMonopatinById(@PathVariable UUID monopatinId);
}
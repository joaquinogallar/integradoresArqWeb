package com.grupo08.unicen.microserviceuser.client;

import com.grupo08.unicen.microserviceuser.model.MonopatinDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "MICROSERVICE-MONOPATIN", url = "http://localhost:8052")
public interface MonopatinFeignClient {
    @GetMapping("/api/monopatines/{monopatinId}")
    public ResponseEntity<MonopatinDto> getMonopatinById(@PathVariable UUID monopatinId);

    @GetMapping("/api/monopatines/nearMonopatines/{x}/{y}/{rangoMetros}")
    public ResponseEntity<List<MonopatinDto>>getNearMonopatines(@PathVariable int x, @PathVariable int y, @PathVariable int rangoMetros);

    @PutMapping("/api/monopatines/{monopatinID}")
    public ResponseEntity<MonopatinDto>editMonopatin(@PathVariable Long monopatinID);
}

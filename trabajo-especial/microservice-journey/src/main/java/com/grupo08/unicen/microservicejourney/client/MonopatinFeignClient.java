package com.grupo08.unicen.microservicejourney.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.grupo08.unicen.microservicejourney.model.MonopatinDTO;

@FeignClient(name = "monopatin-service", url = "http://localhost:8083")
public interface MonopatinFeignClient {
    @GetMapping("/monopatin/{idMonopatin}")
    MonopatinDTO getMonopatinById(@PathVariable Long idMonopatin);

    @GetMapping("/ubicacion/{id}")
    ResponseEntity<?>getUbicacion(@PathVariable Long idMonopatin);
}


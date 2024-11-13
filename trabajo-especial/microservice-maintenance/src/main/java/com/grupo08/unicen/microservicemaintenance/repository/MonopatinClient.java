package com.grupo08.unicen.microservicemaintenance.repository;

import com.grupo08.unicen.microservicemaintenance.dto.MonopatinDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "monopatin-service", url = "http://localhost:8081/")
public interface MonopatinClient {

    @GetMapping("/api/monopatines/maintenance")
    List<MonopatinDto> getMonopatinesInMaintenance();

    // Endpoint para obtener los monopatines activos
    @GetMapping("/api/monopatines/actives")
    List<MonopatinDto> getActivesMonopatines();
}

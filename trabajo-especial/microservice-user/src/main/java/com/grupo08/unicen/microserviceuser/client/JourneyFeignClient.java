package com.grupo08.unicen.microserviceuser.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@FeignClient("MICROSERVICE-JOURNEY")
public interface JourneyFeignClient {
    @GetMapping("/")
    public ResponseEntity<List<Viaje>> getAll();

    @GetMapping("/monopatines/viajes/{cant}/{anio}")
    public ResponseEntity<?> getMonopatinesByViaje(@PathVariable int cant, @PathVariable int anio);

    @PutMapping("/endViaje/{idViaje}")
    public ResponseEntity<?> endViaje(@PathVariable Long idViaje);

    @PostMapping("/crear/{monopatinId}/usuario/{usuarioId}")
    public void createViaje(@PathVariable("monopatinId") Long monopatinId, @PathVariable("usuarioId") Long usuarioId);
}

package com.grupo08.unicen.microservicejourney.client;




import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.grupo08.unicen.microservicejourney.model.UserDTO;

@FeignClient(name = "usuario-service", url = "http://localhost:8086")
public interface UserFeignClient {

    @GetMapping("/usuario/{idUsuario}")
    ResponseEntity<UserDTO> getUsuarioById(@PathVariable Long idUsuario);




}
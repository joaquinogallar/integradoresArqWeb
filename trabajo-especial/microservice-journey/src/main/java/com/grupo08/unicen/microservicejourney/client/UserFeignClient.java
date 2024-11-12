package com.grupo08.unicen.microservicejourney.client;




import com.grupo08.unicen.microserviceuser.dto.UserEntityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.grupo08.unicen.microservicejourney.model.UserDto;

import java.util.UUID;

@FeignClient("MICROSERVICE-USER")
public interface UserFeignClient {

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<UserEntityDto> getUserById(@PathVariable UUID userId);




}
package com.grupo08.unicen.microservicejourney.client;

import com.grupo08.unicen.microservicejourney.model.AccountDto;
import com.grupo08.unicen.microservicejourney.model.UserEntityDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "MICROSERVICE-USER", url = "http://localhost:8050")
public interface UserFeignClient {

    @GetMapping("/api/users/{userId}")
    public ResponseEntity<UserEntityDto> getUserById(@PathVariable UUID userId);

    @PutMapping("/api/users/{userId}")
    public ResponseEntity<UserEntityDto> editUser(@PathVariable UUID userId, @RequestBody UserEntityDto u);

    @GetMapping("/api/accounts/{accountId}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable UUID accountId);

}
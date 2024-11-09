package com.grupo08.unicen.microserviceuser.service;

import com.grupo08.unicen.microserviceuser.repository.UserEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService {

    private UserEntityRepository userEntityRepository;

    // dependency injection
    public UserEntityService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

}

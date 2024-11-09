package com.grupo08.unicen.microserviceuser.dto;

import com.grupo08.unicen.microserviceuser.entity.UserEntity;

public class UserEntityDto {
    private String name;
    private String lastname;
    private String email;
    private String phoneNumber;

    public UserEntityDto(UserEntity userEntity) {
        this.name = userEntity.getName();
        this.lastname = userEntity.getLastname();
        this.email = userEntity.getEmail();
        this.phoneNumber = userEntity.getPhoneNumber();
    }
}

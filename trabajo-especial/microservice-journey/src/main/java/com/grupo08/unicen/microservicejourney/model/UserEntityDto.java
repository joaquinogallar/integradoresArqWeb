package com.grupo08.unicen.microservicejourney.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntityDto {
    private UUID id;
    private String name;
    private String lastname;
    private String email;
    private String phoneNumber;
    private int x ; 
    private int y ;

}
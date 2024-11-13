package com.grupo08.unicen.microservicejourney.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class UserEntityDto {
    private String name;
    private String lastname;
    private String email;
    private String phoneNumber;
    private int x ; 
    private int y ;
    private double balance;

    

    public UserEntityDto(String name, String lastname, String email, String phoneNumber, double balance) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.balance = balance ;
    }
}
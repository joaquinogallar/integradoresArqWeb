package com.grupo08.unicen.microserviceuser.entity;

import com.grupo08.unicen.microserviceuser.dto.UserEntityDto;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String lastname;
    private int x;
    private int y ;

    private String email;
    private String phoneNumber;

    @ManyToMany
    private List<Account> accounts;

    @ElementCollection
    private List<UUID> journeys;

    

    public UserEntity(String name, String lastname, String email, String phoneNumber) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        accounts = new ArrayList<>();
    }

    public UserEntity(UserEntityDto userEntityDto) {
        this.name = userEntityDto.getName();
        this.lastname = userEntityDto.getLastname();
        this.email = userEntityDto.getEmail();
        this.phoneNumber = userEntityDto.getPhoneNumber();
        accounts = new ArrayList<>();
    }

  
}

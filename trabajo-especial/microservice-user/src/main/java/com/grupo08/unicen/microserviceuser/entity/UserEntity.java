package com.grupo08.unicen.microserviceuser.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@ToString
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Name cant be null")
    @NotEmpty(message = "Name cant be empty")
    private String name;
    @NotNull(message = "Lastname cant be null")
    @NotEmpty(message = "Lastname cant be empty")
    private String lastname;
    @NotNull(message = "Email cant be null")
    @NotEmpty(message = "Email cant be empty")
    private String email;
    private String phoneNumber;

    @ManyToMany
    private List<Account> accounts;

    public UserEntity(String name, String lastname, String email, String phoneNumber) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        accounts = new ArrayList<>();
    }
}

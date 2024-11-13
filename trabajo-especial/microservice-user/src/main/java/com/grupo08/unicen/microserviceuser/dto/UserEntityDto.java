package com.grupo08.unicen.microserviceuser.dto;

import com.grupo08.unicen.microserviceuser.entity.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntityDto {


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
    @NotNull(message = "Phone Number cant be null")
    @NotEmpty(message = "Phone Number cant be empty")
    private String phoneNumber;
    private int x ; 
    private int y ;

}

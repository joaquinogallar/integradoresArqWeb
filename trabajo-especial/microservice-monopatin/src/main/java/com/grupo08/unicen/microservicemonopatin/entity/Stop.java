package com.grupo08.unicen.microservicemonopatin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Stop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String location;

    private String address;
}

package com.grupo08.unicen.microserviceuser.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime createdAt;

    private Double balance;

    // assign the date before persist
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}
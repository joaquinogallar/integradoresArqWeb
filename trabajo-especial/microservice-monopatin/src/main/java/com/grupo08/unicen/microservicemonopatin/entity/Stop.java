package com.grupo08.unicen.microservicemonopatin.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Stop {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int x;
    private int y;

    private String address;

    @OneToMany
    private List<Monopatin> monopatines;
}

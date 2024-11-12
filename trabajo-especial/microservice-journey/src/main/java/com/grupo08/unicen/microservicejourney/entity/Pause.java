package com.grupo08.unicen.microservicejourney.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pause {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime startDate;

    @Column(nullable = true)
    private LocalDateTime finishDate;

    @ManyToOne
    @JoinColumn(name = "id_viaje")
    private Journey journey;


}
package com.grupo08.unicen.microservicejourney.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pausa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date fecha_inicio;
    private LocalDateTime hora_inicio;

    @Column(nullable = true)
    private  Date fecha_fin;

    @Column(nullable = true)
    private LocalDateTime hora_fin;

    @ManyToOne
    @JoinColumn(name = "id_viaje")
    private Viaje viaje;


}
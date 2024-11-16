package com.grupo08.unicen.microservicejourney.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.grupo08.unicen.microservicejourney.model.MonopatinDto;

@Entity
@Data
@NoArgsConstructor
public class Journey {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime startDate;
    private LocalDateTime finishDate;

    private Double  kmTraveled;
    private int xOrigin;
    private int yOrigin;
    private Integer xDestinatio;
    private Integer yDestinatio;

    private UUID userId;
    private UUID monopatinId;
    private UUID accountId;

    @OneToOne
    @JoinColumn(name = "id_fee")
    private Fee fee;

    @OneToMany(mappedBy = "journey", cascade = CascadeType.ALL)
    private List<Pause> pauses;

    public Journey(UUID monopatinId, UUID userId, UUID accountId, int x, int y){
        this.kmTraveled = 0.0;
        this.startDate = LocalDateTime.now();
        this.userId = userId;
        this.monopatinId = monopatinId;
        this.accountId = accountId;
        this.xOrigin = x;
        this.yOrigin = y;
        xDestinatio = null;
        yDestinatio = null;
        fee = null;
    }
}
package com.grupo08.unicen.microservicejourney.entity;
import com.grupo08.unicen.microservicemonopatin.entity.Monopatin;
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

    private Double kmTraveled;
    private int xOrigin;
    private int yOrigin;
    private Integer xDestinatio;
    private Integer yDestinatio;

    private UUID userId;
    private UUID monopatinId;

    @OneToOne
    @JoinColumn(name = "id_fee")
    private Fee fee;

    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL)
    private List<Pause> pauses;

    public Journey(UUID monopatinId, UUID userId, int x, int y){
        this.startDate = LocalDateTime.now();
        this.userId = userId;
        this.monopatinId = monopatinId;
        this.xOrigin = x;
        this.yOrigin = y;
    }
}
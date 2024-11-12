package com.grupo08.unicen.microservicejourney.dto;

import com.grupo08.unicen.microservicejourney.entity.Fee;
import com.grupo08.unicen.microservicejourney.entity.Journey;
import com.grupo08.unicen.microservicejourney.entity.Pause;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class JourneyDto {

    private UUID id;

    private LocalDateTime startDate;
    private LocalDateTime finishDate;

    private Double kmTraveled;
    private int xOrigin;
    private int yOrigin;

    private UUID userId;
    private UUID monopatinId;


    private Fee fee;

    private List<Pause> pauses;

    public JourneyDto(Journey v) {
        this.id = v.getId();
        this.startDate = v.getStartDate();
        this.finishDate = v.getFinishDate();
        this.kmTraveled = v.getKmTraveled();
        this.xOrigin = v.getXOrigin();
        this.yOrigin = v.getYOrigin();
        this.userId = v.getUserId();
        this.monopatinId = v.getMonopatinId();
        this.fee = v.getFee();
        this.pauses = v.getPauses();
    }
}

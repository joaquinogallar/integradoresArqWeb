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


    private UUID fee;

    

    public JourneyDto(UUID id,LocalDateTime startDate, LocalDateTime finishDate, double kmTraveled,int x,int y, UUID userid,UUID monopatinId,UUID fee) {
        this.id = id ;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.kmTraveled = kmTraveled;
        this.xOrigin = x;
        this.yOrigin = y;
        this.userId = userid;
        this.monopatinId = monopatinId;
        this.fee = fee;
        
    }
}

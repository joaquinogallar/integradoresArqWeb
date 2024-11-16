package com.grupo08.unicen.microservicejourney.dto;

import com.grupo08.unicen.microservicejourney.entity.Fee;
import com.grupo08.unicen.microservicejourney.entity.Journey;
import com.grupo08.unicen.microservicejourney.entity.Pause;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JourneyDto {

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
    private UUID accountId;
}

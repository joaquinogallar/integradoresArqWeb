package com.grupo08.unicen.microserviceuser.model;

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

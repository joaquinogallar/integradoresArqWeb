package com.grupo08.unicen.microservicejourney.dto;

import java.time.LocalDateTime;

import com.grupo08.unicen.microservicejourney.entity.Journey;
import com.grupo08.unicen.microservicejourney.entity.Pause;



public class PauseDto {

    private LocalDateTime startDate;
    private LocalDateTime finishDate;
     private Journey journey;
    public PauseDto(Pause p) {
        this.startDate = p.getStartDate();
        this.finishDate = p.getFinishDate();
        this.journey = p.getJourney(); 
    }
}

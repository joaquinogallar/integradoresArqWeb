package com.grupo08.unicen.microservicejourney.dto;

import java.time.LocalDateTime;

import com.grupo08.unicen.microservicejourney.entity.Journey;
import com.grupo08.unicen.microservicejourney.entity.Pause;



public class PauseDto {

    private LocalDateTime startDate;
    private LocalDateTime finishDate;
     private Journey journey;
    public PauseDto(LocalDateTime s, LocalDateTime f,Journey j) {
        this.startDate = s;
        this.finishDate =f;
        this.journey = j; 
    }
}

package com.grupo08.unicen.microserviceuser.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PauseDto {
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
     private UUID journey;
}

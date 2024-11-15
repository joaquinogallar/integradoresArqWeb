package com.grupo08.unicen.microservicejourney.entity;

import com.grupo08.unicen.microservicejourney.dto.FeeDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private Double fee;
    private Double specialFee;
    private LocalDate startDate;

    public Fee(FeeDto feeDto) {
        this.fee = feeDto.getFee();
        this.specialFee = feeDto.getSpecialFee();
        this.startDate = LocalDate.now();
    }
}
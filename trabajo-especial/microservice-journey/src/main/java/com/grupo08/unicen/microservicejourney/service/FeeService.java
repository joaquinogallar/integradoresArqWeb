package com.grupo08.unicen.microservicejourney.service;

import com.grupo08.unicen.microservicejourney.dto.FeeDto;
import com.grupo08.unicen.microservicejourney.entity.Fee;
import com.grupo08.unicen.microservicejourney.repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FeeService {
   @Autowired
   FeeRepository tarifaRepository;


    public ResponseEntity<List<FeeDto>> getAllFees() {
        try{
            List<Fee> response = tarifaRepository.findAll();
            List<FeeDto> aux = new ArrayList<>();

            response.forEach(f -> aux.add(new FeeDto(f.getId(), f.getFee(), f.getSpecialFee(), f.getStartDate())));

            return ResponseEntity.ok(aux) ;
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<FeeDto> getFeeById(UUID idTarifa) {
        try {
            Fee f = tarifaRepository.findById(idTarifa).orElse(null);
            FeeDto fd = new FeeDto(f.getId(), f.getFee(), f.getSpecialFee(), f.getStartDate());

            return ResponseEntity.ok(fd);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }


    public ResponseEntity<FeeDto> crearTarifa(FeeDto t) {
        try{
            Fee f = new Fee(t);
            this.tarifaRepository.save(f);
            return ResponseEntity.ok(t);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }
}

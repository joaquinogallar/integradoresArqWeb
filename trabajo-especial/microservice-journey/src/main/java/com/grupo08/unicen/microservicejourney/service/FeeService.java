package com.grupo08.unicen.microservicejourney.service;

import com.grupo08.unicen.microservicejourney.dto.FeeDto;
import com.grupo08.unicen.microservicejourney.entity.Fee;
import com.grupo08.unicen.microservicejourney.repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FeeService {
   @Autowired
   FeeRepository tarifaRepository;


    public List<FeeDto> getAll() {
        try{
            List<FeeDto> aux = new ArrayList<>();
            List<Fee> response = new ArrayList<>(tarifaRepository.findAll());
            for(Fee t : response){
                aux.add(new FeeDto(t)) ;
            }
            return aux ;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public FeeDto getById(UUID idTarifa) throws Exception {
        try {
            return  new FeeDto(this.tarifaRepository.findById(idTarifa).orElse(null));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public FeeDto crearTarifa(Fee t) {
        try{
            this.tarifaRepository.save(t);
            return new FeeDto(t);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}

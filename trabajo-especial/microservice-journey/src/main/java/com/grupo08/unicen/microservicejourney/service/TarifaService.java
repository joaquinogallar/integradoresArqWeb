package com.grupo08.unicen.microservicejourney.service;

import com.grupo08.unicen.microservicejourney.dto.TarifaDTO;
import com.grupo08.unicen.microservicejourney.entity.Tarifa;
import com.grupo08.unicen.microservicejourney.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TarifaService {
   @Autowired
   TarifaRepository tarifaRepository;


    public List<TarifaDTO> getAll() {
        try{
            List<TarifaDTO> aux = new ArrayList<>();
            List<Tarifa> response = new ArrayList<>(tarifaRepository.findAll());
            for(Tarifa t : response){
                aux.add(new TarifaDTO(t)) ;
            }
            return aux ;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public TarifaDTO getById(Long idTarifa) throws Exception {
        try {
            return  new TarifaDTO(this.tarifaRepository.getById(idTarifa));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    public TarifaDTO crearTarifa(Tarifa t) {
        try{
            this.tarifaRepository.save(t);
            return new TarifaDTO(t);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}

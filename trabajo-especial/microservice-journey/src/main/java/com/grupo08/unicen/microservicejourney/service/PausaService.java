package com.grupo08.unicen.microservicejourney.service;

import com.grupo08.unicen.microservicejourney.dto.PausaDTO;
import com.grupo08.unicen.microservicejourney.entity.Pausa;
import com.grupo08.unicen.microservicejourney.entity.Viaje;
import com.grupo08.unicen.microservicejourney.repository.PausaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo08.unicen.microservicejourney.repository.viajeRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PausaService {

    @Autowired
    private PausaRepository pausaRepository;

    @Autowired
    viajeRepository viajeRepository;

    public List<PausaDTO> getPausasPorViaje(Long idViaje) {
        List<Pausa> p = pausaRepository.findPausasByidviaje(idViaje);
        List<PausaDTO> aux = new ArrayList<PausaDTO>();
        for (Pausa pausa : p) {
            aux.add(new PausaDTO(pausa));
        }
        return aux ;
    }


    public PausaDTO crearPausa(Long idViaje) {
        try {

          Viaje v  = viajeRepository.findById(idViaje).orElse(null);

            if (v==null) {
                throw new RuntimeException();
            }
            Pausa p = new Pausa();
            p.setViaje(v);
            p.setHora_inicio(LocalDateTime.now());
            this.pausaRepository.save(p);

            return new PausaDTO(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

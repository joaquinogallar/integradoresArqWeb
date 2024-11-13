package com.grupo08.unicen.microservicejourney.service;

import com.grupo08.unicen.microservicejourney.dto.PauseDto;
import com.grupo08.unicen.microservicejourney.entity.Pause;
import com.grupo08.unicen.microservicejourney.entity.Journey;
import com.grupo08.unicen.microservicejourney.repository.PauseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo08.unicen.microservicejourney.repository.JourneyRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PauseService {

    @Autowired
    private PauseRepository pauseRepository;

    @Autowired
    private JourneyRepository journeyRepository;

    public List<PauseDto> getPausasPorViaje(UUID journeyId) {
        List<Pause> p = pauseRepository.findPausasByidviaje(journeyId);
        List<PauseDto> aux = new ArrayList<>();
        for (Pause pause : p) {
            aux.add(new PauseDto(pause.getStartDate(),pause.getFinishDate(),pause.getJourney()));
        }
        return aux ;
    }


    public PauseDto crearPausa(UUID journeyId) {
        try {
          Journey j = journeyRepository.findById(journeyId).orElse(null);

            if (j==null) {
                throw new RuntimeException();
            }
            Pause p = new Pause();
            p.setJourney(j);
            p.setStartDate(LocalDateTime.now());
            this.pauseRepository.save(p);

            return new PauseDto(p.getStartDate(),p.getFinishDate(),p.getJourney());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

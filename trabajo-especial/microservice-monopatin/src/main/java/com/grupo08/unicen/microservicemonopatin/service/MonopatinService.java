package com.grupo08.unicen.microservicemonopatin.service;

import com.grupo08.unicen.microservicemonopatin.dto.MonopatinDto;
import com.grupo08.unicen.microservicemonopatin.dto.StopDto;
import com.grupo08.unicen.microservicemonopatin.entity.Monopatin;
import com.grupo08.unicen.microservicemonopatin.entity.State;
import com.grupo08.unicen.microservicemonopatin.entity.Stop;
import com.grupo08.unicen.microservicemonopatin.exception.MonopatinNotFoundException;
import com.grupo08.unicen.microservicemonopatin.repository.MonopatinRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class MonopatinService {

    private MonopatinRepository monopatinRepository;

    // dependency injection
    public MonopatinService(MonopatinRepository monopatinRepository) {
        this.monopatinRepository = monopatinRepository;
    }

    public ResponseEntity<List<MonopatinDto>> getAllMonopatines() {
        try {
            List<Monopatin> monopatines = monopatinRepository.findAll();
            List<MonopatinDto> monopatinDtos = new ArrayList<>();
            monopatines.forEach(m -> monopatinDtos.add(new MonopatinDto(m.getId(), m.getState(), m.getX(), m.getY(), m.getKmTraveled(), m.getUseTime())));
            return ResponseEntity.ok(monopatinDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<MonopatinDto> getMonopatinById(UUID monopatinId) {
        Monopatin m = monopatinRepository.findById(monopatinId)
                .orElseThrow(() -> new MonopatinNotFoundException(monopatinId.toString()));
        return ResponseEntity.ok(new MonopatinDto(m.getId(), m.getState(), m.getX(), m.getY(), m.getKmTraveled(), m.getUseTime()));
    }

    public ResponseEntity<String> createMonopatin(MonopatinDto monopatin) {
        try {
            monopatinRepository.save(new Monopatin(monopatin));
            return ResponseEntity.ok("Monopatin created successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e);
        }
    }

    public ResponseEntity<List<MonopatinDto>> getMonopatinesConTiempoPausa() {
           try{
            List<Monopatin> monopatines= this.monopatinRepository.getMonopatinesConTiempoPausa();
            List<MonopatinDto> respuesta = new ArrayList<>();
            for (Monopatin m : monopatines) {
                respuesta.add(new MonopatinDto(m.getId(), m.getState(), m.getX(), m.getY(), m.getKmTraveled(), m.getUseTime())) ;
            }
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<List<MonopatinDto>> getMonopatinesConTiempoPausaPorKms(BigDecimal minKms) {
        try{
            List<Monopatin> monopatines= this.monopatinRepository.getMonopatinesConTiempoPausa();
            List<MonopatinDto> respuesta = new ArrayList<>();
            for (Monopatin m : monopatines) {
                if(m.getKmTraveled().compareTo(minKms) > 0)
                    respuesta.add(new MonopatinDto(m.getId(), m.getState(), m.getX(), m.getY(), m.getKmTraveled(), m.getUseTime())) ;
            }
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }
    

    public ResponseEntity<List<MonopatinDto>> getMonopatinesSinTiempoPausa() {
        try{
            List<Monopatin> monopatines= this.monopatinRepository.getMonopatinesSinTiempoPausa();
            List<MonopatinDto> respuesta = new ArrayList<>();
            for (Monopatin m : monopatines) {
                respuesta.add(new MonopatinDto(m.getId(), m.getState(), m.getX(), m.getY(), m.getKmTraveled(), m.getUseTime()));
            }
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(null);
        }
    }

    public ResponseEntity<List<MonopatinDto>> getMonopatinesSinTiempoPausaPorKms(BigDecimal minKms) {
        try{
            List<Monopatin> monopatines= this.monopatinRepository.getMonopatinesSinTiempoPausa();
            List<MonopatinDto> respuesta = new ArrayList<>();
            for (Monopatin m : monopatines) {
                if(m.getKmTraveled().compareTo(minKms) > 0)
                    respuesta.add(new MonopatinDto(m.getId(), m.getState(), m.getX(), m.getY(), m.getKmTraveled(), m.getUseTime()));
            }
            return ResponseEntity.ok(respuesta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<MonopatinDto> putMonopatin(Long monopatinID) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'putMonopatin'");
    }

    public ResponseEntity <List<MonopatinDto>> getMonopatinesInMaintenance() {
        try {
            List<Monopatin> monopatines = monopatinRepository.getMonopatinesInMaintenance();
        List<MonopatinDto> aux = new ArrayList<>();
        for (Monopatin m : monopatines) {
            aux.add(new MonopatinDto(m.getId(), m.getState(), m.getX(), m.getY(), m.getKmTraveled(), m.getUseTime()));
        }
        return ResponseEntity.ok(aux);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(null);
        }
        
    }

    public ResponseEntity<List<MonopatinDto>> getActivesMonopatines() {
        try {
            List<Monopatin> monopatines = monopatinRepository.getActivesMonopatines();
        List<MonopatinDto> aux = new ArrayList<>();
        for (Monopatin m : monopatines) {
            aux.add(new MonopatinDto(m.getId(), m.getState(), m.getX(), m.getY(), m.getKmTraveled(), m.getUseTime()));
        }
        return ResponseEntity.ok(aux);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(null);
    }
}

     public ResponseEntity<List<MonopatinDto>> getNearMonopatines(int x, int y,int rangoMetros) {
       try {
        List<MonopatinDto> aux = new ArrayList<>();
        List<Monopatin> mono = monopatinRepository.findAvailables(); 
        for (Monopatin m: mono) {
            double distancia = Math.sqrt(
            Math.pow(m.getX() - x, 2) +
            Math.pow(m.getY() - y, 2)
            );
            if (distancia <= rangoMetros) {
                aux.add(new MonopatinDto(m.getId(), m.getState(), m.getX(), m.getY(), m.getKmTraveled(), m.getUseTime()));
            }
        }
        return ResponseEntity.ok(aux);
        
       } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
       }
    }

    public ResponseEntity<MonopatinDto> putMonopatinInMaintenance(UUID monopatinId) {
        Monopatin m = monopatinRepository.findById(monopatinId).orElse(null);
        try {
                m.setState(State.IN_MAINTENANCE);
                monopatinRepository.save(m);
                return ResponseEntity.ok(new MonopatinDto(m.getId(), m.getState(), m.getX(), m.getY(), m.getKmTraveled(), m.getUseTime()));
            
        }     catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(null);
        } 
    }

    public ResponseEntity<MonopatinDto> putMonopatinOutMaintenance(UUID monopatinId) {
        Monopatin m = monopatinRepository.findById(monopatinId).orElse(null);
        try {
                m.setState(State.AVAILABLE);
                monopatinRepository.save(m);
                return ResponseEntity.ok(new MonopatinDto(m.getId(), m.getState(), m.getX(), m.getY(), m.getKmTraveled(), m.getUseTime()));
            
        }     catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(null);
        } 
    }
}




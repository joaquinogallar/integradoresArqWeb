package com.grupo08.unicen.microservicejourney.service;

import com.grupo08.unicen.microservicejourney.dto.JourneyDto;
import com.grupo08.unicen.microservicejourney.entity.Pause;
import com.grupo08.unicen.microservicejourney.entity.Fee;
import com.grupo08.unicen.microservicejourney.entity.Journey;
import com.grupo08.unicen.microservicejourney.client.MonopatinFeignClient;
import com.grupo08.unicen.microservicejourney.client.StopFeignClient;
import com.grupo08.unicen.microservicejourney.client.UserFeignClient;
import com.grupo08.unicen.microservicejourney.model.UserEntityDto;
import com.grupo08.unicen.microservicejourney.repository.FeeRepository;

import com.grupo08.unicen.microservicemonopatin.dto.MonopatinDto;
import com.grupo08.unicen.microservicemonopatin.entity.Monopatin;
import com.grupo08.unicen.microservicemonopatin.entity.State;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.grupo08.unicen.microservicejourney.repository.JourneyRepository;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class JourneyService {
    JourneyRepository journeyRepository;
    UserFeignClient userFeignClient;
    MonopatinFeignClient monopatinFeignClient;
    StopFeignClient stopFeignClient;
    FeeRepository feeRepository ;

    public JourneyService(JourneyRepository journeyRepository, UserFeignClient userFeignClient, MonopatinFeignClient monopatinFeignClient, StopFeignClient stopFeignClient, FeeRepository feeRepository) {
        this.journeyRepository = journeyRepository;
        this.userFeignClient = userFeignClient;
        this.monopatinFeignClient = monopatinFeignClient;
        this.stopFeignClient = stopFeignClient;
        this.feeRepository = feeRepository;
    }

    public ResponseEntity<List<JourneyDto>> getAll() {
        try{
            List<Journey> journeys = this.journeyRepository.findAll() ;
            List<JourneyDto> journeyDtos = new ArrayList<>();
            journeys.forEach(j -> journeyDtos.add(new JourneyDto(j)));

            return ResponseEntity.ok(journeyDtos);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<JourneyDto> createViaje(UUID monopatinId, UUID userId) {
        try {
            Monopatin monopatin = monopatinFeignClient.getMonopatinById(monopatinId).getBody();
            UserEntityDto user = userFeignClient.getUserById(userId).getBody();

            if(monopatin == null || user == null) throw new RuntimeException();

            Journey journey = new Journey(monopatinId, userId, monopatin.getX(), monopatin.getY());
            return ResponseEntity.ok(new JourneyDto(journey));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(null);
        }
    }

    public ResponseEntity<List<JourneyDto>> getViajeByMonopatin(UUID idMonopatin) {

        try {
            List<Journey> viajes= journeyRepository.FindViajesPorId_monopatin(idMonopatin);
            List<JourneyDto> aux = new ArrayList<>();
            for (Journey v : viajes) {
                aux.add(new JourneyDto(v)) ;
            }
            return ResponseEntity.ok(aux);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(null);
        }
      
    }

    public List<Long>getMonopatinesByXViajes(int cantidad, int anio){
        return journeyRepository.findMonopatinesByViaje(anio,cantidad);
    }

    public JourneyDto endViaje(UUID journeyId) {
        double xfinal = 0;
        double yfinal = 0;
        Journey journey  = journeyRepository.findById(journeyId).orElseThrow();

        Monopatin monopatin = monopatinFeignClient.getMonopatinById(journey.getMonopatinId()).getBody();
        if(monopatin == null) throw new RuntimeException();

        MonopatinDto m = new MonopatinDto(monopatin);
        if(stopFeignClient.getParadaByX(m.getX(),m.getY()) != null) {
            journey.setFinishDate(LocalDateTime.now());
            m.setState(State.AVAILABLE);

            xfinal = m.getX();
            yfinal = m.getY();
            journey.setKmTraveled(
                    Math.sqrt(
                            Math.pow(xfinal - journey.getXOrigin(), 2) + Math.pow(yfinal - journey.getYOrigin(), 2)
                    )
            );
        }

        journeyRepository.save(journey);

        List<Pause>pausas = journeyRepository.findPausasByIdViaje(journeyId);
        double tarifaXpausas = 0 ;
        double tarifaNormal = 0;


        Fee ta = feeRepository.getTarifaNormalEnPlazoValido();
        Long t =  ChronoUnit.MINUTES.between(journey.getStartDate(), journey.getFinishDate());

        m.setUseTime(t);

        tarifaNormal = (xfinal+yfinal)*ta.getFee();
        for (Pause pausa : pausas) {
            Long minutos = ChronoUnit.MINUTES.between(pausa.getStartDate(), pausa.getFinishDate());
            if(minutos>15) {
                Fee tarifaEspecial = feeRepository.getTarifaExtraEnPlazoValido();
                Long minutosFinal = minutos-15 ;
                 tarifaXpausas = minutosFinal*tarifaEspecial.getSpecialFee();
            }

            // TODO: el balance se setea a la cuenta, no al usuario
/*            UserEntityDto u  =  userFeignClient.getUserById(journey.getUserId()).getBody();
            u.setBalance(tarifaNormal+tarifaXpausas);*/
            return new JourneyDto(journey);
        }
        throw new RuntimeException();
    }

    public int getFacturadoEntreMeses(int year, int mes, int mes2) {
       return journeyRepository.getFacturado(year,mes,mes2);
    }

    
    
}
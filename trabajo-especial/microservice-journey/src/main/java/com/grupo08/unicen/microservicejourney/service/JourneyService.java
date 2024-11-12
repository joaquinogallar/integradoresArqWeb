package com.grupo08.unicen.microservicejourney.service;

import com.grupo08.unicen.microservicejourney.dto.JourneyDto;
import com.grupo08.unicen.microservicejourney.entity.Pause;
import com.grupo08.unicen.microservicejourney.entity.Fee;
import com.grupo08.unicen.microservicejourney.entity.Journey;
import com.grupo08.unicen.microservicejourney.client.MonopatinFeignClient;
import com.grupo08.unicen.microservicejourney.client.StopFeignClient;
import com.grupo08.unicen.microservicejourney.client.UserFeignClient;
import com.grupo08.unicen.microservicejourney.repository.FeeRepository;

import com.grupo08.unicen.microservicemonopatin.dto.MonopatinDto;
import com.grupo08.unicen.microservicemonopatin.entity.Monopatin;
import com.grupo08.unicen.microservicemonopatin.entity.State;
import com.grupo08.unicen.microserviceuser.dto.AccountDto;
import com.grupo08.unicen.microserviceuser.dto.UserEntityDto;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    JourneyRepository journeyRepository;
    @Autowired
    UserFeignClient userFeignClient;
    @Autowired
    MonopatinFeignClient monopatinFeignClient;
    @Autowired
    StopFeignClient stopFeignClient;
    @Autowired
    FeeRepository feeRepository ;

    public ResponseEntity<List<JourneyDto>> getAll() {
        try{
            List<Journey> journeys = this.journeyRepository.findAll() ;
            List<JourneyDto> journeyDtos = new ArrayList<>();
            journeys.forEach(j -> journeyDtos.add(new JourneyDto(j)));

            return ResponseEntity.ok(journeyDtos);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<JourneyDto> createViaje(UUID monopatinId, UUID userId) {
        try {
            Monopatin monopatin = monopatinFeignClient.getMonopatinById(monopatinId).getBody();
            UserEntityDto user = userFeignClient.getUserById(userId).getBody();
            Journey journey = new Journey(monopatinId, userId, monopatin.getX(), monopatin.getY());
            return ResponseEntity.ok(new JourneyDto(journey));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<JourneyDto> getViajeByMonopatin(UUID idMonopatin) {
        List<Journey> viajes= journeyRepository.FindViajesPorId_monopatin(idMonopatin);
        List<JourneyDto> aux = new ArrayList<>();
        for (Journey v : viajes) {
            aux.add(new JourneyDto(v)) ;
        }
        return aux;
    }

    public List<Long>getMonopatinesByViajes(int cantidad, int anio){
        return journeyRepository.findMonopatinesByViaje(anio,cantidad);
    }

    public JourneyDto endViaje(UUID journeyId) {
        double xfinal = 0;
        double yfinal = 0;
        Journey journey  = journeyRepository.findById(journeyId).orElse(null);

        MonopatinDto m = new MonopatinDto(monopatinFeignClient.getMonopatinById(journey.getMonopatinId()).getBody());
        if(stopFeignClient.getParadaByX(m.getX(),m.getY())!=null) {

            journey.setFinishDate(LocalDateTime.now());

            m.setState(State.AVAILABLE);

            if(journey.getXOrigin()- m.getX() <0) xfinal = (journey.getXOrigin()- m.getX())*-1;
            else xfinal = journey.getXOrigin()- m.getX();
        }
        if(journey.getYOrigin()- m.getY() <0)
            yfinal = (journey.getYOrigin()- m.getY())*-1  ;
        else
            yfinal = journey.getYOrigin()- m.getY();

        journey.setKmTraveled(xfinal+yfinal);
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
}
package com.grupo08.unicen.microservicejourney.service;

import com.grupo08.unicen.microservicejourney.dto.JourneyDto;
import com.grupo08.unicen.microservicejourney.entity.Pause;
import com.grupo08.unicen.microservicejourney.entity.Fee;
import com.grupo08.unicen.microservicejourney.entity.Journey;
import com.grupo08.unicen.microservicejourney.client.MonopatinFeignClient;
import com.grupo08.unicen.microservicejourney.client.StopFeignClient;
import com.grupo08.unicen.microservicejourney.client.UserFeignClient;
import com.grupo08.unicen.microservicejourney.model.AccountDto;
import com.grupo08.unicen.microservicejourney.model.MonopatinDto;
import com.grupo08.unicen.microservicejourney.model.State;
import com.grupo08.unicen.microservicejourney.model.UserEntityDto;
import com.grupo08.unicen.microservicejourney.repository.FeeRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.grupo08.unicen.microservicejourney.repository.JourneyRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


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
            journeys.forEach(j -> journeyDtos.add(new JourneyDto(j.getId(),j.getStartDate(),j.getFinishDate(),j.getKmTraveled(),j.getXDestinatio(),j.getYOrigin(),j.getUserId(),j.getMonopatinId(),j.getFee().getId())));

            return ResponseEntity.ok(journeyDtos);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public JourneyDto createViaje(UUID monopatinId, UUID userId, UUID accountId) {
        MonopatinDto monopatin = monopatinFeignClient.getMonopatinById(monopatinId).getBody();
        UserEntityDto user = userFeignClient.getUserById(userId).getBody();
        AccountDto accountDto = userFeignClient.getAccountById(accountId).getBody();

        if(monopatin == null || user == null || accountDto == null || accountDto.getBalance()<=0)  throw new RuntimeException();

        Journey j = new Journey(monopatinId, userId, accountId, monopatin.getX(), monopatin.getY());
        return new JourneyDto(j.getId(),j.getStartDate(),j.getFinishDate(),j.getKmTraveled(),j.getXDestinatio(),j.getYOrigin(),j.getUserId(),j.getMonopatinId(),j.getFee().getId());
    }


    public ResponseEntity<List<JourneyDto>> getViajeByMonopatin(UUID idMonopatin) {

        try {
            List<Journey> viajes= journeyRepository.FindViajesPorId_monopatin(idMonopatin);
            List<JourneyDto> aux = new ArrayList<>();
            for (Journey j : viajes) {
                aux.add(new JourneyDto(j.getId(),j.getStartDate(),j.getFinishDate(),j.getKmTraveled(),j.getXDestinatio(),j.getYOrigin(),j.getUserId(),j.getMonopatinId(),j.getFee().getId())) ;
            }
            return ResponseEntity.ok(aux);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(null);
        }
      
    }

    public ResponseEntity<List<MonopatinDto>> getMonopatinesByXViajes(int cantidad, int anio){
        try {
            List<UUID> idMonopatines = journeyRepository.findMonopatinesByViaje(anio,cantidad);
            List<MonopatinDto> monopatines = new ArrayList<>();

            idMonopatines.forEach(im -> monopatines.add(monopatinFeignClient.getMonopatinById(im).getBody()));

            return ResponseEntity.ok(monopatines);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }

    }

    public JourneyDto endViaje(UUID journeyId) {
        double xfinal = 0;
        double yfinal = 0;
        Journey j  = journeyRepository.findById(journeyId).orElseThrow();

        MonopatinDto monopatin = monopatinFeignClient.getMonopatinById(j.getMonopatinId()).getBody();
        if(monopatin == null) throw new RuntimeException();

        AccountDto account = userFeignClient.getAccountById(j.getAccountId()).getBody();

        
        if(stopFeignClient.getStopByXY(monopatin.getX(),monopatin.getY()) != null) {
            j.setFinishDate(LocalDateTime.now());
            monopatin.setState(State.AVAILABLE);

            xfinal = monopatin.getX();
            yfinal = monopatin.getY();
            j.setKmTraveled(
                    // formula para sacar la distancia entre 2 vectores (origen y destino)
                    Math.sqrt(
                            Math.pow(xfinal - j.getXOrigin(), 2) + Math.pow(yfinal - j.getYOrigin(), 2)
                    )
            );
        }

        journeyRepository.save(j);

        List<Pause>pausas = journeyRepository.findPausasByIdViaje(journeyId);
        double tarifaXpausas = 0 ;
        double tarifaNormal = 0;


        Fee ta = feeRepository.getTarifaNormalEnPlazoValido();
        Long t =  ChronoUnit.MINUTES.between(j.getStartDate(), j.getFinishDate());

        monopatin.setUseTime(t);
        monopatinFeignClient.editMonopatin(monopatin.getId());

        tarifaNormal = (xfinal+yfinal)*ta.getFee();
        for (Pause pausa : pausas) {
            Long minutos = ChronoUnit.MINUTES.between(pausa.getStartDate(), pausa.getFinishDate());
            if(minutos>15) {
                Fee tarifaEspecial = feeRepository.getTarifaExtraEnPlazoValido();
                Long minutosFinal = minutos-15 ;
                tarifaXpausas = minutosFinal*tarifaEspecial.getSpecialFee();
            }

            
            UserEntityDto u  =  userFeignClient.getUserById(j.getUserId()).getBody();
            account.setBalance(tarifaNormal+tarifaXpausas);
            userFeignClient.editUser(j.getUserId(),u);
            return new JourneyDto(j.getId(),j.getStartDate(),j.getFinishDate(),j.getKmTraveled(),j.getXDestinatio(),j.getYOrigin(),j.getUserId(),j.getMonopatinId(),j.getFee().getId());
        }
        throw new RuntimeException();
    }

    public int getFacturadoEntreMeses(int year, int mes, int mes2) {
       return journeyRepository.getFacturado(year,mes,mes2);
    }
    
}
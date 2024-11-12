package com.grupo08.unicen.microservicejourney.controller;


import com.grupo08.unicen.microservicejourney.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/viajes")
public class ViajeController {

    @Autowired
    JourneyService viajeService ;

    @GetMapping("/")
    public  ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.getAll());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
    @GetMapping("/monopatines/viajes/{cant}/{anio}")
    public ResponseEntity<?> getMonopatinesByViaje(@PathVariable int cant, @PathVariable int anio){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.getMonopatinesByViajes(cant, anio));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/endViaje/{idViaje}")
    public ResponseEntity<?> endViaje(@PathVariable Long idViaje){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.endViaje(idViaje));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
   @PostMapping("/crear/{monopatinId}/usuario/{usuarioId}")
        public void createViaje(@PathVariable("monopatinId") Long monopatinId, @PathVariable("usuarioId") Long usuarioId){
      viajeService.createViaje(monopatinId,usuarioId);
        }

        @GetMapping("/monopatin/{id_monopatin}")
        public ResponseEntity<?>getViajeByMonopatin(@PathVariable Long id_monopatin){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.getViajeByMonopatin(id_monopatin));
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        }



        }








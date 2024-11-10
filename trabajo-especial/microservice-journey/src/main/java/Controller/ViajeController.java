package Controller;

import DTOS.viajeDTO;
import Service.viajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viajes")
public class ViajeController {

    @Autowired
    viajeService viajeService ;

    @GetMapping("/")
    public  ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(viajeService.getAll());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @PostMapping("/crear/{monopatinId}/usuario/{usuarioId}")
        public void createViaje(@PathVariable("monopatinId") Long monopatinId, @PathVariable("usuarioId") Long usuarioId){
      viajeService.createViaje(monopatinId,usuarioId);
        }
    }







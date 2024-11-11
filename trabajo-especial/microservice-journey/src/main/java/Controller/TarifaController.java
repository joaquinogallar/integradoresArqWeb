package Controller;

import Entitys.Tarifa;
import Service.TarifaService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/tarifas")
public class TarifaController {

    @Autowired
    TarifaService tarifaService;


    @GetMapping("/")
    public ResponseEntity<?> getAllTarifas() {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(tarifaService.getAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{tarifaId}")
    public ResponseEntity<?> getTarifaById(@PathVariable Long tarifaId) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(tarifaService.getById(tarifaId));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/")
    public ResponseEntity<?> crateTarifa(@RequestBody Tarifa t) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(tarifaService.crearTarifa(t));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
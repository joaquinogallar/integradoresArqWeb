package Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Service.PausaService ;

@RestController
@RequestMapping("/pausas")
public class PausaController {


     @Autowired
     PausaService pausaService;

    @PostMapping("viaje/{idViaje}")
    public ResponseEntity<?> crearPausa(@PathVariable Long idViaje){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pausaService.crearPausa(idViaje));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    @GetMapping("/viaje/{idViaje}")
    public ResponseEntity<?> getPausasPorViaje(@PathVariable Long idViaje){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(pausaService.getPausasPorViaje(idViaje));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

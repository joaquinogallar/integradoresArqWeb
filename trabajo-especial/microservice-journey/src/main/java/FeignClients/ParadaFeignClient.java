package FeignClients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "parada-service", url = "http://localhost:8087")
public interface ParadaFeignClient {
    @GetMapping("/parada/{idParada}")
    ResponseEntity<?> getParadaById(@PathVariable Long idParada);

    @GetMapping("/parada/{x}/{y}")
    ResponseEntity<p> getParadaByX(@PathVariable double x, @PathVariable double y);
}

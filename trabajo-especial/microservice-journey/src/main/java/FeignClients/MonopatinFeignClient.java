package FeignClients;

import com.example.microservicioMonopatin.entity.MonopatinDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "monopatin-service", url = "http://localhost:8083")
public interface MonopatinFeignClient {
    @GetMapping("/monopatin/{idMonopatin}")
    ResponseEntity<MonopatinDTO> getMonopatinById(@PathVariable Long idMonopatin);
}


package FeignClients;




import com.example.microserviciouser.DTOS.userDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuario-service", url = "http://localhost:8086")
public interface UserFeignClient {

    @GetMapping("/usuario/{idUsuario}")
    ResponseEntity<userDTO> getUsuarioById(@PathVariable Long idUsuario);




}
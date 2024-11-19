package com.grupo08.unicen.gateway.FeignClient;




import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.grupo08.unicen.gateway.model.RolDTO;
import com.grupo08.unicen.gateway.model.UsuarioDTO;

@FeignClient(name = "MICROSERVICE-USER", url = "http://localhost:8050")
public interface UsuarioFeignClient {

    @PostMapping("/api/usuarios")
    UsuarioDTO crearCuenta(@RequestBody UsuarioDTO usuario);

    @GetMapping("/api/rol/{idRol}")
    RolDTO getRolById(@PathVariable("idRol") Long idRol);

    @GetMapping("/api/usuarios/email/{userEmail}")
    UsuarioDTO getUserByUserEmail(@PathVariable("userEmail") String userEmail);
}
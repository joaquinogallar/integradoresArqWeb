package com.grupo08.unicen.gateway.FeignClient;




import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.grupo08.unicen.gateway.Model.RolDTO;
import com.grupo08.unicen.gateway.Model.UsuarioDTO;

@FeignClient(name = "usuario-service", url = "http://localhost:8081")
public interface UsuarioFeignClient {

    @PostMapping("/usuarios")
    UsuarioDTO crearCuenta(@RequestBody UsuarioDTO usuario);

    @GetMapping("/rol/{idRol}")
    RolDTO getRolById(@PathVariable("idRol") Long idRol);

    @GetMapping("/usuarios/email/{userEmail}")
    UsuarioDTO getUserByUserEmail(@PathVariable("userEmail") String userEmail);
}
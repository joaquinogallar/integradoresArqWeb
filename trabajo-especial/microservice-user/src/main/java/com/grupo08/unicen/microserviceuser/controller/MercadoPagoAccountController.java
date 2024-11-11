package com.grupo08.unicen.microserviceuser.controller;

import com.grupo08.unicen.microserviceuser.entity.MercadoPagoAccount;
import com.grupo08.unicen.microserviceuser.service.MercadoPagoAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mercadosPago")
public class MercadoPagoAccountController {

    @Autowired
    private MercadoPagoAccountService mercadoPagoAccountService;

    @GetMapping
    public ResponseEntity<List<MercadoPagoAccount>> getAllMercadoPagoAccounts() {
        return mercadoPagoAccountService.getMercadoPagoAccounts();
    }

    @PostMapping
    public ResponseEntity<String> createMercadoPagoAccount(@RequestBody final MercadoPagoAccount account) {
        return mercadoPagoAccountService.createMercadoPagoAccount(account);
    }
}

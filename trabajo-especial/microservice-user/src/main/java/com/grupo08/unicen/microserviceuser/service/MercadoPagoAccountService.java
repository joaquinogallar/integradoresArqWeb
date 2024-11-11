package com.grupo08.unicen.microserviceuser.service;

import com.grupo08.unicen.microserviceuser.entity.MercadoPagoAccount;
import com.grupo08.unicen.microserviceuser.repository.MercadoPagoAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MercadoPagoAccountService {

    private final MercadoPagoAccountRepository mercadoPagoAccountRepository;

    public MercadoPagoAccountService(MercadoPagoAccountRepository mercadoPagoAccountRepository) {
        this.mercadoPagoAccountRepository = mercadoPagoAccountRepository;
    }

    public ResponseEntity<List<MercadoPagoAccount>> getMercadoPagoAccounts() {
        try {
            List<MercadoPagoAccount> list = mercadoPagoAccountRepository.findAll();
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    public ResponseEntity<String> createMercadoPagoAccount(MercadoPagoAccount account) {
        try {
            mercadoPagoAccountRepository.save(account);
            return ResponseEntity.status(HttpStatus.CREATED).body("Account created");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}

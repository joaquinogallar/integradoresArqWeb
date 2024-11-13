package com.grupo08.unicen.microservicepayment.controller;

import com.grupo08.unicen.microservicepayment.entity.Transaction;
import com.grupo08.unicen.microservicepayment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public class PaymentController {

	@Autowired
    private PaymentService paymentService;

    @GetMapping("/transacciones")
    public List<Transaction> getAllTransactions() {
        return paymentService.getAllTransactions();
    }

    @GetMapping("/transaccion/{id}")
    public Transaction getTransactionById(@PathVariable UUID id) {
        return paymentService.getTransactionById(id);
    }

    @PostMapping("/transaccion")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return paymentService.createTransaction(transaction);
    }
    
    @GetMapping("/tarifas")
    public List<Transaction> getAllTariffs() {
        return paymentService.getAllTransactions();
    }

/*    @GetMapping("/tarifa/{id}")
    public Transaction getTariffById(@PathVariable UUID id) {
        return paymentService.getTariffById(id);
    }*/

/*    @PostMapping("/tarifa")
    public Transaction createTariff(@RequestBody Transaction transaction) {
        return paymentService.createTariff(transaction);
    }*/
    
}

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

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return paymentService.getAllTransactions();
    }

    @GetMapping("/transactions/{id}")
    public Transaction getTransactionById(@PathVariable UUID id) {
        return paymentService.getTransactionById(id);
    }

    @PostMapping("/transactions")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return paymentService.createTransaction(transaction);
    }
    
    // Similar endpoints para tarifas
}

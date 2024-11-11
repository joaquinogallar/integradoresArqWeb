package com.grupo08.unicen.microservicepayment.service;

import com.grupo08.unicen.microservicepayment.entity.Transaction;
import com.grupo08.unicen.microservicepayment.entity.Tariff;
import com.grupo08.unicen.microservicepayment.repository.TransactionRepository;
import com.grupo08.unicen.microservicepayment.repository.TariffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

	@Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TariffRepository tariffRepository;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(UUID id) {
        return transactionRepository.findById(id).orElse(null);
    }

    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    // Métodos para gestionar tarifas...
    public List<Tariff> getAllTariffs() {
    	return tariffRepository.findAll();
    }
    
    public Tariff getTariffById(UUID id) {
    	return tariffRepository.findById(id).orElse(null);
    }
    
    public Tariff createTariff(Tariff tariff) {
    	return tariffRepository.save(tariff);
    }
}

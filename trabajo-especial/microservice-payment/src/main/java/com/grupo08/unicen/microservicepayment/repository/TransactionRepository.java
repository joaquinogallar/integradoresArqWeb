package com.grupo08.unicen.microservicepayment.repository;

import com.grupo08.unicen.microservicepayment.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {}
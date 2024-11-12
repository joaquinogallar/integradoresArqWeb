package com.grupo08.unicen.microservicepayment.dto;

import java.math.BigDecimal;
import java.util.UUID;
import java.time.LocalDateTime;

public class TransactionDTO {

	private UUID id;
    private UUID idCuenta;
    private BigDecimal monto;
    private String tipo;
    private LocalDateTime fecha;
	
	public TransactionDTO() {}

    public TransactionDTO(UUID id, UUID idCuenta, BigDecimal monto, String tipo, LocalDateTime fecha) {
        this.id = id;
        this.idCuenta = idCuenta;
        this.monto = monto;
        this.tipo = tipo;
        this.fecha = fecha;
    }
}

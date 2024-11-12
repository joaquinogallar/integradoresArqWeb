package com.grupo08.unicen.microservicepayment.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class TariffDTO {
	private UUID id;
	private BigDecimal montoNormal;
	private BigDecimal montoExtra;
	private LocalDateTime fechaVigencia;
	
	public TariffDTO() {}

	public TariffDTO(UUID id, BigDecimal montoNormal, BigDecimal montoExtra, LocalDateTime fecha) {
		this.id = id;
		this.montoNormal = montoNormal;
		this.montoExtra = montoExtra;
		this.fechaVigencia = fecha;
	}
}

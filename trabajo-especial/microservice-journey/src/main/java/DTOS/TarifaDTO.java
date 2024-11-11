package DTOS;

import Entitys.Tarifa;

import java.util.Date;

public class TarifaDTO {
    private Double tarifa;
    private String tipo_tarifa;
    private Date fecha_inicio;

    public TarifaDTO(Tarifa t) {
        this.tarifa = t.getTarifa();
        this.tipo_tarifa = t.getTipo_tarifa();

    }

}

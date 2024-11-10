package DTOS;

import Entitys.Viaje;

public class viajeDTO {

    private double kmRecorridos ;

    public viajeDTO(Viaje v) {
        this.kmRecorridos = v.getkmRecorridos();
    }
}

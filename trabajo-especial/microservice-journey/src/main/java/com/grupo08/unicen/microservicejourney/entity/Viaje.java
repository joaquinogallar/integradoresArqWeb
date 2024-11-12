package com.grupo08.unicen.microservicejourney.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import com.grupo08.unicen.microservicejourney.model.MonopatinDTO;

@Entity

@Data
@NoArgsConstructor

public class Viaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate fecha_inicio;
    @Setter
    @Getter
    private LocalDate fecha_fin;
    private Double kmRecorridos;
    private Long id_usuario;
    private MonopatinDTO monopatin;
    private int xOrigen ;
    private int yOrigen;
    
    private LocalDateTime horainicio;
    private LocalDateTime horafin;
    

    @OneToOne
    @JoinColumn(name = "id_tarifa")
    private Tarifa tarifa;

    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL)
    private List<Pausa> pausas;
    public Viaje (LocalDate fecha_inicio, Long id_usuario, MonopatinDTO monopatin,int x, int y, LocalDateTime horaInicio){
    this.fecha_inicio = fecha_inicio;
    this.id_usuario = id_usuario;
    this.monopatin = monopatin;
    this.xOrigen = x;
    this.yOrigen = y;
    this.horainicio = horaInicio ;
    }
    public double getkmRecorridos() {
        return kmRecorridos;
    }
public void setkmRecorridos(double kmRecorridos) {
        this.kmRecorridos = kmRecorridos;
}
public LocalDateTime getHoraInicio() {
  return this.getHoraInicio();
}


}
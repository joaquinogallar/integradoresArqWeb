package Entitys;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
    private LocalDate fecha_fin;
    private Double kmRecorridos;
    private Long id_usuario;
    private Long id_monopatin;
    private int xOrigen ;
    private int yOrigen;


    @OneToOne
    @JoinColumn(name = "id_tarifa")
    private Tarifa tarifa;

    @OneToMany(mappedBy = "viaje", cascade = CascadeType.ALL)
    private List<Pausa> pausas;
    public Viaje (LocalDate fecha_inicio, Long id_usuario, Long id_monopatin){
    this.fecha_inicio = fecha_inicio;
    this.id_usuario = id_usuario;
    this.id_monopatin = id_monopatin;
    }
    public double getkmRecorridos() {
        return kmRecorridos;
    }
public void setkmRecorridos(double kmRecorridos) {
        this.kmRecorridos = kmRecorridos;
}

}
package Entitys;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="tarifa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Double tarifa;
    private String tipoTarifa;
    private Date fechaInicio;
    private Double precioTarifaPausa;


    public Double getTarifa(){
        return this.tarifa;
    }

    public String getTipo_tarifa(){
        return this.tipoTarifa ;
    }
}
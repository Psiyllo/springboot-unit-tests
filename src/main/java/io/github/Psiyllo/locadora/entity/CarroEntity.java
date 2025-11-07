package io.github.Psiyllo.locadora.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "carro")
@Data
public class CarroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String modelo;
    private double valorDiaria;

    public CarroEntity() {
    }

    public CarroEntity(String modelo, double valorDiaria) {
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
    }
}

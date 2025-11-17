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
    private int ano;


    public CarroEntity() {
    }

    public CarroEntity(String modelo, double valorDiaria, int ano) {
        this.modelo = modelo;
        this.valorDiaria = valorDiaria;
        this.ano = ano;
    }

    public CarroEntity(long id, String modelo, double valorDiaria, int ano) {
        this(modelo, valorDiaria, ano);
        this.id = id;
    }
}

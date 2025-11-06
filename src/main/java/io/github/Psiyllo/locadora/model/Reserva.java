package io.github.Psiyllo.locadora.model;

import io.github.Psiyllo.locadora.model.Exceptions.ReservaInvalidaException;

public class Reserva {

    private Carro carro;
    private Cliente cliente;
    private int quantidadeDias;


    public Reserva(Carro carro, Cliente cliente, int quantidadeDias) {
        if(quantidadeDias < 1){
            throw new ReservaInvalidaException("A Reserva não pode ter uma quantidade de dias menor que 1");
        }
        this.carro = carro;
        this.cliente = cliente;
        this.quantidadeDias = quantidadeDias;
    }

    public double calcularTotalReserva(){
        return this.carro.calcularValorAluguel(this.quantidadeDias);
    }
}

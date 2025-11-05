package io.github.Psiyllo.locadora.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarroTest {

    @Test
    @DisplayName("Deve calcular o valor correto do alguel")
    void deveCalcularValorAlguel(){
        Carro carro = new Carro("Sedan", 100.0);
        double total = carro.calcularValorAlguel(6);
        Assertions.assertEquals(600.0, total);
    }
}

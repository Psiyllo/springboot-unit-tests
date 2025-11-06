package io.github.Psiyllo.locadora.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CarroTest {

    @Test
    @DisplayName("Deve calcular o valor correto do alguel")
    void deveCalcularValorAlguel(){

        //1. Cenário
        Carro carro = new Carro("Sedan", 100.0);

        //2. Execução
        double total = carro.calcularValorAluguel(3);

        //3. Verificação
        Assertions.assertEquals(300.0, total);
    }

    @Test
    @DisplayName("Deve calcular o valor correto do alguel com desconto")
    void deveCalcularValorAlguelComDesconto(){

        //1. Cenário
        Carro carro = new Carro("Sedan", 100.0);
        int quantidadeDias = 5;

        //2. Execução
        double total = carro.calcularValorAluguel(quantidadeDias);

        //3. Verificação
        Assertions.assertEquals(450.0, total);
    }
}

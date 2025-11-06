package io.github.Psiyllo.locadora.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {

    Cliente cliente;
    Carro carro;

    @BeforeEach
    void setUp(){
        carro = new Carro("Sedan", 50);
        cliente = new Cliente("Paulo");
    }

    @Test
    void deveCriarUmaReserva() {
        var dias = 5;

        var reserva = new Reserva(carro, cliente, dias);

        assertThat(reserva).isNotNull();
    }

    @Test
    void deveDarErroAoCriarUmaReservaComDiasNegativos() {
    }

    @Test
    void deveCalcularOTotalDoAluguel() {
    }
}
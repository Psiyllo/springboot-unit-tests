package io.github.Psiyllo.locadora.model;

import io.github.Psiyllo.locadora.model.Exceptions.ReservaInvalidaException;
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

        assertThrows(ReservaInvalidaException.class, () -> new Reserva(carro,cliente,0));
        assertDoesNotThrow(() -> new Reserva(carro,cliente,2));

        var erro = catchThrowable(() -> new Reserva(carro, cliente, 0));

        assertThat(erro).isInstanceOf(ReservaInvalidaException.class)
                .hasMessage("A Reserva não pode ter uma quantidade de dias menor que 1");
    }

    @Test
    void deveCalcularOTotalDoAluguel() {
        var dias = 3;

        var reserva = new Reserva(carro,cliente,dias);

        double total = reserva.calcularTotalReserva();

        assertEquals(150, total);
        assertThat(total).isEqualTo(150);
    }
}
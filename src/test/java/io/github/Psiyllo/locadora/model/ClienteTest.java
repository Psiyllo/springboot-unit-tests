package io.github.Psiyllo.locadora.model;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ClienteTest {

    @Test
    void deveCriarClienteComNome(){
        var cliente = new Cliente("Paulo");

        String nome = cliente.getNome();

        assertNotNull(nome);

        assertThat(nome).isEqualTo("Paulo");
        assertThat(nome).isLessThan("Pauloo");

        assertTrue(nome.startsWith("P"));
        assertFalse(nome.length()==100);

        assertThat(nome.length()).isLessThan(100);
    }

    @Test
    void deveCriarClienteSemNome(){
        var cliente = new Cliente(null);

        String nome = cliente.getNome();

        assertNull(nome);
    }
}

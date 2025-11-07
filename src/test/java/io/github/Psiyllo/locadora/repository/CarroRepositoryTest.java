package io.github.Psiyllo.locadora.repository;

import io.github.Psiyllo.locadora.entity.CarroEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CarroRepositoryTest {

    @Autowired
    CarroRepository repository;

    @Test
    void deveSalvarCarro(){
        var entity = new CarroEntity("Sedan", 100);
        repository.save(entity);

        assertNotNull(entity.getId());
    }

    @Test
    @Sql("/sql/popular-carros.sql")
    void deveBuscarCarroPorModelo(){
        List<CarroEntity> lista = repository.findByModelo("Suv");
        assertEquals(4, lista.size());

        var carro = lista.stream().findFirst().get();

        assertThat(carro.getValorDiaria()).isEqualTo(150);
        assertThat(carro.getModelo()).isEqualTo("Suv");
    }
}
package io.github.Psiyllo.locadora.repository;

import io.github.Psiyllo.locadora.entity.CarroEntity;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CarroRepositoryTest {

    @Autowired
    CarroRepository repository;

    CarroEntity carro;

    @BeforeEach
    void setUp(){
        carro = new CarroEntity("Passat CC", 100, 2013);
    }

    @Test
    void deveSalvarCarro(){
        var entity = carro;
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
        assertThat(carro.getAno()).isEqualTo(2025);
    }

    @Test
    void deveBuscarCarroPorId(){
        var carroSalvo = repository.save(carro);

        Optional<CarroEntity> carroEncontrado = repository.findById(carroSalvo.getId());

        assertThat(carroEncontrado).isPresent();
        assertThat(carroEncontrado.get().getModelo()).isEqualTo("Passat CC");
    }

    @Test
    void deveAtualizarCarro(){
        var carroSalvo = repository.save(carro);

        carroSalvo.setAno(2014);

        var carroAtualizado = repository.save(carro);

        assertThat(carroAtualizado.getAno()).isEqualTo(2014);
    }

    @Test
    void deveDeletarCarro(){
        var carroSalvo = repository.save(carro);

        repository.deleteById(carroSalvo.getId());

        Optional<CarroEntity> carroEncontrado = repository.findById(carroSalvo.getId());

        assertThat(carroEncontrado).isEmpty();
    }
}
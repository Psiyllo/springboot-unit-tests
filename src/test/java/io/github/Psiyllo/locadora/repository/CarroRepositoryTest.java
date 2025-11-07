package io.github.Psiyllo.locadora.repository;

import io.github.Psiyllo.locadora.entity.CarroEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class CarroRepositoryTest {

    @Autowired
    CarroRepository repository;

    @Test
    public void deveSalvarCarro(){
        var entity = new CarroEntity("Sedan", 100);
        repository.save(entity);

        assertNotNull(entity.getId());
    }

}
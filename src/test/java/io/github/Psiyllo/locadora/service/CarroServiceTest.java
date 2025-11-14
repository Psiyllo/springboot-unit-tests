package io.github.Psiyllo.locadora.service;

import io.github.Psiyllo.locadora.entity.CarroEntity;
import io.github.Psiyllo.locadora.repository.CarroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarroServiceTest {

    @InjectMocks
    CarroService service;
    @Mock
    CarroRepository repository;

    @Test
    void deveSalvarUmCarro(){

        CarroEntity carroSalvar = new CarroEntity("Sedan", 20, 2026);

        CarroEntity carroMock = new CarroEntity("Sedan", 200, 2026);
        carroMock.setId(1L);

        Mockito.when(repository.save(Mockito.any()) ).thenReturn(carroMock);

        var carroSalvo = service.salvar(carroSalvar);

        assertNotNull(carroSalvo);
        assertEquals("Sedan", carroSalvo.getModelo());

        Mockito.verify(repository).save(Mockito.any());
    }
}
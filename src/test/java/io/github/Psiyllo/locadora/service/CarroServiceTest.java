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
        Mockito
                .when(repository.findById(1L))
                .thenReturn(Optional.of(new CarroEntity("Testando MOCK", 10.0, 2025)));

        Optional<CarroEntity> carroEncontrado = repository.findById(1L);
        System.out.println(carroEncontrado.get().getModelo());
    }

}
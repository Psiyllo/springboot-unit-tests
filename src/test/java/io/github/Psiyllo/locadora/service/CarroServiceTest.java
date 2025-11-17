package io.github.Psiyllo.locadora.service;

import io.github.Psiyllo.locadora.entity.CarroEntity;
import io.github.Psiyllo.locadora.model.Exceptions.EntityNotFoundException;
import io.github.Psiyllo.locadora.repository.CarroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
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

    @Test
    void deveDarErroAoTentarSalvarCarroComDiariaNegativa(){
        CarroEntity carro = new CarroEntity("Sedan", 0, 2026);

        var erro = catchThrowable( () -> service.salvar(carro) );

        assertThat(erro).isInstanceOf(IllegalArgumentException.class);

        Mockito.verify(repository, Mockito.never()).save(Mockito.any());

    }

    @Test
    void deveAtualizarUmCarro(){

        var carroExistente = new CarroEntity("Palio", 10, 2013);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(carroExistente));

        var carroAtualizado = new CarroEntity("Passat", 20, 2013);
        carroAtualizado.setId(1L);
        Mockito.when(repository.save(Mockito.any())).thenReturn(carroAtualizado);

        Long id = 1L;
        var carro = new CarroEntity("Sedan", 0, 2026);

        var resultado = service.atualizar(id, carro);

        assertEquals(resultado.getModelo(),"Passat");
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void deveDarErroAoTentarAtualizarCarroInexistente(){
        Long id = 1L;

        var carro = new CarroEntity("Palio", 10, 2013);
        Mockito.when(repository.findById(Mockito.any())).thenReturn(Optional.empty());

        var erro = catchThrowable(() -> service.atualizar(id, carro));

        assertThat(erro).isInstanceOf(EntityNotFoundException.class);
        Mockito.verify(repository, Mockito.never()).save(Mockito.any());

    }
}
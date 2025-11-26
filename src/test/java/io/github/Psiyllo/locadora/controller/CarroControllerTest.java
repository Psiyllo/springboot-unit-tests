package io.github.Psiyllo.locadora.controller;

import com.jayway.jsonpath.JsonPath;
import io.github.Psiyllo.locadora.entity.CarroEntity;
import io.github.Psiyllo.locadora.model.Exceptions.EntityNotFoundException;
import io.github.Psiyllo.locadora.service.CarroService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CarroController.class)
class CarroControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    CarroService service;

    @Test
    void deveSalvarUmCarro() throws Exception{
        //cenário
        CarroEntity carro = new CarroEntity(1L,"Palio", 10, 2013);

        Mockito.when(service.salvar(Mockito.any())).thenReturn(carro);

        String json = """
                {
                    "modelo": "Palio",
                    "valorDiaria": 10,
                    "ano": 2013
                }
                """;

        //execução
        ResultActions result = mvc.perform(
                post("/carros")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(status().isCreated());

        //verificação
        result
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.modelo").value("Palio"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valorDiaria").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ano").value(2013));
    }

    @Test
    void deveRetornarArgumentExceptionAoSalvarCarroComDiariaNegativa() throws Exception{
        Mockito.when(service.salvar(Mockito.any()))
                .thenThrow(new IllegalArgumentException("Valor da diária inválido"));

        String json = """
                {
                    "modelo": "Palio",
                    "valorDiaria": 0,
                    "ano": 2013
                }
                """;

        mvc.perform(
                        MockMvcRequestBuilders.post("/carros")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(json)
                ).andExpect(status().isUnprocessableEntity());
    }

    @Test
    void deveObterDetalhesCarro() throws Exception{
        Mockito.when(service.buscarPorId(Mockito.any())).thenReturn(new CarroEntity(
                1L, "Palio", 10,2013
        ));

        mvc.perform(
                MockMvcRequestBuilders.get("/carros/1")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.modelo").value("Palio"))
                .andExpect(jsonPath("$.valorDiaria").value(10))
                .andExpect(jsonPath("$.ano").value(2013));
    }

    @Test
    void deveRetornarNotFoundAoObterDetalhesCarroInexistente() throws Exception{
        Mockito.when(service.buscarPorId(Mockito.any())).thenThrow(EntityNotFoundException.class);

        mvc.perform(
                        MockMvcRequestBuilders.get("/carros/1")
                ).andExpect(status().isNotFound());
    }

    @Test
    void deveListarCarros() throws Exception{
        var listagem = List.of(
                new CarroEntity(1L, "Fiat Palio", 10, 2013),
                new CarroEntity(2L, "Honda Fit", 15, 2015),
                new CarroEntity(3L, "Byd Dolphin Gs", 35, 2026)
        );

        Mockito.when(service.listarTodos()).thenReturn(listagem);

        mvc.perform(
                MockMvcRequestBuilders.get("/carros")
        ).andExpect(status().isOk())
                .andExpect(jsonPath("$[0].modelo").value("Fiat Palio"))
                .andExpect(jsonPath("$[1].modelo").value("Honda Fit"))
                .andExpect(jsonPath("$[2].modelo").value("Byd Dolphin Gs"));
    }

    @Test
    void deveAtualizarCarro() throws Exception{
        Mockito.when(service.atualizar(Mockito.any(), Mockito.any())).thenReturn(new CarroEntity(
                1L, "Fiat Palio", 10, 2013
        ));

        String json = """
                {
                    "modelo": "Honda Fit",
                    "valorDiaria": 15,
                    "ano": 2015
                }
                """;

        mvc.perform(
                MockMvcRequestBuilders.put("/carros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(status().isNoContent());
    }

    @Test
    void deveRetornarNotFoundAoAtualizarCarroInexistente() throws Exception{
        Mockito.when(service.atualizar(Mockito.any(), Mockito.any())).thenThrow(EntityNotFoundException.class);

        String json = """
                {
                    "modelo": "Honda Fit",
                    "valorDiaria": 15,
                    "ano": 2015
                }
                """;

        mvc.perform(
                MockMvcRequestBuilders.put("/carros/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(status().isNotFound());
    }
    @Test
    void deveDeletarCarro() throws Exception{
        Mockito.doNothing().when(service).deletar(Mockito.any());

        mvc.perform(
                        MockMvcRequestBuilders.delete("/carros/1"))
                .andExpect(status().isNoContent());
    }
    @Test
    void deveRetornarNotFoundAoDeletarCarroInexistente() throws Exception{
        Mockito.doThrow(EntityNotFoundException.class).when(service).deletar(Mockito.any());

        mvc.perform(
                        MockMvcRequestBuilders.delete("/carros/1"))
                .andExpect(status().isNotFound());
    }
}
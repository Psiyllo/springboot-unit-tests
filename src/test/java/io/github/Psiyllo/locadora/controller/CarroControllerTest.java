package io.github.Psiyllo.locadora.controller;

import io.github.Psiyllo.locadora.entity.CarroEntity;
import io.github.Psiyllo.locadora.service.CarroService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


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
        );

        //verificação
        result
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.modelo").value("Palio"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.valorDiaria").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.ano").value(2013));
    }
}
package io.github.Psiyllo.locadora.controller;

import io.github.Psiyllo.locadora.entity.CarroEntity;
import io.github.Psiyllo.locadora.model.Exceptions.EntityNotFoundException;
import io.github.Psiyllo.locadora.service.CarroService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("carros")
@Data
public class CarroController {

    private final CarroService service;

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody CarroEntity carro){
        try {
            var carroSalvo = service.salvar(carro);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(carroSalvo);
        } catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<CarroEntity> detalhesCarro(@PathVariable Long id){
        try {
            var carroEncontrado = service.buscarPorId(id);
            return ResponseEntity.ok(carroEncontrado);
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> atualizarCarro(@PathVariable Long id, @RequestBody CarroEntity carro){
        try {
            var carroEncontrado = service.buscarPorId(id);
            carroEncontrado.setModelo(carro.getModelo());
            carroEncontrado.setValorDiaria(carro.getValorDiaria());
            carroEncontrado.setAno(carro.getAno());
            var carroAtualizado = service.atualizar(id, carroEncontrado);
            return ResponseEntity.status(HttpStatus.OK).body(carroAtualizado);
        } catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(e.getMessage());
        }
    }
}

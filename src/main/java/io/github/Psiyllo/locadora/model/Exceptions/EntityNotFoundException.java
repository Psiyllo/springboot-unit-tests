package io.github.Psiyllo.locadora.model.Exceptions;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(String message){
        super(message);
    }
}

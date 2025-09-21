package dev.java10x.user.Exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID id) {
        super("Usuario no encontrado con ID: " + id);
    }
}

package com.example.swiftroute.shared;

import java.util.UUID;

public class EntityNotFoundException extends RuntimeException {
    private EntityNotFoundException(String message) {
        super(message);
    }

    public static EntityNotFoundException of(String entityName, UUID id){
        return new EntityNotFoundException(
            String.format("%s with ID [%s] was not found", entityName, id)
        );
    }

}

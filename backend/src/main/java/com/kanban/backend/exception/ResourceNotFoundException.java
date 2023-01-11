package com.kanban.backend.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String className, Long id) {
        super("Could not find " + className + " with id " + id);
    }

}

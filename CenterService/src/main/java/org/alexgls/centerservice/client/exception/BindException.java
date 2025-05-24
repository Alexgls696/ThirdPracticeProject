package org.alexgls.centerservice.client.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class BindException extends RuntimeException {
    private final List<String>errors;

    public BindException(String message, final List<String> errors) {
        super(message);
        this.errors = errors;
    }
}

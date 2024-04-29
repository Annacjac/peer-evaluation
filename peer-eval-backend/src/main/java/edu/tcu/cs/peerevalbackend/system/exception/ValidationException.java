package edu.tcu.cs.peerevalbackend.system.exception;

import java.util.List;

public class ValidationException extends Throwable {
    private List<String> errors;
    public ValidationException(List<String> errors) {
        super();
        this.errors = errors;
    }
    public List<String> getErrors() {
        return errors;
    }
}

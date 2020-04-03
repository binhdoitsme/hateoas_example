package org.example.exception;

public class DependencyNotInjectedException extends RuntimeException {
    public DependencyNotInjectedException() {
        super("Dependency is not injected!");
    }
}

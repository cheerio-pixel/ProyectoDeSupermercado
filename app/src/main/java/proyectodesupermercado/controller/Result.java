package proyectodesupermercado.controller;

import java.util.Optional;
import java.util.function.Consumer;

public class Result<T> {
    private final T value;
    private final String failureMessage;

    public Result(T value, String failureMessage) {
        this.value = value;
        this.failureMessage = failureMessage;
        if (value != null && failureMessage != null) {
            throw new RuntimeException("Failure message and value cannot be non-null at the same time");
        }
    }

    public Optional<T> failureOrValue(Consumer<String> failureHandler) {
        if (failureMessage != null) {
            failureHandler.accept(failureMessage);
        }
        return Optional.ofNullable(value);
    }
}

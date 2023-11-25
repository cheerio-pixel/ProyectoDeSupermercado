package proyectodesupermercado.lib.databaseUtils.exceptions;

public class AnnotationTableNotFoundException extends RuntimeException {
    public AnnotationTableNotFoundException(String errorMessage) {
        super(errorMessage);
    }
    public AnnotationTableNotFoundException(Throwable cause) {
        super (cause);
    }

    public AnnotationTableNotFoundException(String message, Throwable cause) {
        super (message, cause);
    }
}

package proyectodesupermercado.lib.databaseUtils.exceptions;

public class AnnotationIdNotFoundException extends RuntimeException {
    public AnnotationIdNotFoundException(String errorMessage) {
        super(errorMessage);
    }
    public AnnotationIdNotFoundException(Throwable cause) {
        super (cause);
    }

    public AnnotationIdNotFoundException(String message, Throwable cause) {
        super (message, cause);
    }
}

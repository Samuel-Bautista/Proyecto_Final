package excepciones;

public class LimiteSuperadoException extends Exception {
    public LimiteSuperadoException(String mensaje) {
        super("error, limite superado");
    }
}
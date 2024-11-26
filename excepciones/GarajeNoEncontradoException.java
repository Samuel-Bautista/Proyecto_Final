package excepciones;

public class GarajeNoEncontradoException extends Exception {
    public GarajeNoEncontradoException(String mensaje) {
        super("error, garaje no encontrado");
    }
}

package excepciones;

public class VehiculoExistenteException extends Exception {
    public VehiculoExistenteException(String mensaje) {
        super("Error, vehiculo inexistente");
    }
}
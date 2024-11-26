package excepciones;

public class EspacioNoDisponibleException extends Exception {
    public EspacioNoDisponibleException(String mensaje) {
        super("Error, espacio no disponible");
    }
}
package excepciones;

public class TipoVehiculoInvalidoException extends Exception {
    public TipoVehiculoInvalidoException(String mensaje) {
        super("error, el tipo de vehiculo no es valido");
    }
}
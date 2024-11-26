package proyectofinal;

/**
 *
 * @author Samuel
 */
public class Camioneta extends Vehiculo {
    private String tipoServicio; 
    private int numeroPasajeros;
    private boolean tieneRemolque;

    public Camioneta(String marca, double precio, int cilindraje, String tipoServicio, int numeroPasajeros, boolean tieneRemolque) {
        super(marca, precio, cilindraje);
        this.tipoServicio = tipoServicio;
        this.numeroPasajeros = numeroPasajeros;
        this.tieneRemolque = tieneRemolque;
        calcularImpuestoCirculacion();
    }

    @Override
    public void calcularImpuestoCirculacion() {
        this.impuestoCirculacion = 0.05 * precio;
        
        if (tipoServicio.equals("Pickup") || tipoServicio.equals("Carga") || tipoServicio.equals("Otro")) {
            cuotaMesGaraje *= 1.45;
        } else if (tipoServicio.equals("SUV")) {
            cuotaMesGaraje *= 1.1;
        }

        if (numeroPasajeros <= 2) {
            cuotaMesGaraje *= 1.5;
        } else {
            cuotaMesGaraje *= 1.6;
        }

        if (tieneRemolque) {
            cuotaMesGaraje *= 1.1;
        }
    }
}

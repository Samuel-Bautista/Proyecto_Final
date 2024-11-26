package proyectofinal;

/**
 *
 * @author Samuel
 */

public class Auto extends Vehiculo {
    private boolean tieneRadio;
    private boolean tieneNavegador;

    public Auto(String marca, double precio, int cilindraje, boolean tieneRadio, boolean tieneNavegador) {
        super(marca, precio, cilindraje);
        this.tieneRadio = tieneRadio;
        this.tieneNavegador = tieneNavegador;
        calcularImpuestoCirculacion();
    }

    @Override
    public void calcularImpuestoCirculacion() {
        this.impuestoCirculacion = 0.02 * precio;
        if (tieneRadio) {
            this.impuestoCirculacion *= 1.01;
        }
        if (tieneNavegador) {
            this.impuestoCirculacion *= 1.02;
        }

        if (cilindraje > 2499) {
            cuotaMesGaraje *= 1.2;
        }
    }
}


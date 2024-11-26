package proyectofinal;

public class Moto extends Vehiculo {
    private boolean tieneSidecar;

    public Moto(String marca, double precio, int cilindraje, boolean tieneSidecar) {
        super(marca, precio, cilindraje);
        this.tieneSidecar = tieneSidecar;
        calcularImpuestoCirculacion();
    }

    @Override
    public void calcularImpuestoCirculacion() {
        this.impuestoCirculacion = 0.02 * precio;
        if (tieneSidecar) {
            this.impuestoCirculacion *= 1.1; 
            cuotaMesGaraje *= 1.5; 
        }
    }
}

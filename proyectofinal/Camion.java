package proyectofinal;

/**
 *
 * @author Samuel
 */

public class Camion extends Vehiculo {
    private int numeroEjes;
    private String tipoCamion; 
    private double capacidadCarga;

    public Camion(String marca, double precio, int cilindraje, int numeroEjes, String tipoCamion, double capacidadCarga) {
        super(marca, precio, cilindraje);
        this.numeroEjes = numeroEjes;
        this.tipoCamion = tipoCamion;
        this.capacidadCarga = capacidadCarga;
        calcularImpuestoCirculacion();
    }
    
    @Override
    public void calcularImpuestoCirculacion() {
        this.impuestoCirculacion = 0.09 * precio;
        this.impuestoCirculacion += (capacidadCarga / 5) * 10;

        if (tipoCamion.equals("Doble")) {
            cuotaMesGaraje *= 2.25;
        } else {
            cuotaMesGaraje *= 1.75; 
        }
    }
}

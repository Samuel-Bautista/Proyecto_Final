package proyectofinal;

public abstract class Vehiculo {
    protected String marca;
    protected double precio;
    protected int cilindraje;
    protected String placa;
    protected double cuotaMesGaraje = 100;
    protected double impuestoCirculacion;

    public Vehiculo(String marca, double precio, int cilindraje) {
        this.marca = marca;
        this.precio = precio;
        this.cilindraje = cilindraje;
        this.placa = null;
    }

    public abstract void calcularImpuestoCirculacion();
    
    public boolean matricular(String matricula) {
    //dar valor a la matricula siempre y cuando tenga 6 caracteres
        if (matricula.length() == 6) {
            this.placa = matricula;
            return true;
        } else return false;
    }
    
    // Getters y setters
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getCuotaMesGaraje() {
        return cuotaMesGaraje;
    }

    public void setCuotaMesGaraje(double cuotaMesGaraje) {
        this.cuotaMesGaraje = cuotaMesGaraje;
    }

    public double getImpuestoCirculacion() {
        return impuestoCirculacion;
    }

    public void setImpuestoCirculacion(double impuestoCirculacion) {
        this.impuestoCirculacion = impuestoCirculacion;
    }
}

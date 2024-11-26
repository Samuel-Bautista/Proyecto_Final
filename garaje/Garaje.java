package garaje;

import excepciones.*;
import proyectofinal.*;

import java.util.ArrayList;
import java.util.List;


public class Garaje {
    private String departamento, ciudad, direccion, telefono, email, nombreAdministrador;
    private int numeroPlazas;
    private List<Vehiculo> vehiculos;

    private static final int LIMITE_CAMIONES_PEQUENO = 10;
    private static final int LIMITE_CAMIONES_GRANDE = 20;
    private static final double PORCENTAJE_MOTOS = 0.2; // 20% de las plazas pueden ser ocupadas por motos
    private static final double PORCENTAJE_CAMIONETAS = 0.2; // 20% de las plazas pueden ser ocupadas por camionetas
    
    public Garaje(String departamento, String ciudad, String direccion, String telefono, String email, String nombreAdministrador, int numeroPlazas) {
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.nombreAdministrador = nombreAdministrador;
        this.numeroPlazas = numeroPlazas;
        this.vehiculos = new ArrayList<>();
    }

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public int getNumeroPlazas() {
        return numeroPlazas;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    // Método para calcular la ocupación de vehículos en el garaje
    public int calcularOcupacion() {
        return vehiculos.size();
    }

    // Método para calcular la ocupación por tipo de vehículo
    public int calcularOcupacionPorTipo(Vehiculo tipoVehiculo) {
        int count = 0;
        for (Vehiculo v : vehiculos) {
            if (v.getClass().equals(tipoVehiculo.getClass())) {
                count++;
            }
        }
        return count;
    }

    // Método para ingresar un vehículo al garaje
    public void ingresarVehiculo(Vehiculo vehiculo) throws EspacioNoDisponibleException {
        if (vehiculos.size() < numeroPlazas) {
            vehiculos.add(vehiculo);
        } else {
            throw new EspacioNoDisponibleException("No hay espacio disponible en el garaje.");
        }
    }

    // Método para retirar un vehículo del garaje
    public void retirarVehiculo(String placa) throws VehiculoExistenteException {
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getPlaca().equals(placa)) {
                vehiculos.remove(i);
                return;
            }
        }
        throw new VehiculoExistenteException("El vehículo con placa " + placa + " no se encuentra en el garaje.");
    }

    // Método para calcular los ingresos mensuales de este garaje
    public double calcularRecaudoMensual() {
        double totalRecaudo = 0.0;
        for (Vehiculo v : vehiculos) {
            totalRecaudo += v.getCuotaMesGaraje();
        }
        return totalRecaudo;
    }
    
    public static boolean buscarVehiculo(String placa) {
        for (Garaje garaje : RedGarajes.getGarajes()) {
            for (Vehiculo vehiculo : garaje.getVehiculos()) {
                if (vehiculo.getPlaca().equals(placa)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Métodos para verificar políticas de espacio
    public boolean puedeIngresarMoto() {
        System.out.println(calcularOcupacionPorTipo(new Moto("", 0, 0, false))+" aaaaa");
        return calcularOcupacionPorTipo(new Moto("", 0, 0, false)) < numeroPlazas * PORCENTAJE_MOTOS;
    }
    public boolean puedeIngresarAuto() {
        return calcularOcupacionPorTipo(new Auto("", 0, 0, false, false)) < numeroPlazas * PORCENTAJE_MOTOS;
    }

    public boolean puedeIngresarCamioneta() {
        return calcularOcupacionPorTipo(new Camioneta("", 0, 0, "", 0, false)) < numeroPlazas * PORCENTAJE_CAMIONETAS;
    }

    public boolean puedeIngresarCamion() {
        int maxCamiones = (numeroPlazas < 100) ? LIMITE_CAMIONES_PEQUENO : LIMITE_CAMIONES_GRANDE;
        return calcularOcupacionPorTipo(new Camion("", 0, 0, 0, "", 0)) < maxCamiones;
    }

    public void setTelefono(String telefono) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}


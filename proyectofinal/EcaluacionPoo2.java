package proyectofinal;


import garaje.*;
import excepciones.*;

import java.util.Scanner;

/**
 *
 * @author Samuel Bautista u20241220454
 */

public class EcaluacionPoo2 {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws VehiculoExistenteException {
        int opcion;
        while (true) {
            System.out.println("Menu de Gestion de Empresa");
            System.out.println("1. Crear garaje");
            System.out.println("2. Eliminar garaje");
            System.out.println("3. Actualizar garaje");
            System.out.println("4. Ingresar vehículo");
            System.out.println("5. Retirar vehículo");
            System.out.println("6. Generar informes");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    crearGaraje();
                    break;
                case 2:
                    eliminarGaraje();
                    break;
                case 3:
                    actualizarGaraje();
                    break;
                case 4:
                    ingresarVehiculo();
                    break;
                case 5:
                    retirarVehiculo();
                    break;
                case 6:
                    generarInformes();
                    break;
                case 7:
                    System.out.println("Saliendo del sistema.");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void crearGaraje() {
        System.out.println("Crear un nuevo garaje:");
        System.out.print("Nombre del administrador: ");
        String nombreAdministrador = scanner.nextLine();
        System.out.print("Departamento: ");
        String departamento = scanner.nextLine();
        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();
        System.out.print("Direccion: ");
        String direccion = scanner.nextLine();
        System.out.print("Telefono: ");
        String telefono = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Numero de plazas: ");
        int numeroPlazas = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Garaje nuevoGaraje = new Garaje(departamento, ciudad, direccion, telefono, email, nombreAdministrador, numeroPlazas);
        RedGarajes.agregarGaraje(nuevoGaraje);
        System.out.println("Garaje creado con exito.");
    }

    public static void eliminarGaraje() {
        System.out.print("Ingrese el nombre del administrador del garaje a eliminar: ");
        String nombreAdministrador = scanner.nextLine();

        try {
            Garaje garaje = RedGarajes.buscarGarajePorNombre(nombreAdministrador);
            RedGarajes.getGarajes().remove(garaje);
            System.out.println("Garaje eliminado con exito.");
        } catch (GarajeNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void actualizarGaraje() {
        System.out.print("Ingrese el nombre del administrador del garaje a actualizar: ");
        String nombreAdministrador = scanner.nextLine();

        try {
            Garaje garaje = RedGarajes.buscarGarajePorNombre(nombreAdministrador);
            System.out.println("Actualizar datos del garaje:");
            System.out.print("Nuevo teléfono: ");
            String telefono = scanner.nextLine();
            System.out.print("Nuevo email: ");
            String email = scanner.nextLine();

            // Actualizar garaje con nuevos datos
            garaje.setTelefono(telefono);
            garaje.setEmail(email);
            System.out.println("Garaje actualizado con éxito.");
        } catch (GarajeNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void ingresarVehiculo() {
        System.out.print("Ingrese los detalles del vehiculo para ingresar al garaje:");
        System.out.print("Marca: ");
        String marca = scanner.nextLine();
        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        System.out.print("Cilindraje: ");
        int cilindraje = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.println("Tipo de vehículo (1 = Moto, 2 = Auto, 3 = Camion, 4 = Camioneta): ");
        int tipoVehiculo = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        Vehiculo vehiculo = null;

        switch (tipoVehiculo) {
            case 1:
                System.out.print("¿La moto tiene sidecar? (true/false): ");
                boolean tieneSidecar = scanner.nextBoolean();
                vehiculo = new Moto(marca, precio, cilindraje, tieneSidecar);
                break;
            case 2:
                System.out.print("¿El auto tiene radio? (true/false): ");
                boolean tieneRadio = scanner.nextBoolean();
                System.out.print("¿El auto tiene navegador? (true/false): ");
                boolean tieneNavegador = scanner.nextBoolean();
                vehiculo = new Auto(marca, precio, cilindraje, tieneRadio, tieneNavegador);
                break;
            case 3:
                System.out.print("Número de ejes: ");
                int numeroEjes = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
                String tipoCamion;
                if (numeroEjes<=2) {
                    tipoCamion = "sencillo";
                } else {tipoCamion = "doble";}
                System.out.print("Capacidad de carga (en toneladas): ");
                double capacidadCarga = scanner.nextDouble();
                vehiculo = new Camion(marca, precio, cilindraje, numeroEjes, tipoCamion, capacidadCarga);
                break;
            case 4:
                System.out.print("Tipo de servicio (SUV, Pickup, Carga, Otro): ");
                String tipoServicio = scanner.nextLine();
                System.out.print("Número de pasajeros: ");
                int numeroPasajeros = scanner.nextInt();
                System.out.print("¿Tiene remolque? (true/false): ");
                boolean tieneRemolque = scanner.nextBoolean();
                vehiculo = new Camioneta(marca, precio, cilindraje, tipoServicio, numeroPasajeros, tieneRemolque);
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        }

        try {
            scanner.nextLine(); // Limpiar el buffer

            System.out.print("Ingrese la matricula del vehiculo: ");
            String placa = scanner.nextLine();
            if (Garaje.buscarVehiculo(placa)) {
                System.out.println("Vehiculo ya existe");
            } else {
            if (vehiculo.matricular(placa)) {
                vehiculo.setPlaca(placa);
                System.out.print("Ingrese el nombre del administrador del garaje: ");
                String nombreAdministrador = scanner.nextLine();

                Garaje garaje = RedGarajes.buscarGarajePorNombre(nombreAdministrador);
                switch (tipoVehiculo) {
                    case 1:
                        if (garaje.puedeIngresarMoto()) {garaje.ingresarVehiculo(vehiculo);System.out.println("Vehículo ingresado con éxito.");} else {System.out.println("Maximo tipo de vehiculo permitido");}
                        break;
                    case 2:
                        if (garaje.puedeIngresarAuto()) {garaje.ingresarVehiculo(vehiculo);System.out.println("Vehículo ingresado con éxito.");} else {System.out.println("Maximo tipo de vehiculo permitido");}
                        break;
                    case 3:
                        if (garaje.puedeIngresarCamion()) {garaje.ingresarVehiculo(vehiculo);System.out.println("Vehículo ingresado con éxito.");} else {System.out.println("Maximo tipo de vehiculo permitido");}
                        break;
                    case 4:
                        if (garaje.puedeIngresarCamioneta()) {garaje.ingresarVehiculo(vehiculo);System.out.println("Vehículo ingresado con éxito.");} else {System.out.println("Maximo tipo de vehiculo permitido");}   
                        break;
                }
            } else System.out.println("la placa debe tener 6 caracteres");
            }} catch (GarajeNoEncontradoException | EspacioNoDisponibleException e) {
                System.out.println(e.getMessage());
            }
    }

    public static void retirarVehiculo() throws VehiculoExistenteException {
        System.out.print("Ingrese la matricula del vehiculo a retirar: ");
        String placa = scanner.nextLine();
        System.out.print("Ingrese el nombre del administrador del garaje: ");
        String nombreAdministrador = scanner.nextLine();

        try {
            Garaje garaje = RedGarajes.buscarGarajePorNombre(nombreAdministrador);
            garaje.retirarVehiculo(placa);
            System.out.println("Vehículo retirado con exito.");
        } catch (GarajeNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void generarInformes() {
        System.out.println("Generando informes...");
        System.out.println("1. Ocupacion para todos los garajes");
        System.out.println("2. Ocupacion por tipo de vehiculo");
        System.out.println("3. Recaudo mensual por cada parqueadero y total");
        System.out.print("Seleccione una opcion: ");
        int opcionInforme = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        switch (opcionInforme) {
            case 1:
                System.out.println("Informe de ocupación para todos los garajes:");
                for (Garaje garaje : RedGarajes.getGarajes()) {
                System.out.println("Garaje: " + garaje.getNombreAdministrador());
                System.out.println("Ocupación: " + garaje.calcularOcupacion() + "/" + garaje.getNumeroPlazas());
                }
                break;
            case 2:
                System.out.println("Informe de ocupacion por tipo de vehiculo:");
                System.out.print("Ingrese el tipo de vehículo (moto, auto, camion, camioneta): ");
                String tipoVehiculo = scanner.next();
                scanner.nextLine(); // Limpiar el buffer
                int cantidad = 0;
                
                for (Garaje garaje : RedGarajes.getGarajes()) {
                    for (Vehiculo vehiculo : garaje.getVehiculos()) {
                        if (vehiculo.getClass().getSimpleName().equalsIgnoreCase(tipoVehiculo)) {
                            cantidad++;
                  
                        }
                    }
                }

                System.out.println("Total de vehículos del tipo solicitado: " + cantidad);
                 break;
                
            case 3:
                 System.out.println("Informe de recaudo mensual por cada parqueadero y total:");
                double recaudoTotal = 0;

                for (Garaje garaje : RedGarajes.getGarajes()) {
                    double recaudoGaraje = garaje.calcularRecaudoMensual();
                    recaudoTotal += recaudoGaraje;
                    System.out.println("Garaje: " + garaje.getNombreAdministrador());
                    System.out.println("Recaudo mensual: " + recaudoGaraje);
                }

                System.out.println("Recaudo total mensual de todos los garajes: " + recaudoTotal);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
}

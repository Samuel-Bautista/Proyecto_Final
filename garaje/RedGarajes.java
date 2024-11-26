package garaje;

import excepciones.*;

import java.util.ArrayList;
import java.util.List;

public class RedGarajes {
    private static List<Garaje> garajes = new ArrayList<>();

    public static void agregarGaraje(Garaje garaje) {
        garajes.add(garaje);
    }

    public static List<Garaje> getGarajes() {
        return garajes;
    }

    public static Garaje buscarGarajePorNombre(String nombre) throws GarajeNoEncontradoException {
        for (Garaje g : garajes) {
            if (g.getNombreAdministrador().equals(nombre)) {
                return g;
            }
        }
        throw new GarajeNoEncontradoException("Garaje no encontrado.");
    }
}
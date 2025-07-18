package controller;

import model.Platillo;
import java.util.ArrayList;
import java.util.List;

public class PlatilloController {
    private List<Platillo> platillos;

    public PlatilloController() {
        this.platillos = new ArrayList<>();
        // Ejemplo de datos
        platillos.add(new Platillo("Pizza Margarita", "Tomate, queso, albahaca", 8.5));
        platillos.add(new Platillo("Hamburguesa Clásica", "Carne, pan, lechuga", 6.0));
    }

    public List<Platillo> listarPlatillos() {
        return platillos;
    }

    public void agregarPlatillo(Platillo platillo) {
        platillos.add(platillo);
    }
}

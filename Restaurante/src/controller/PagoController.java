package controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Pago;

/**
 * Controlador para manejo de ventas de platillos
 */
public class PagoController {
    private List<Pago> pagos;

    public PagoController() {
        this.pagos = new ArrayList<>();
        // Ventas simuladas
        pagos.add(new Pago("Encebollado", 3.5, 2, LocalDate.of(2025, 7, 18)));
        pagos.add(new Pago("Hornado", 5.0, 1, LocalDate.of(2025, 7, 17)));
    }

    public List<Pago> listarTodos() {
        return pagos;
    }

    public void registrarPago(Pago pago) {
        pagos.add(pago);
    }

    public boolean actualizarPago(int index, Pago nuevoPago) {
        if (index >= 0 && index < pagos.size()) {
            pagos.set(index, nuevoPago);
            return true;
        }
        return false;
    }
}

package model;

import java.time.LocalDate;

/**
 * Modelo que representa una venta (antes: Pago)
 */
public class Pago {
    private String platillo;
    private double precio;
    private int cantidad;
    private double total;
    private LocalDate fechaVenta;

    public Pago(String platillo, double precio, int cantidad, LocalDate fechaVenta) {
        this.platillo = platillo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.total = precio * cantidad;
        this.fechaVenta = fechaVenta;
    }

    // Getters
    public String getPlatillo() { return platillo; }
    public double getPrecio() { return precio; }
    public int getCantidad() { return cantidad; }
    public double getTotal() { return total; }
    public LocalDate getFechaVenta() { return fechaVenta; }

    // Setters
    public void setPlatillo(String platillo) { this.platillo = platillo; }
    public void setPrecio(double precio) {
        this.precio = precio;
        recalcularTotal();
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        recalcularTotal();
    }
    public void setFechaVenta(LocalDate fechaVenta) { this.fechaVenta = fechaVenta; }

    private void recalcularTotal() {
        this.total = this.precio * this.cantidad;
    }

    @Override
    public String toString() {
        return "Platillo: " + platillo + " | Precio: " + precio + " | Cantidad: " + cantidad + " | Total: " + total + " | Fecha: " + fechaVenta;
    }
}

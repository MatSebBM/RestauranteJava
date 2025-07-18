package model;

public class Platillo {
    private String nombre;
    private String ingredientes;
    private double precio;

    public Platillo(String nombre, String ingredientes, double precio) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public String getIngredientes() { return ingredientes; }
    public double getPrecio() { return precio; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setIngredientes(String ingredientes) { this.ingredientes = ingredientes; }
    public void setPrecio(double precio) { this.precio = precio; }
}

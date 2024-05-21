package ejercicio.pedidosva;

public class Producto {
    private int id;
    private String nombre;
    private int cantidad;
    private double precioUnitario;

    public Producto(int id, String nombre, int cantidad, double precioUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return nombre + " - Cantidad: " + cantidad + " - Precio: $" + precioUnitario;
    }
}

/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 4 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/
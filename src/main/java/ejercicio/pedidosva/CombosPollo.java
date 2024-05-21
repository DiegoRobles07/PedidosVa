//clase para representar un objeto ComboPollo, con sus getters y setters respectivos
package ejercicio.pedidosva;


public class CombosPollo implements Combo {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String imagen;

    public CombosPollo(int id, String nombre, String descripcion, double precio, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
    }
    @Override
    public String getDescripcion() {
        return descripcion;
    }

    
    // Constructor, getters y setters de la clase CombosPollo

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public String getImagen() {
        return imagen;
    }
    @Override
    public int getId(){
        return id;
    }
}


/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 4 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/
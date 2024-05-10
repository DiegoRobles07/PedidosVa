
package ejercicio.pedidosva;

public class Desayunos implements Combo{
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String imagenRuta;

    public Desayunos(int id, String nombre, String descripcion, double precio, String imagenRuta) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.imagenRuta = imagenRuta;
    }

    // Constructor, getters y setters de la clase Desayuno
    @Override
    public String getDescripcion() {
        return descripcion;
    }

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
        return imagenRuta;
    }
}


package ejercicio.pedidosva;

    
public class Ensaladas implements Combo{
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private String imagen;

    public Ensaladas(int id, String nombre, String descripcion, double precio, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen = imagen;
    }

    // Constructor, getters y setters de la clase CombosPollo
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
        return imagen;
    }
    @Override
    public int getId(){
        return id;
    }
}

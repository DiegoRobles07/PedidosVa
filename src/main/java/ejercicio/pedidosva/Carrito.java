
package ejercicio.pedidosva;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
     private List<Producto> productos;

    public Carrito() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

}

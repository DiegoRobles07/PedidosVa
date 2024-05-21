//clase para representar un objeto Carrito, con sus getters y setters respectivos y funciones para eliminar o agregar al carrito, entre otras
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
        System.out.println("Producto agregado al carrito: " + producto.getNombre());
        System.out.println("Cantidad en carrito después de agregar: " + productos.size());
    }

    public void eliminarProducto(Producto producto) {
        productos.remove(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecioUnitario() * producto.getCantidad();
        }
        return total;
    }

    public void mostrarContenido() {
        System.out.println("Contenido del carrito:");
        for (Producto producto : productos) {
            System.out.println("Nombre: " + producto.getNombre() + ", Cantidad: " + producto.getCantidad() + ", Precio unitario: " + producto.getPrecioUnitario());
        }
    }

    public void vaciarCarrito() {
        productos.clear();
        System.out.println("El carrito ha sido vaciado.");
    }

}


/*Autor Diego Rene Robles Estrada RE100123
PRUEBA PARCIAL 4 PROGRAMACION ORIENTADA A OBJETOS
2024
/*/
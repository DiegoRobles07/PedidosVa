package ejercicio.pedidosva;
import java.util.ArrayList;
import java.util.List;
import ejercicio.pedidosva.SessionObserver;

public class InicioDeSesion {
    private static InicioDeSesion instancia;
    private boolean sesionIniciada = false;
    private int idUsuarioActual;
    private List<SessionObserver> observadores = new ArrayList<>();

    // Constructor privado para evitar la creación de instancias fuera de esta clase
    private InicioDeSesion() {}

    // Método estático para obtener la instancia única de InicioDeSesion
    public static InicioDeSesion obtenerInstancia() {
        if (instancia == null) {
            instancia = new InicioDeSesion();
        }
        return instancia;
    }

    // Métodos para iniciar y verificar la sesión
    public void iniciarSesion(int idUsuario) {
        this.sesionIniciada = true;
        this.idUsuarioActual = idUsuario;
        notificarSesionIniciada(idUsuario);
    }

    public void cerrarSesion() {
        this.sesionIniciada = false;
        this.idUsuarioActual = -1;
        notificarSesionCerrada();
    }

    public boolean sesionIniciada() {
        return sesionIniciada;
    }

    public int getIdUsuarioActual() {
        return idUsuarioActual;
    }

    // Métodos para agregar y remover observadores
    public void agregarObservador(SessionObserver observador) {
        observadores.add(observador);
    }

    public void removerObservador(SessionObserver observador) {
        observadores.remove(observador);
    }

    // Métodos para notificar a los observadores
    private void notificarSesionIniciada(int idUsuario) {
        for (SessionObserver observador : observadores) {
            observador.onSesionIniciada(idUsuario);
        }
    }

    private void notificarSesionCerrada() {
        for (SessionObserver observador : observadores) {
            observador.onSesionCerrada();
        }
    }
}

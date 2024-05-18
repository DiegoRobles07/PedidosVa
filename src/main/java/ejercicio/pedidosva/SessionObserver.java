package ejercicio.pedidosva;


public interface SessionObserver {
    void onSesionIniciada(int Idusuario);
    void onSesionCerrada();
}

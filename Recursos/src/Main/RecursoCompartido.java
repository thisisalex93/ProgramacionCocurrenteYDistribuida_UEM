package Main;
import java.util.concurrent.Semaphore;

class RecursoCompartido {

    private int unidades;
    private Semaphore semaforo;

    public RecursoCompartido(int unidades) {
        this.unidades = unidades;
        this.semaforo = new Semaphore(unidades, true);
    }

    public boolean reservar(int n) {
        try {
            semaforo.acquire(n);
            unidades -= n;
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public void liberar(int n) {
        unidades += n;
        semaforo.release(n);
    }

    public int getUnidades() {
        return unidades;
    }
}
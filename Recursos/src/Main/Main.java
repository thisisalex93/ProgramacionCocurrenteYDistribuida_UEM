package Main;

public class Main {

    public static void main(String[] args) {
        final int NUM_PROCESOS = 10;
        final int UNIDADES_RECURSO = 5;

        RecursoCompartido recurso = new RecursoCompartido(UNIDADES_RECURSO);

        for (int i = 0; i < NUM_PROCESOS; i++) {
            Proceso proceso = new Proceso(i + 1, recurso);
            proceso.start();
        }
    }
}
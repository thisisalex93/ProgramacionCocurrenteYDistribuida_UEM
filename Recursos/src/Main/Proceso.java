package Main;

class Proceso extends Thread {

    private int id;
    private RecursoCompartido recurso;

    public Proceso(int id, RecursoCompartido recurso) {
        this.id = id;
        this.recurso = recurso;
    }

    public void run() {
        int unidades = (int) (Math.random() * 5) + 1;

        System.out.printf("Proceso %d quiere reservar %d unidades.%n", id, unidades);

        boolean reservado = recurso.reservar(unidades);

        if (reservado) {
            System.out.printf("Proceso %d reservo %d unidades. Quedan %d unidades.%n", id, unidades,
                    recurso.getUnidades());
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            recurso.liberar(unidades);
            System.out.printf("Proceso %d libero %d unidades. Quedan %d unidades.%n", id, unidades,
                    recurso.getUnidades());
        } else {
            System.out.printf("Proceso %d no pudo reservar %d unidades.%n", id, unidades);
        }
    }
}
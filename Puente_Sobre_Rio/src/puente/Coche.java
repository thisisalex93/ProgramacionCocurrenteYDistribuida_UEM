package puente;

class Coche extends Thread {
    private final String direccion;
    private final Puente puente;

    public Coche(String direccion, Puente puente) {
        this.direccion = direccion;
        this.puente = puente;
    }

    public void run() {
        try {
            System.out.println("Coche " + this.getId() + " quiere cruzar hacia el " + this.direccion);
            if (direccion.equals("norte")) {
                puente.cruzarNorte();
            } else if (direccion.equals("sur")) {
                puente.cruzarSur();
            }
            System.out.println("Coche " + this.getId() + " ha cruzado hacia el " + this.direccion);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
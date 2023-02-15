package puente;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Puente puente = new Puente();
        while (true) {
            // Generar una dirección aleatoria (norte o sur)
            String direccion = Math.random() < 0.5 ? "norte" : "sur";
            // Crear un objeto Coche con la dirección aleatoria y el objeto Puente compartido
            Coche coche = new Coche(direccion, puente);
            // Arrancar el hilo del coche para que intente cruzar el puente
            coche.start();
            // Esperar un tiempo aleatorio antes de crear el siguiente coche
            Thread.sleep((long) (Math.random() * 1000));
        }
    }
}

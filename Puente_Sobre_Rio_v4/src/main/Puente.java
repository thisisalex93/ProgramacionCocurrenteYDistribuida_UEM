package main;

public class Puente {
	private boolean puenteNorteOcupado;
	private boolean puenteSurOcupado;
	
	public Puente() {
		puenteNorteOcupado = false;
		puenteSurOcupado = false;
	}
	
	public synchronized void cruzarPuente(boolean vieneDelNorte) {
		while ((vieneDelNorte && puenteSurOcupado) || (!vieneDelNorte && puenteNorteOcupado)) {
			try {
				wait(); // Esperar a que el puente esté libre
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (vieneDelNorte) {
			puenteNorteOcupado = true;
		} else {
			puenteSurOcupado = true;
		}
		// Permitir el acceso al coche
		if (vieneDelNorte) {
			puenteNorteOcupado = false;
		} else {
			puenteSurOcupado = false;
		}
		notifyAll(); // Notificar a los otros hilos que el puente está libre
	}
}

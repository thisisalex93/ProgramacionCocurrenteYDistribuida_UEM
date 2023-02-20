package main;

public class TestPuente {
	public static void main(String[] args) {
		Puente puente = new Puente();
		int numCoches = 10;
		Thread[] coches = new Thread[numCoches];
		for (int i = 0; i < numCoches; i++) {
			final int j = i;
			boolean vieneDelNorte = (i % 2 == 0); // alternar coches del norte y del sur
			coches[i] = new Thread(new Runnable() {
				public void run() {
					System.out.println("Coche " + j + " llego desde " + (vieneDelNorte ? "el norte" : "el sur"));
					puente.cruzarPuente(vieneDelNorte);
					System.out.println("Coche " + j + " cruzo el puente");
				}
			});
			coches[i].start();
		}
		for (int i = 0; i < numCoches; i++) {
			try {
				coches[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}


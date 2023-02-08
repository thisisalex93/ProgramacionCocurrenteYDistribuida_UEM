package puente;

public class Main {
	  public static void main(String[] args) {
	    MonitorPuente monitorPuente = new MonitorPuente();
	    Coche coche1 = new Coche("norte", monitorPuente);
	    Coche coche2 = new Coche("sur", monitorPuente);
	    Coche coche3 = new Coche("norte", monitorPuente);
	    Coche coche4 = new Coche("sur", monitorPuente);
	    coche1.start();
	    coche2.start();
	    coche3.start();
	    coche4.start();
	  }
	}

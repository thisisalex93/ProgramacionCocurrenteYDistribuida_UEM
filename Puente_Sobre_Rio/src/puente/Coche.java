package puente;

public class Coche extends Thread {
	  private String direccion;
	  private MonitorPuente monitorPuente;

	  public Coche(String direccion, MonitorPuente monitorPuente) {
	    this.direccion = direccion;
	    this.monitorPuente = monitorPuente;
	  }

	  @Override
	  public void run() {
	    monitorPuente.cruzarPuente(direccion);
	    System.out.println("Coche cruzando el puente en direccion " + direccion);
	    try {
	      Thread.sleep(1000);
	    } catch (InterruptedException e) {
	      System.out.println(e.getStackTrace());
	    }
	    monitorPuente.salirPuente();
	    System.out.println("Coche ha salido del puente en direccion " + direccion);
	  }
	}

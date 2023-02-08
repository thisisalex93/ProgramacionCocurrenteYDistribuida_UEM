package puente;

public class MonitorPuente {
	  private int cochesCruzando = 0;
	  private String direccionActual = "norte";

	  public synchronized void cruzarPuente(String direccion) {
	    while (!direccion.equals(direccionActual) || cochesCruzando > 0) {
	      try {
	        wait();
	      } catch (InterruptedException e) {
	    	  System.out.println(e.getStackTrace());
	      }
	    }
	    cochesCruzando++;
	  }
	  public synchronized void salirPuente() {
		    cochesCruzando--;
		    direccionActual = (direccionActual.equals("norte")) ? "sur" : "norte";
		    notifyAll();
		  }
		}
	 

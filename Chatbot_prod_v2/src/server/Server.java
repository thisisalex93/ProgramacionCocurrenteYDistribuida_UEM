package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
// Clase servidor que se encarga de ejecutar un bucle infinito esperando nuevas conexiones hasta que se pare el proceso.
public class Server {

    public static void main(String[] args) {
    	
    	try {
			ServerSocket server = new ServerSocket(5000);
			Socket socket;
			
			System.out.println("Server running...");
			
			while(true) {
				socket = server.accept();
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				
				out.writeUTF("Indicate your name");
				String name = in.readUTF();
				//Se instancia un nuevo hilo que gestionara el ciclo de vida del cliente en cuestion
				ServerThread st = new ServerThread(socket, in, out, name);
				st.start();
				
				System.out.println("Connection (" + socket.getPort() + ") created with client: " + name);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}


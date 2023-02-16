package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
//Hilo del servidor encargado de manejar las opciones transferidas por el hilo del cliente. Se encarga sobre todo de logs y confirmaciones al cliente.
public class ServerThread extends Thread{
	
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private String name;
	
	public ServerThread(Socket socket, DataInputStream in, DataOutputStream out, String name) {
		super();
		this.socket = socket;
		this.in = in;
		this.out = out;
		this.name = name;
	}
	@Override
	public void run() {
		
		int option;
		boolean exit = false;
		
		while(!exit) {
			try {
				option = in.readInt();
				
				switch(option) {
					case 1:
						int randomNumber = in.readInt();
						System.out.println("Client " + name +" rolled a die: " + randomNumber);
						out.writeUTF("Die successfully rolled");
						break;
					case 2:
						String date = in.readUTF();
						System.out.println("Client " + name +" asked for today's date: " + date);
						out.writeUTF("Date successfully sent");
						break;
					case 3:
						String time = in.readUTF();
						System.out.println("Client " + name +" asked for actual time: " + time);
						out.writeUTF("Time successfully sent");
						break;
					case 4:
						int daysForSanta = in.readInt();
						System.out.println("Client " + name +" asked for how many days until Santa comes: " + daysForSanta + " days.");
						out.writeUTF("Days successfully sent.");
						break;
					case 5:
						exit = true;
						break;
					default:
						out.writeUTF("Only numbers between 1 - 5 are allowed");
						
				}	
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Client " + name + " closed the connection");
	}
}

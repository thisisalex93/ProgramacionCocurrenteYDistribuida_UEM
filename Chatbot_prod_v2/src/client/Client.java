package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
//Clase cliente para instanciar nuevas conexiones

public class Client {
    public static void main(String[] args) {
    	try {
    		Scanner sc = new Scanner(System.in);
    		
        	sc.useDelimiter("\n");
        	
        	Socket socket = new Socket("127.0.0.1", 5000);
        	
        	DataInputStream in = new DataInputStream(socket.getInputStream());
    		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
    		
    		String message = in.readUTF();
    		System.out.println(message);
    		
    		String name = sc.next();
    		out.writeUTF(name);
    		ClientThread ct = new ClientThread(in, out);
    		ct.start();
    		
    		
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
}

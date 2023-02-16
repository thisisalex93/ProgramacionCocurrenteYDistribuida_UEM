package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
// Hilo del cliente encargado de mostrar el menu, obtener la opcion seleccionada, aplicar la logica necesaria, pasarla al hilo servidor y constestar al cliente.
public class ClientThread  extends Thread {
	
	private DataInputStream in;
	private DataOutputStream out;
	
	public ClientThread(DataInputStream in, DataOutputStream out) {
		super();
		this.in = in;
		this.out = out;
	}
	
	@Override
	public void run() {
		
		Scanner sc = new Scanner(System.in);
		int option = 0;
		boolean exit = false;
		String message;
		
		while(!exit) {
			System.out.println("1 - Roll a die");
			System.out.println("2 - Tell me the date");
			System.out.println("3 - Tell me the time");
			System.out.println("4 - When Santaclaus is coming");
			System.out.println("5 - Exit");
			
			option = sc.nextInt();
				try {
					out.writeInt(option);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			switch(option) {
			case 1:
				int randomNumber = randomNumber(1, 6);
				System.out.println("A die has been rolled: " + randomNumber);
				try {
					out.writeInt(randomNumber);
					message = in.readUTF();
					System.out.println(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case 2:
				String date = todaysDate().toString();
				System.out.println("Today is: " + date);
				try {
					out.writeUTF(date);
					message = in.readUTF();
					System.out.println(message);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
				
			case 3:
				String time = timeNow().toString();
				System.out.println("Actual time is: " + time);
				try {
					out.writeUTF(time);
					message = in.readUTF();
					System.out.println(message);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;	
				
			case 4:
				int daysForSanta = daysForSanta();
				System.out.println("Santa is coming in : " + daysForSanta + " days.");
				try {
					out.writeInt(daysForSanta);
					message = in.readUTF();
					System.out.println(message);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
				
			case 5:
				exit = true;
				break;
				
			default:
				try {
					message = in.readUTF();
					System.out.println(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	
			}
		}
	}
	
	public int randomNumber(int min, int max) {
		int num = (int)Math.floor(Math.random()*(max-min+1)+(min));
		return num;
	}
	public LocalDate todaysDate() {
		return LocalDate.now();
	}
	
	public LocalTime timeNow() {
		return LocalTime.now();
	}
	
	public int daysForSanta() {
		
		LocalDate actualDate = LocalDate.now();
        LocalDate christmas = LocalDate.of(actualDate.getYear(), Month.DECEMBER, 25);
        if (actualDate.isAfter(christmas)) {
        	christmas = christmas.plusYears(1); // Si ya pasó Navidad este año, se toma la del año siguiente
        }
        int daysToChristmas = (int) ChronoUnit.DAYS.between(actualDate, christmas);
        return daysToChristmas;
	}
}

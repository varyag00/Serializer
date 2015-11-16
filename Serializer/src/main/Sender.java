package main;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;

public class Sender {

	private static Socket sock;
	private static int port = 3000;
	private static String ip = "localhost";
	
	private static ObjectCreator objCreator;
	private static Serializer ser;
	private static Scanner scanner;
	
	public static void main(String[] args) throws Exception{
		initialize(args);	
		

				
		try{
				//prompt for user input
			System.out.println("Enter your choice of object, from 0 (least complex) to 4 (most complex)");
			scanner = new Scanner(System.in );
			String in = scanner.next();
			
				//create specified object
			Object obj = objCreator.createObject(Integer.parseInt(in));
			
			
			//allow user to edit object
			//TODO lol eventually (use reflection to edit each field
			
			 //serialize object and convert to string
			Document doc = ser.serialize(obj);
			XMLOutputter outputter = new XMLOutputter();
			String output = outputter.outputString(doc);
			
				//connect to a remote socket
			System.out.println("Connecting to \"" + ip + "\" on port " + port + "...");
			sock = new Socket(ip, port);		
			
				//write to a remote socket
			System.out.println("Sending XML file...");
			OutputStream out = sock.getOutputStream();
			
			out.write(output.getBytes());
			out.flush();
			
				//clean up
			System.out.println("Exiting...");
			sock.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void initialize(String[] args) throws Exception {
		
		objCreator = new ObjectCreator();
		ser = new Serializer();
		
		try {
			if (args.length == 2)
			{
				ip = args[0];
				port = Integer.parseInt(args[1]);
			}
		}
		catch (Exception e){
			System.out.println("Invalid input. Format: Sender <ip> <port>"); 
		}
	}

}


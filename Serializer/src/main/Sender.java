/* Name:		J. Daniel Gonzalez
 * UCID:		10058656
 * Class:		CPSC 501
 * Ass:			3
 * 
 * Sources: 	1. Used code provided by Waliur Rahman and Lee Ringham as templates for Serializer, Sender and Receiver
 * 				2. Used my own CPSC 501 Assignment 2 Object Inspect as a template for the Object Visualizer
 */

package main;

import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;

public class Sender {

	private static Socket sock;
	private static int port = 8001;
	private static String ip = "localhost";
	
	private static ObjectCreator objCreator;
	private static Serializer ser;
	private static Scanner scanner;
	
	public static void main(String[] args) throws Exception{
		initialize(args);	
				
		try{
				//prompt for user input
			System.out.println("=========================");
			System.out.println("Sender: Enter your choice of object, from 0 (least complex) to 4 (most complex)");
			scanner = new Scanner(System.in );
			String in = scanner.next();
			
				//create specified object
			Object obj = objCreator.createObject(Integer.parseInt(in));
			
			
			//allow user to edit object
			//TODO lol eventually (use reflection to edit each field)
			
			 //serialize object and convert to string
			Document doc = ser.serialize(obj);
			XMLOutputter outputter = new XMLOutputter();
			String output = outputter.outputString(doc);
			
				//connect to a remote socket
			System.out.println("Sender: Connecting to \"" + ip + "\" on port " + port + "...");
			sock = new Socket(ip, port);		
			
				//write to a remote socket
			System.out.print("Sender: Sending XML file.");
			for (int i = 0; i < 10; i++){
				System.out.print(".");
				TimeUnit.MILLISECONDS.sleep(100);
			}
			System.out.println();
			OutputStream out = sock.getOutputStream();
			
			out.write(output.getBytes());
			out.flush();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
				//clean up
			System.out.println("Sender: Exiting Sender...");
			System.out.println("=========================");
			sock.close();
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
			System.out.println("Sender: Invalid input. Format: Sender <ip> <port>"); 
		}
	}

}


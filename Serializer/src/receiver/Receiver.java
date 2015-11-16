/* Name:		J. Daniel Gonzalez
 * UCID:		10058656
 * Class:		CPSC 501
 * Ass:			3
 * 
 * Sources: 	1. Used code provided by Waliur Rahman and Lee Ringham as templates for Serializer, Sender and Receiver
 * 				2. Used my own CPSC 501 Assignment 2 Object Inspect as a template for the Object Visualizer
 */

package receiver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.jdom2.output.XMLOutputter;

public class Receiver {
	
	private static int timeout = 2000;
	private static final char END_OF_STREAM = (char)-1;
	private static int port = 8001;
	private static ServerSocket serverSock;
	private static Socket sock;
	
	public static void main(String[] args) throws Exception{
		
		initialize(args);
		
		try 
		{
				//create the socket 
			System.out.println("=========================");
			System.out.println("Receiver: Waiting for Sender to connect...\t");
			serverSock = new ServerSocket(port);
			sock = serverSock.accept();
			sock.setSoTimeout(timeout);			
			
				//read message
			String received = "";
			boolean socketClosed = false;
			while(!socketClosed)
			{
				System.out.println("Receiver: Sender Connected...");
				try 
				{
					char dataByte;
					while((dataByte = (char)sock.getInputStream().read()) != END_OF_STREAM)				
						received += Character.toString(dataByte);

					socketClosed = dataByte == END_OF_STREAM;
				} 
				catch(IOException e)
				{
					e.printStackTrace();
				}
				finally{
					socketClosed = true;
					sock.close();
					serverSock.close();
				}
			} 
			
				//convert string to XML document (use deserializer's method)
			//TODO	
			System.out.println("Receiver: Received string = " + received);//delete after

		}		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally{
				//close the socket a second time in case the first one failed....
			System.out.println("Receiver: Exiting Receiver...");
			System.out.println("=========================");
			sock.close();
			serverSock.close();
		}
	}
	
	public static void initialize(String[] args) {
		
		try {
			if (args.length == 1)
				port = Integer.parseInt(args[0]);
			
		}
		catch (Exception e){
			System.out.println("Receiver: Invalid input. Format: Sender <port>"); 
		}
	}
}
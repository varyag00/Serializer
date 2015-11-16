import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.jdom2.output.XMLOutputter;

public class Receiver {
	
	private static int timeout = 2000;
	private static final char END_OF_STREAM = (char)-1;
	private static int port = 3000;
	
	public static void main(String[] args) {
		
		initialize(args);
		
		try 
		{
				//create the socket 
			System.out.println("Waiting for Sender to connect...\t");
			ServerSocket serverSocket = new ServerSocket(port);
			Socket socket = serverSocket.accept();
			socket.setSoTimeout(timeout);			
			System.out.println("Connected...");
			
				//read message
			String received = "";
			boolean socketClosed = false;
			while(!socketClosed)
			{
				try 
				{
					char dataByte;
					while((dataByte = (char)socket.getInputStream().read()) != END_OF_STREAM)				
						received += Character.toString(dataByte);

					socketClosed = dataByte == END_OF_STREAM;
				} 
				catch(IOException e)
				{
					socketClosed = true;
				}

			} 
			
				//convert string to XML document (use deserializer's method)
			//TODO	
			
				//close the socket
			System.out.println("Exiting...");
			socket.close();
			serverSocket.close();
		}		
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void initialize(String[] args) {
		
		try {
			if (args.length == 1)
				port = Integer.parseInt(args[0]);
			
		}
		catch (Exception e){
			System.out.println("Invalid input. Format: Sender <ip>"); 
		}
	}
}
package main;

import ...;

public class Receiver {
	private static Deserializer deserializer = null;
	private static Visualizer visualizer = null;
	private static SocketAcceptor socketAcceptor = null;
	
	public static boolean connected;

	public static void main(String[] args) {
		initialize(args);

		while(connected) {
			String message = socketAcceptor.getMessage();
			if(!connected)
				continue;

			Document doc = deserializer.stringToDoc(message);
			Object obj = deserializer.deserialize(doc);
			visualizer.visualize(obj, true);
		}

		exit();
	}
	
	public static void initialize(String[] args) {
		connected = true;
		network = new Network();
		serializer = new Serializer();
		objectCreator = new ObjectCreator();
		
		if(args.length == 2) {
			network.setIP(args[0]);
			network.setPort(Integer.valueOf(args[1]));
		}
	}

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

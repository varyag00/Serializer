import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;

public class Sender {
	private static Network network = null;
	private static Serializer serializer = null;
	private static ObjectCreator objectCreator = null;

	private static boolean connected;

	public static void main(String[] args) {
		initialize(args);	// initialize the ip and port #

		Document doc = null;
		String XMLString = null;
		while(connected) {
			Object obj = objectCreator.createObject();
			
			if(!connected)
				continue;

			doc = serializer.serialize(obj);
			XMLString = XMLtoString(doc);

			network.send(XMLString);
		}

		exit();
	}

	public static boolean getConnected(){
		return connected;
	}
	public static void  setConnected(boolean state){
		connected = state;
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

	public static String XMLtoString(Document doc) {
		XMLOutputter xmlOutput = new XMLOutputter();
		return xmlOutput.outputString(doc);
	}

	public static void exit() {
		network.disconnect();
	}


}

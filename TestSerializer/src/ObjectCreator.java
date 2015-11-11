public class ObjectCreator {

	public Object createObject() {
		int selection = GUI.displayObjectMenu();
		Object object = null;
		switch(selection) {
			case 0:
				Sender.setConnected(false);
				break;
			case 1:
				object = createSimpleObject();
				break;
			case 2:
			case 3:
			case 4:
			case 5:
				object = createObjectsCollectionObject();
				break;
		}
		return object;
	}

	private SimpleObject createSimpleObject() {
		int a = GUI.getIntInput("enter value for field \"a\":");
		int b= GUI...
		return SimpleObject....;
	}


	private ObjectsCollectionObject createObjectsCollectionObject() {
		Vector<Object> list = new Vector<Object>();
		int collectionSize = GUI.getIntInput(....);
		for(int i = 0; i < collectionSize; i++) {
			list.add(createObject());
		}
		return new ObjectsCollectionObject(list);
	}




}

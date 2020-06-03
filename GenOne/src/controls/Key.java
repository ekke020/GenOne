package controls;

import java.util.HashMap;

public class Key	{
	
	protected static final HashMap<Integer, Key> keyBoolean = new HashMap<Integer, Key>();
	   // Creating the keys as simply variables
	protected static Key up = new Key();
	protected static Key down = new Key();
	protected static Key left = new Key();
	protected static Key right = new Key();
	//protected static Key special = new Key();
	


	protected boolean isDown;
	

	
	public boolean isDown() {
		return isDown;
	}

	/* toggles the keys current state*/
	protected void toggle(){
		isDown =  !isDown;
	}

	public static HashMap<Integer, Key> getKeyboolean() {
		return keyBoolean;
	}

}
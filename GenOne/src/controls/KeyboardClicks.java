package controls;

import java.awt.event.KeyEvent;
import java.util.EventObject;

public class KeyboardClicks extends EventObject {

	private int keyCode;
	
	public KeyboardClicks(KeyEvent key) {
		super(key);
		this.keyCode = key.getKeyCode();
	}

	public int getKeyCode() {
		return this.keyCode;
	}
}

package controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JPanel;

import overWorld.PlayerCharacter;

public class GameControls implements KeyListener{


	
	private KeyboardListener keyboardListener;
	public GameControls() {
		Key.keyBoolean.put(37, Key.left);
		Key.keyBoolean.put(38, Key.up);
		Key.keyBoolean.put(39, Key.right);
		Key.keyBoolean.put(40, Key.down);
	}
	public void keyTyped(KeyEvent e) {
				
	}

	
	public void keyPressed(KeyEvent e) {

		KeyboardClicks kc = new KeyboardClicks(e);
		if (e.getKeyCode() == 37) {
			if (Key.left.isDown() != true) {
				Key.left.toggle();
				PlayerCharacter.setCounter(0);
				for (Key key: Key.keyBoolean.values()) {
					if (!key.equals(Key.left))
						key.isDown = false;
				}
				
			}
		} // Left
		else if (e.getKeyCode() == 38) {
			if (Key.up.isDown() != true) {
				Key.up.toggle();
				for (Key key: Key.keyBoolean.values()) {
					if (!key.equals(Key.up))
						key.isDown = false;
				}
				
			}
		} // up
		else if (e.getKeyCode() == 39) {
			if (Key.right.isDown() != true) {
				Key.right.toggle();
				for (Key key: Key.keyBoolean.values()) {
					if (!key.equals(Key.right))
						key.isDown = false;
				}
				
			}
		} // right
		else if (e.getKeyCode() == 40) {
			if (Key.down.isDown() != true) {
				Key.down.toggle();
				for (Key key: Key.keyBoolean.values()) {
					if (!key.equals(Key.down))
						key.isDown = false;
				}
				
			}
		} // down
		if (keyboardListener != null) {
			keyboardListener.formEventOccurred(kc);
		}
		
	}
	/* 
	 * Create the logic behind the toggle for left, right, up and down. All keys should toggle to false when one key becomes true. 
	 * If one key is true then it should not toggle off until another key is pressed or said key is released.
	 * */
	
	public void keyReleased(KeyEvent e) {
		
		KeyboardClicks kc = new KeyboardClicks(e);
		if (e.getKeyCode() == 37) {
			Key.left.toggle();
		} // Left
		else if (e.getKeyCode() == 38) {
			Key.up.toggle();
		} // up
		else if (e.getKeyCode() == 39) {
			Key.right.toggle();
		} // right
		else if (e.getKeyCode() == 40) {
			Key.down.toggle();
		} // down
		if (keyboardListener != null) {
			keyboardListener.formEventOccurred(kc);
		}

	}
	
	public void setKeyboardListener(KeyboardListener keyboardListener) {
		
		this.keyboardListener = keyboardListener;
	}
	


}

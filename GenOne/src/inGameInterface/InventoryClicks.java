package inGameInterface;

import java.util.ArrayList;
import java.util.EventObject;


public class InventoryClicks extends EventObject {

	private int id;
	
	public int getID() {
		return this.id;
	}
	public InventoryClicks(Object source, int id) {
		super(source);
		this.id = id;
		
	}
	public InventoryClicks(Object source) {
		super(source);
	}

}

package testpack;

import java.util.EventObject;

public class ToolClicks extends EventObject {
	private int button;
	
	public ToolClicks(Object source) {
		super(source);
	}
	
	public ToolClicks(Object source, int x) {
		super(source);
		this.button = x;
	}

	public int getButton() {
		return button;
	}

	public void setButton(int button) {
		this.button = button;
	}
}

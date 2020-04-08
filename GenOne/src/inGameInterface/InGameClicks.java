package inGameInterface;

import java.util.EventObject;

public class InGameClicks extends EventObject {

	private int button;
	private double dButton;
	private String text;
	private boolean toggle;
	
	public boolean getToggle() {
		return this.toggle;
	}
	public double getDButton() {
		return this.dButton;
	}
	public int getButton() {
		return this.button;
	}
	public String getText() {
		return this.text;
	}
	public InGameClicks(Object source, int x) {
		super(source);
		this.button = x;
	}
	public InGameClicks(Object source, int x, String text) {
		super(source);
		this.button = x;
		this.text = text;
	}
	public InGameClicks(Object source, double x) {
		super(source);
		this.dButton = x;
	}
	public InGameClicks(Object source, int x, boolean toggle) {
		super(source);
		this.toggle = toggle;
		this.button = x;
	}
	
}

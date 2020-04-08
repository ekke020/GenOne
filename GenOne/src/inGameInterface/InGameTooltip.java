package inGameInterface;

import java.util.EventObject;

public class InGameTooltip extends EventObject{

	private int tooltip;
	
	public int getTooltip() {
		return this.tooltip;
	}
	public InGameTooltip(Object source, int x) {
		super(source);
		this.tooltip = x;
	}
}

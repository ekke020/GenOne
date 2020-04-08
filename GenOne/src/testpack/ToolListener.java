package testpack;

import java.util.EventListener;

public interface ToolListener extends EventListener{
	public void formEventOccurred(ToolClicks e);
}

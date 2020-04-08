package inGameInterface;

import java.util.EventListener;

import testpack.FormClicks;

public interface InGameListener extends EventListener  {
	public void formEventOccurred(InGameClicks e);
	
}

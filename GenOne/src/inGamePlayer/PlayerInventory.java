package inGamePlayer;

import java.util.ArrayList;

import items.Balls;
import items.Items;
import items.KeyItems;
import items.TMHM;

public class PlayerInventory {
	
	
	private ArrayList<Balls> balls; // List containing all of the players pokeballs.
	private ArrayList<Items> items; // List containing all of the players items.
	private ArrayList<KeyItems> keyItems; // List containing all key items of the player.
	private ArrayList<TMHM> tmhm; // List containing all the TM and HMs of the player.
	
	PlayerInventory() {
		balls = new ArrayList<Balls>();
		items = new ArrayList<Items>();
		keyItems = new ArrayList<KeyItems>();
		tmhm = new ArrayList<TMHM>();
		
		/*this.mainItems.addAll(balls);
		this.mainItems.addAll(items);
		this.mainItems.addAll(keyItems);
		this.mainItems.addAll(tmhm);*/
	}
	/*
	 * First iteration of the player inventory, figure out how to treat more than one of the same object in the list.
	 */
	public ArrayList<Balls> getPlayerBalls() {
		return this.balls;
	} 
	public ArrayList<Items> getPlayerItems() {
		return this.items;
	}
	public ArrayList<KeyItems> getPlayerKeyItems() {
		return this.keyItems;
	}
	public ArrayList<TMHM> getPlayerTMHM() {
		return this.tmhm;
	}
}

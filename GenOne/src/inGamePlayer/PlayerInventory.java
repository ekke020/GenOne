package inGamePlayer;

import java.util.ArrayList;
import java.util.stream.IntStream;

import items.GreatBall;
import items.Items;
import items.MasterBall;
import items.PokeBall;
import items.UltraBall;


public class PlayerInventory {
	
	private int[] items;
	private int[] amount;
	    // Rethink the whole item and inventory system, gather inspiration from sources
	//private ArrayList<Items> items = new ArrayList<Items>(); 
	private ArrayList<Items> balls = new ArrayList<Items>(); 
	private ArrayList<Items> keyItems = new ArrayList<Items>(); 
	private ArrayList<Items> tmhm = new ArrayList<Items>(); 
	
	PlayerInventory() {
		items = new int[10];
		amount = new int[10];

		addItem(generateItem(1), 5);
		addItem(generateItem(1), 5);
		addItem(generateItem(1), 5);
		addItem(generateItem(2), 5);
	
	}
    public int[] getItems() {
        return this.items;
    }
    public int getAmount(int x) {
        return this.amount[x];
    }
    public int getInventoryLength() {
    	int length = 0;
		for (int i = 0; i < items.length; i++) {
			length++;
			  if (this.items[i] == 0) {
				  length--;
				  break;
			  }
			}
		return length;
    }
	public Items generateItem(int x) {
		Items item[] = new Items[5];	
		item[1] = new PokeBall();
		item[2] = new GreatBall();
		item[3] = new UltraBall();
		item[4] = new MasterBall();
		return item[x];
	}
	private int find(int[] a, int target)
	{
		return IntStream.range(0, a.length)
						.filter(i -> target == a[i])
						.findFirst()
						.orElse(-1);	// return -1 if target is not found
	}
	public int getItemID(int x) {
		return this.items[x];
	}
	public void addItem(Items item, int x) {
		boolean itemExist = false;

		for (int i : items) {
			  if (item.getItemID() == i) {
				  this.amount[find(items, i)] += x;
				  itemExist = true;
				  break;
			  }
			}
		if (itemExist == false) {
			for (int i = 0; i < items.length; i++) {
				  if (this.items[i] == 0) {
					  this.items[i] = item.getItemID();
					  this.amount[i] = x;
					  break;
				  }
				}
		}
	}

	
}

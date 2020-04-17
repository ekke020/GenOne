package inGamePlayer;


import java.util.stream.IntStream;

import items.GreatBall;
import items.HyperPotion;
import items.Items;
import items.MasterBall;
import items.MaxPotion;
import items.PokeBall;
import items.Potion;
import items.SuperPotion;
import items.UltraBall;


public class PlayerInventory {
	
	private int[] items;
	private int[] itemsAmount;
	private int[] pokeBalls;
	private int[] pokeBallsAmount;
	private int[] keyItems;
	private int[] keyItemsAmount;
	private int[] tmhm;
	private int[] tmhmAmount;
	private int[] copy;
	private int[] copyAmount;
	
	PlayerInventory() {
		pokeBalls = new int[10];
		pokeBallsAmount = new int[10];
		
		items = new int[10];
		itemsAmount = new int[10];
		
		keyItems = new int[10];
		keyItemsAmount = new int[10];
		
		tmhm = new int[10];
		tmhmAmount = new int[10];

		addItem(generateItem(2), 5);
		addItem(generateItem(1), 5);
		addItem(generateItem(1), 5);
		addItem(generateItem(2), 5);
		addItem(generateItem(4), 5);
		addItem(generateItem(4), 5);
		addItem(generateItem(3), 5);
		addItem(generateItem(5), 5);
	
	}
    public int[] getItems() {
        return this.items;
    }
    public int getItemsAmount(int x) {
        return this.itemsAmount[x];
    }
    public int[] getPokeBalls() {
        return this.pokeBalls;
    }
    public int getPokeBallsAmount(int x) {
        return this.pokeBallsAmount[x];
    }
    public int[] getkeyItems() {
    	return this.keyItems;
    }
    public int getKeyItemsAmount(int x) {
    	return this.keyItemsAmount[x];
    }
    public int[] getTMHM() {
    	return this.tmhm;
    }
    public int getTMHMAmount(int x) {
    	return this.tmhmAmount[x];
    }
    public int getAmount(int cat, int x) {
    	if (cat == 0) {
    		return this.itemsAmount[x];
    	}
    	else if (cat == 1) {
    		return this.pokeBallsAmount[x];
    	}
    	else if (cat == 2) {
    		return this.keyItemsAmount[x];
    	}
    	else if (cat == 3) {
    		return this.tmhmAmount[x];
    	}
    	return 0;
    }
    public int getInventoryLength(int cat) {
    	copy = new int[10];
    	
    	if (cat == 0) {
    		copy = items;
    	}
    	else if (cat == 1) {
    		copy = pokeBalls;
    		//System.arraycopy(pokeBalls, 0, copy, 0, pokeBalls.length);	
    	}
    	else if (cat == 2) {
    		copy = keyItems;
    	}
    	else if (cat == 3) {
    		copy = tmhm;
    	}
    	int length = 0;
		for (int i = 0; i < copy.length; i++) {
			length++;
			  if (this.copy[i] == 0) {
				  length--;
				  break;
			  }
			}
		return length;
    }
	public Items generateItem(int x) {
		Items item[] = new Items[9];	
		item[1] = new PokeBall();
		item[2] = new GreatBall();
		item[3] = new UltraBall();
		item[4] = new MasterBall();
		item[5] = new Potion();
		item[6] = new SuperPotion();
		item[7] = new HyperPotion();
		item[8] = new MaxPotion();
		return item[x];
	}
	private int find(int[] a, int target)
	{
		return IntStream.range(0, a.length)
						.filter(i -> target == a[i])
						.findFirst()
						.orElse(-1);	// return -1 if target is not found
	}
	public int getItemID(int x, int index) {
		if (index == 0)
			return this.items[x];
		if (index == 1)
			return this.pokeBalls[x];
		if (index == 2)
			return this.keyItems[x];
		if (index == 3)
			return this.tmhm[x];
		
		return 0;
	}
	public void addItem(Items item, int x) {
		copy = new int[10];
		copyAmount = new int[10];
		boolean itemExist = false;
		if (item.getItemType() == 0) {
    		copy = items;
    		copyAmount = itemsAmount;
    	}
    	else if (item.getItemType() == 1) {
    		copy = pokeBalls;
    		copyAmount = pokeBallsAmount;
    	}
    	else if (item.getItemType() == 2) {
    		copy = keyItems;
    		copyAmount = keyItemsAmount;
    	}
    	else if (item.getItemType() == 3) {
    		copy = tmhm;
    		copyAmount = tmhmAmount;
    	}
		
		for (int i : copy) {
			  if (item.getItemID() == i) {
				  this.copyAmount[find(copy, i)] += x;
				  itemExist = true;
				  break;
			  }
			}
		if (itemExist == false) {
			for (int i = 0; i < copy.length; i++) {
				  if (this.copy[i] == 0) {
					  this.copy[i] = item.getItemID();
					  this.copyAmount[i] = x;
					  break;
				  }
				}
		}
	}
	public void removeItem(Items item, int amount) {
		
		copy = new int[10];
		copyAmount = new int[10];
		
		if (item.getItemType() == 0) {
    		copy = items;
    		copyAmount = itemsAmount;
    	}
    	else if (item.getItemType() == 1) {
    		copy = pokeBalls;
    		copyAmount = pokeBallsAmount;
    	}
    	else if (item.getItemType() == 2) {
    		copy = keyItems;
    		copyAmount = keyItemsAmount;
    	}
    	else if (item.getItemType() == 3) {
    		copy = tmhm;
    		copyAmount = tmhmAmount;
    	}
		
		for (int i : copy) {
			  if (item.getItemID() == i) {
				  this.copyAmount[find(copy, i)] -= amount;
				  if (this.copyAmount[find(copy,i)] == 0) {
					  System.out.println("found it");
					  System.out.println(copy[i]);
					  this.copy[find(copy, i)] = 0; 
				  }
				  break;
			  }
			}
	}

	
}

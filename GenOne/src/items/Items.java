package items;

public class Items {

	protected String itemName;
	protected String icon;
	protected int itemType; // General item == 0, PokeBalls == 1, KeyItems == 2, TM/HM == 3
	protected int itemID;
	
	public Items() {
		
	}
	public int getItemID() {
		return this.itemID;
	}
	public String getIcon() {
		return this.icon;
	}
	public String getItemName() {
		return this.itemName;
	}
}

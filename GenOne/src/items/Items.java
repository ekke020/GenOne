package items;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

import inGameInterface.BattleElements;

public class Items {

	protected String itemName;
	protected String icon;
	protected int itemType; // General item == 0, PokeBalls == 1, KeyItems == 2, TM/HM == 3
	protected int itemID;
	protected int itemEffect;
	
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
	public int getItemType() {
		return this.itemType;
	}
	public int getItemEffect() {
		return this.itemEffect;
	}
	protected void potionEffect() {
		
	}
	protected void itemEffect(int x, int y, int i, int z, BattleElements b1) {
	//	attackMove.setBounds(x, y, i, z);	
		b1.getBattleLpane().revalidate();
		b1.getBattleLpane().repaint();
	}
	protected ImageIcon loadItemEffect(String id, int x, int y) {
		String userDirectory = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Version 1.0 Folder" + File.separator + "Game assets" + File.separator + "AttackSprites";
		File custom = new File(userDirectory);
		String absoluteP = custom.getAbsolutePath();
		String imgFile = absoluteP + File.separator + id;
		ImageIcon icon = new ImageIcon(imgFile);
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(x, y,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		icon = new ImageIcon(newimg);
		System.out.println(id);
        return icon;
	}
}

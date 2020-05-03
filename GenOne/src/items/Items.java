package items;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.Line2D;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import inGameInterface.BattleElements;
import pokemon.Monsters;
import tools.GenerateRandomNum;

public class Items {

	protected JLabel itemSprite;
	
	protected static Timer timer;
	
	public Timer getTimer() {
		return timer;
	}
	protected String itemName;
	protected String icon;
	
	protected int itemType; // General item == 0, PokeBalls == 1, KeyItems == 2, TM/HM == 3
	protected int itemID;
	protected int itemEffect;
	protected double ballRate;
	
	protected int x;
	protected int y;
	protected int z;
	protected int jiggleCount;
	

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
	public void catchPokemon(BattleElements b1, Monsters m1) {
		
	}
	protected void itemEffect(int x, int y, int i, int z, BattleElements b1) {
		itemSprite.setBounds(x, y, i, z);
		b1.getBattleLpane().revalidate();
		b1.getBattleLpane().repaint();
	}
	protected ImageIcon loadItemEffect(String id, int x, int y) {
		String userDirectory = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Version 1.0 Folder" + File.separator + "Game assets" + File.separator + "EffectSprites";
		File custom = new File(userDirectory);
		String absoluteP = custom.getAbsolutePath();
		String imgFile = absoluteP + File.separator + id;
		ImageIcon icon = new ImageIcon(imgFile);
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(x, y,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		icon = new ImageIcon(newimg);
        return icon;
	}
	protected boolean catchCalculator(Monsters m1) {
		GenerateRandomNum rNum = new GenerateRandomNum();
		int bMax = m1.getMaxHP() * 3;
		int bCurrent = m1.getHP() * 2;
		
		if (bMax > 255) {
			bMax = bMax /4;
			bCurrent = bCurrent /4;
			if (bMax < 1)
				bMax = 1;
			if (bCurrent < 1)
				bCurrent = 1;
		}
		
		int btotal = (int)(((bMax - bCurrent) * (m1.getCatchrate() * this.ballRate)) / bMax);
		if (btotal > 255)
			btotal = 255;
		
		System.out.println(btotal);
			if (rNum.randomNumFromZero(256) <= btotal) {
				return true;
				// This is wrong, btotal should only be compared once
				// Shake tests should be compared 4 times in case btotal fails to beat the random number
			}
					
		return false;
	}
	protected int shakeCheck(Monsters m1) {
		int p = 63;
		GenerateRandomNum rNum = new GenerateRandomNum();
		
		int shakes = 0;
		int bMax = m1.getMaxHP() * 3;
		int bCurrent = m1.getHP() * 2;
		int btotal = (int)(((bMax - bCurrent) * (m1.getCatchrate() * this.ballRate)) / bMax);
		
		if (btotal < 2)
			p = 63;
		else if (btotal == 2)
			p = 75;
		else if (btotal == 3)
			p = 84;
		else if (btotal == 4)
			p = 90;
		else if (btotal == 5)
			p = 95;
		else if (btotal == 6 || btotal == 7)
			p = 103;
		else if (btotal >= 8 && btotal <= 10)
			p = 113;
		else if (btotal >= 11 && btotal <= 15)
			p = 126;
		else if (btotal >= 16 && btotal <= 20)
			p = 134;
		else if (btotal >= 21 && btotal <= 30)
			p = 149;
		else if (btotal >= 31 && btotal <= 40)
			p = 160;
		else if (btotal >= 41 && btotal <= 50)
			p = 169;
		else if (btotal >= 51 && btotal <= 60)
			p = 177;
		else if (btotal >= 61 && btotal <= 80)
			p = 191;
		else if (btotal >= 81 && btotal <= 100)
			p = 201;
		else if (btotal >= 101 && btotal <= 120)
			p = 211;
		else if (btotal >= 121 && btotal <= 140)
			p = 220;
		else if (btotal >= 141 && btotal <= 160)
			p = 227;
		else if (btotal >= 161 && btotal <= 180)
			p = 234;
		else if (btotal >= 181 && btotal <= 200)
			p = 240;
		else if (btotal >= 201 && btotal <= 220)
			p = 246;
		else if (btotal >= 221 && btotal <= 240)
			p = 251;
		else if (btotal >= 241 && btotal <= 254)
			p = 253;
		else if (btotal == 255)
			p = 255;
		
		for (int i = 0; i < 3; i++) {
			if (rNum.randomNumFromZero(256) < p) 
				shakes++;
			else 
				break;
		}
		return shakes;
	}

}

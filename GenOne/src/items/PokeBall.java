package items;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

import inGameInterface.BattleElements;

public class PokeBall extends Items {

	public PokeBall() {
		this.itemName = "Poke ball";
		this.icon = "Poke_Ball_Item_Sprite.png";
		this.itemType = 1;
		this.itemID = 1;
	}
	public void catchPokemon(BattleElements b1) {
		

		itemSprite = new JLabel();
		itemSprite.setIcon(loadItemEffect("Poke_Ball_Item_Sprite.png", 35, 35));
		x = 110;
		y = 290;
		z = 0;
		b1.getBattleLpane().add(itemSprite, JLayeredPane.PALETTE_LAYER);
		timer = new Timer(5, new ActionListener() {
			// 110, 290, 35, 35
			@Override
		      public void actionPerformed(ActionEvent e) {

				
				
				y--;
				x++;
			
				itemEffect(x, y, 35, 35, b1);
			
				
				if (x >= 300) { // 330
					System.out.println(y);
					Double angle = Math.toDegrees(Math.atan2(180 - x, 180 - y));
					System.out.println(angle);
					//System.out.println(Math.tan());
		        	((Timer) e.getSource()).stop();
				} 
		        
		      }
		    
			
		});
		timer.start();
	}

}

/*
 * 	if (itemSprite.getX() < 200) {
					y -= 2;
					x++;
					System.out.println("This Y: " + y);
				}
				if (itemSprite.getX() > 200) {
					System.out.println("This X: " + x);
					y++;
					x++;
				}
 */

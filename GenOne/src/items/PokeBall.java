package items;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		b1.getBattleLpane().add(itemSprite, JLayeredPane.PALETTE_LAYER);
		timer = new Timer(1, new ActionListener() {
			// 110, 290, 35, 35
			@Override
		      public void actionPerformed(ActionEvent e) {

				if (itemSprite.getX() < 330)
					itemEffect(x, y, 35, 35, b1);
				y--;
				x++;
				if (x < 250 && x % 2 == 0)
					//y--;
				if (x >= 330) {
					System.out.println(y);
		        	((Timer) e.getSource()).stop();
				} 
		        
		      }
		    
			
		});
		timer.start();
	}
}

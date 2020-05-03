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
import pokemon.Monsters;

public class PokeBall extends Items {

	public PokeBall() {
		this.itemName = "Poke ball";
		this.icon = "Poke_Ball_Item_Sprite.png";
		this.itemType = 1;
		this.itemID = 1;
		this.ballRate = 1;
	}
	public void catchPokemon(BattleElements b1, Monsters m1) {
		

		itemSprite = new JLabel();
		itemSprite.setIcon(loadItemEffect("Poke_Ball_Sprite_01.png", 35, 35));
		x = 110;
		y = 290;
		z = 0;
		b1.getBattleLpane().add(itemSprite, JLayeredPane.PALETTE_LAYER);
		timer = new Timer(2, new ActionListener() {
			// 110, 290, 35, 35
			@Override
		      public void actionPerformed(ActionEvent e) {

				
				if(x < 150) {
					y-= 2;
					x++;
				}
				else if(x < 160) {
					y-= 3;
					x++;
				}
				else if(x < 205) {
					y--;
					x++;
				}
				else if(x < 230) {
					if (x % 2 == 0)
						y--;
					x++;
				}
				else if(x < 360) {
					if (x % 3 == 0)
						y++;
					x++;
				}
				if (x <= 360) {
					itemEffect(x, y, 35, 35, b1);
				}
				if (x >= 359 && !(b1.getTimer().isRunning())) {
					x++;
				}
				if (x == 410) {
					itemSprite.setIcon(loadItemEffect("Poke_Ball_Sprite_02.png", 35, 35));
					b1.getBattleLpane().revalidate();
					b1.getBattleLpane().repaint();
					b1.shrinkPokemon(m1);
					x++;
				}
				if (x == 440) {
					itemSprite.setIcon(loadItemEffect("Poke_Ball_Sprite_03.png", 35, 45));
					itemEffect(360, y, 35, 45, b1);
					b1.getBattleLpane().revalidate();
					b1.getBattleLpane().repaint();
					
				}
				if (x == 460) {
					itemSprite.setIcon(loadItemEffect("Poke_Ball_Sprite_02.png", 35, 35));
					itemEffect(360, y, 35, 35, b1);
					b1.getBattleLpane().revalidate();
					b1.getBattleLpane().repaint();
				}
				if (x == 480) {
					itemSprite.setIcon(loadItemEffect("Poke_Ball_Sprite_01.png", 35, 35));
					b1.getBattleLpane().revalidate();
					b1.getBattleLpane().repaint();
				}
				if (x >= 480) { // 330
					jiggle(b1, m1, catchCalculator(m1));
		        	((Timer) e.getSource()).stop();
				} 
		        
		      }
		    
			
		});
		timer.start();
	}
	private void jiggle(BattleElements b1, Monsters m1, boolean capture) {
		x = 0;
		jiggleCount = 3;
		if (capture == false) {
			jiggleCount = shakeCheck(m1);
		}
		System.out.println(jiggleCount);
		m1.setCaught(capture);
		System.out.println("jiggle check : " + capture);
		timer = new Timer(25, new ActionListener() {
	
		      public void actionPerformed(ActionEvent e) {
		    	  if (x == 15) {
		    		  itemSprite.setIcon(loadItemEffect("Poke_Ball_Sprite_Jiggle_01.png", 35, 35));
		    		  b1.getBattleLpane().revalidate();
		    		  b1.getBattleLpane().repaint();
		    	  }
		    	  if (x == 10 || x == 20) {
		    		  itemSprite.setIcon(loadItemEffect("Poke_Ball_Sprite_Jiggle_02.png", 35, 35));
		    		  b1.getBattleLpane().revalidate();
		    		  b1.getBattleLpane().repaint();
		    	  }
		    	  if (x == 5 || x == 25 || x == 45) { 
		    		  itemSprite.setIcon(loadItemEffect("Poke_Ball_Sprite_Jiggle_03.png", 35, 35));
		    		  b1.getBattleLpane().revalidate();
		    		  b1.getBattleLpane().repaint();
		    	  }	
		    	  if (x == 30 || x == 40) {
		    		  itemSprite.setIcon(loadItemEffect("Poke_Ball_Sprite_Jiggle_04.png", 35, 35));
		    		  b1.getBattleLpane().revalidate();
		    		  b1.getBattleLpane().repaint();
		    	  }
		    	  if (x == 35) {
		    		  itemSprite.setIcon(loadItemEffect("Poke_Ball_Sprite_Jiggle_05.png", 35, 35));
		    		  b1.getBattleLpane().revalidate();
		    		  b1.getBattleLpane().repaint();
		    	  } 
		    	  x++;
		    	  if (x == 80) {
		    		  jiggleCount--;
		    		  x = 0;
		    	  }
		    	  //System.out.println(x);
		    	  if (jiggleCount == 0) {
		    		  if (capture == false) {
		    			  b1.getBattleLpane().remove(itemSprite);
		    			  b1.getBattleLpane().revalidate();
		    			  b1.getBattleLpane().repaint();
		    			  b1.enlargePokemon(m1);
		    		  }
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

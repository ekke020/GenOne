package moves;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

import inGameInterface.BattleElements;
import inGamePlayer.Player;
import pokemon.Monsters;

public class QuickAttack extends Moves {
	
	public QuickAttack() {
		this.power = 40;
		this.Accuracy = 100;
		this.type = "NORMAL";
		this.name = "QUICK ATTACK";
		this.category = "Damage";
		this.damageType = "Physical";
		this.priority = 2;
		this.maxPP = 30;
		this.pp = this.maxPP;
	}
	public void damageEventPlayer(BattleElements b1, Monsters m1, Player p1, int y) {
		x = 320;
	
		timer = new Timer(1, new ActionListener() {
			
			@Override
		      public void actionPerformed(ActionEvent e) {
				

				if (b1.getEnemySprite().getBounds().x >= 250) {
					b1.getEnemySprite().setBounds(x, 50, 180, 180); // x == 320
					x-=4;
				}				
		        b1.repaint();
		        if (x <= 250) { // x >= 90
		        	b1.getEnemySprite().setBounds(320, 50, 180, 180);
		        	b1.damageTakenPlayer(m1, p1, y);
		        	((Timer) e.getSource()).stop();
		         
		        }
		      }
		    
			
		});
		timer.start();
	}
	public void damageEvent(BattleElements b1, Monsters m1, Player p1, int y) {
		x = b1.getPlayerSprite().getBounds().x;
		
		attackMove = new JLabel();
		b1.getBattleLpane().add(attackMove, JLayeredPane.PALETTE_LAYER);
		
		timer = new Timer(1, new ActionListener() {
			
			@Override
		      public void actionPerformed(ActionEvent e) {
				

				if (b1.getPlayerSprite().getBounds().x >= -140) {
					b1.getPlayerSprite().setBounds(x, 170, 170, 180);
				}				
		        b1.repaint();
		        if (x == -140) {
		        	attackMove.setIcon(loadAttackMove("Physical.png", 70, 70));
					moveAttack(310, 140, 70, 70, b1);
		        }
		        if (x == -170) {
		        	moveAttack(360, 80, 70, 70, b1);
		        }
		        if (x == -200) {
		        	moveAttack(410, 20, 70, 70, b1);
		        }
		        x--;
		        if (x == -235) { // x >= 90
					b1.getBattleLpane().remove(attackMove);
		        	b1.getPlayerSprite().setBounds(50, 170, 170, 180);
					b1.getBattleLpane().revalidate();
					b1.getBattleLpane().repaint();
		        	b1.damageTaken(m1, p1, y);
		        	((Timer) e.getSource()).stop();
		         
		        }
		      }
		    
			
		});
		timer.start();
	}
}

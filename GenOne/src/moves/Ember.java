package moves;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

import inGameInterface.BattleElements;
import inGamePlayer.Player;
import pokemon.Monsters;

public class Ember extends Moves {

	public Ember() {
		this.Accuracy = 100;
		this.power = 40;
		this.type = "FIRE";
		this.name = "EMBER";
		this.category = "Damage";
		this.damageType = "Special";
		this.priority = 1;
		this.maxPP = 25;
		this.pp = this.maxPP;
		this.id = "EmberSmall.png";
	}
	
	public void damageEvent(BattleElements b1, Monsters m1, Player p1, int x) {
		
		attackMove = new JLabel();
		t = 20;
		b1.getBattleLpane().add(attackMove, JLayeredPane.PALETTE_LAYER);
		this.timer = new Timer(100, new ActionListener() {
			
			@Override
		      public void actionPerformed(ActionEvent e) {
				
				if (t == 20 || t == 18 || t == 16) {
					attackMove.setIcon(loadAttackMove("EmberSmall.png", 30, 30));
					moveAttack(340, 156, 40, 40, b1);
				}
				if (t == 19 || t == 17 || t == 15) {
					attackMove.setIcon(loadAttackMove("EmberSmall.png", 40, 40));	
					moveAttack(335, 151, 40, 40, b1);
				} 
				if (t == 14 || t == 12 || t == 10) {
					attackMove.setIcon(loadAttackMove("EmberSmall.png", 30, 30));
					moveAttack(400, 156, 40, 40, b1);
				}
				if (t == 13 || t == 11 || t == 9) {
					attackMove.setIcon(loadAttackMove("EmberSmall.png", 40, 40));
					moveAttack(395, 151, 40, 40, b1);
				}
				if (t == 8 || t == 6 || t == 4) {
					attackMove.setIcon(loadAttackMove("EmberSmall.png", 30, 30));
					moveAttack(370, 156, 40, 40, b1);
				}
				if (t == 7 || t == 5 || t == 3) {
					attackMove.setIcon(loadAttackMove("EmberSmall.png", 40, 40));
					moveAttack(365, 151, 40, 40, b1);
				}
				t--;
				if (t == 2) {
					b1.getBattleLpane().remove(attackMove);
					b1.getBattleLpane().revalidate();
					b1.getBattleLpane().repaint();
					b1.damageTaken(m1, p1, x);
		        	((Timer) e.getSource()).stop();
				}  
		      }
		});
		this.timer.start();
	}
	public void damageEventPlayer(BattleElements b1, Monsters m1, Player p1, int y) {
		
		attackMove = new JLabel();
		t = 20;
		b1.getBattleLpane().add(attackMove, JLayeredPane.PALETTE_LAYER);
		this.timer = new Timer(100, new ActionListener() {
			
			@Override
		      public void actionPerformed(ActionEvent e) {
				
				if (t == 20 || t == 18 || t == 16) {
					attackMove.setIcon(loadAttackMove("EmberSmall.png", 30, 30));
					moveAttack(60, 295, 40, 40, b1);
				}
				if (t == 19 || t == 17 || t == 15) {
					attackMove.setIcon(loadAttackMove("EmberSmall.png", 40, 40));	
					moveAttack(55, 289, 40, 40, b1);
				} 
				if (t == 14 || t == 12 || t == 10) {
					attackMove.setIcon(loadAttackMove("EmberSmall.png", 30, 30));
					moveAttack(120, 295, 40, 40, b1);
				}
				if (t == 13 || t == 11 || t == 9) {
					attackMove.setIcon(loadAttackMove("EmberSmall.png", 40, 40));
					moveAttack(115, 289, 40, 40, b1);
				}
				if (t == 8 || t == 6 || t == 4) {
					attackMove.setIcon(loadAttackMove("EmberSmall.png", 30, 30));
					moveAttack(85, 295, 40, 40, b1);
				}
				if (t == 7 || t == 5 || t == 3) {
					attackMove.setIcon(loadAttackMove("EmberSmall.png", 40, 40));
					moveAttack(80, 289, 40, 40, b1);
				}
				t--;
				if (t == 2) {
					b1.getBattleLpane().remove(attackMove);
					b1.getBattleLpane().revalidate();
					b1.getBattleLpane().repaint();
					b1.damageTakenPlayer(m1, p1, y);
		        	((Timer) e.getSource()).stop();
				}  
		      }
		});
		this.timer.start();
	}
}

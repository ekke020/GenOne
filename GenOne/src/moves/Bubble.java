package moves;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

import inGameInterface.BattleElements;
import inGamePlayer.Player;
import pokemon.Monsters;
import tools.RotateLabel;

public class Bubble extends Moves {

	public Bubble() {
		this.Accuracy = 100;
		this.power = 40;
		this.type = "WATER";
		this.name = "BUBBLE";
		this.category = "Damage";
		this.damageType = "Special";
		this.priority = 1;
		this.maxPP = 30;
		this.pp = this.maxPP;
	}
	
	public void damageEvent(BattleElements b1, Monsters m1, Player p1, int x) {
		
		attackMove = new JLabel();
		attackMoveTwo = new JLabel();
		attackMoveThree = new JLabel();
		attackMoveFour = new JLabel();
		t = 20;
		this.timer = new Timer(100, new ActionListener() {
			
			@Override
		      public void actionPerformed(ActionEvent e) {
				
				if (t == 18) {
					b1.getBattleLpane().add(attackMove, JLayeredPane.PALETTE_LAYER);
					attackMove.setIcon(loadAttackMove("BubbleOne.png", 65, 55));
					moveAttack(158, 175, 65, 55, b1);
				}
				if (t == 12) {
					b1.getBattleLpane().add(attackMoveTwo, JLayeredPane.PALETTE_LAYER);
					attackMoveTwo.setIcon(loadAttackMove("BubbleTwo.png", 70, 85)); // Width height
					moveAttackTwo(225, 161, 70, 85, b1);
				}
				if (t == 6) {
					b1.getBattleLpane().add(attackMoveThree, JLayeredPane.PALETTE_LAYER);
					attackMoveThree.setIcon(loadAttackMove("BubbleThree.png", 75, 80)); // Width height
					moveAttackThree(285, 112, 75, 80, b1);
				}
				if (t == 0) {
					b1.getBattleLpane().add(attackMoveFour, JLayeredPane.PALETTE_LAYER);
					attackMoveFour.setIcon(loadAttackMove("BubbleFour.png", 100, 115)); // Width height
					moveAttackFour(340, 70, 100, 115, b1);
				}
				t--;
				if (t == -8) {
					b1.getBattleLpane().remove(attackMove);
					b1.getBattleLpane().remove(attackMoveTwo);
					b1.getBattleLpane().remove(attackMoveThree);
					b1.getBattleLpane().remove(attackMoveFour);
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
		attackMoveTwo = new JLabel();
		attackMoveThree = new JLabel();
		attackMoveFour = new JLabel();
		//rotateLabel = new RotateLabel();
		t = 20;
		this.timer = new Timer(100, new ActionListener() {
			
			@Override
		      public void actionPerformed(ActionEvent e) {
				
				if (t == 18) {
					rotateLabel = new RotateLabel(loadAttackMove("BubbleOne.png", 65, 55), 90);
					b1.getBattleLpane().add(rotateLabel, JLayeredPane.PALETTE_LAYER);
					rotateLabel.setBounds(270, 180, 65, 55);
					b1.getBattleLpane().revalidate();
					b1.getBattleLpane().repaint();
					//moveAttack(158, 175, 65, 55, b1);
				}
				if (t == 12) {
					rotateLabel = new RotateLabel(loadAttackMove("BubbleTwo.png", 70, 85), 180);
					b1.getBattleLpane().add(rotateLabel, JLayeredPane.PALETTE_LAYER);
					rotateLabel.setBounds(195, 164, 70, 85);
					b1.getBattleLpane().revalidate();
					b1.getBattleLpane().repaint();
					//moveAttackTwo(225, 161, 70, 85, b1);
				}
				if (t == 6) {
					rotateLabel = new RotateLabel(loadAttackMove("BubbleThree.png", 75, 80), 180);
					b1.getBattleLpane().add(rotateLabel, JLayeredPane.PALETTE_LAYER);
					rotateLabel.setBounds(140, 220, 75, 80);
					b1.getBattleLpane().revalidate();
					b1.getBattleLpane().repaint();
					//moveAttackThree(285, 112, 75, 80, b1);
				}
				if (t == 0) {
					rotateLabel = new RotateLabel(loadAttackMove("BubbleFour.png", 100, 115), 180);
					b1.getBattleLpane().add(rotateLabel, JLayeredPane.PALETTE_LAYER);
					rotateLabel.setBounds(50, 220, 100, 115);
					b1.getBattleLpane().revalidate();
					b1.getBattleLpane().repaint();
					//moveAttackFour(340, 70, 100, 115, b1);
				} 
				t--;
				if (t == -8) {
					b1.getBattleLpane().remove(3);
					b1.getBattleLpane().remove(2);
					b1.getBattleLpane().remove(1);
					b1.getBattleLpane().remove(0);
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

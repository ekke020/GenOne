package moves;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.Timer;

import inGameInterface.BattleElements;
import inGamePlayer.Player;

public class Growl extends Moves {

	public Growl() {
		this.Accuracy = 100;
		this.power = -1;
		this.type = "NORMAL";
		this.name = "GROWL";
		this.category = "Status";
		this.id = "GrowlSleep.png";
		this.categoryNum = 1;
		this.priority = 1;
		this.maxPP = 40;
		this.pp = this.maxPP;
	}
	
	public void statusEvent(BattleElements b1) {
		x = 90;
		y = 260;
		attackMove = new JLabel(); 
		attackMove.setIcon(loadAttackMove(this.id, 40, 40));
		
		b1.getBattleLpane().add(attackMove, JLayeredPane.PALETTE_LAYER);
		
		timer = new Timer(5, new ActionListener() {
			
			@Override
		      public void actionPerformed(ActionEvent e) {
				
				if (x <= 180) {
					y--;
					moveAttack(x, y, 40, 40, b1);
				}
				else if (x <= 230) {
					y++;
					moveAttack(x, y, 40, 40, b1);
				}
				else if (x <= 360) {
					y--;
					moveAttack(x, y, 40, 40, b1);
				}
				x++;
				
		        if (x == 360) {
		        	b1.getBattleLpane().remove(attackMove);
		        	b1.getBattleLpane().revalidate();
		        	b1.getBattleLpane().repaint();
		        	((Timer) e.getSource()).stop();
		        }
		        
		      }
		    
			
		});
		timer.start();
	}
	public void statusEventPlayer(BattleElements b1) {
		x = 290;
		y = 155;
		attackMove = new JLabel(); 
		attackMove.setIcon(loadAttackMove(this.id, 40, 40));
		
		b1.getBattleLpane().add(attackMove, JLayeredPane.PALETTE_LAYER);
		
		timer = new Timer(5, new ActionListener() {
			
			@Override
		      public void actionPerformed(ActionEvent e) {
				
				
				if (x >= 220) {
					y++;
					moveAttack(x, y, 40, 40, b1);
				}
				else if (x >= 190) {
					y--;
					moveAttack(x, y, 40, 40, b1);
				}
				else if (x >= 180) {
					moveAttack(x, y, 40, 40, b1);
				}
				else if (x >= 47) {
					y++;
					moveAttack(x, y, 40, 40, b1);
				}
				x--;
				
		        if (x == 47) {
		        	b1.getBattleLpane().remove(attackMove);
		        	b1.getBattleLpane().revalidate();
		        	b1.getBattleLpane().repaint();
		        	((Timer) e.getSource()).stop();
		        }
		        
		      }
		    
			
		});
		timer.start();
	}
}

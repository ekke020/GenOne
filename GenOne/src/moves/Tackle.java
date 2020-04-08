package moves;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import inGameInterface.BattleElements;
import inGamePlayer.Player;
import pokemon.Monsters;

import javax.swing.Timer;

public class Tackle extends Moves {

	public Tackle() {
		this.power = 40;
		this.Accuracy = 100;
		this.type = "NORMAL";
		this.name = "TACKLE";
		this.category = "Damage";
		this.damageType = "Physical";
		this.priority = 1;
		this.maxPP = 35;
		this.pp = this.maxPP;
	}
	
	public void damageEventPlayer(BattleElements b1, Monsters m1, Player p1, int y) {
		x = 320;
	
		this.timer = new Timer(1, new ActionListener() {
			
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
		this.timer.start();
	}
	public void damageEvent(BattleElements b1, Monsters m1, Player p1, int y) {
		x = 50;
	
		this.timer = new Timer(1, new ActionListener() {
			
			@Override
		      public void actionPerformed(ActionEvent e) {
				

				if (b1.getPlayerSprite().getBounds().x <= 90) {
					b1.getPlayerSprite().setBounds(x, 170, 170, 180);
					x+=4;
				}				
		        b1.repaint();
		        if (x >= 90) { // x >= 90
		        	b1.getPlayerSprite().setBounds(50, 170, 170, 180);
		        	b1.damageTaken(m1, p1, y);
		        	((Timer) e.getSource()).stop();
		         
		        }
		      }
		    
			
		});
		this.timer.start();
	}
}

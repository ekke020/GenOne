package overWorld;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import controls.Key;
import tools.LoadSprites;

public class PlayerCharacter extends JLabel	{
	
	private LoadSprites loadSprites = new LoadSprites();
	private String dir;
	private static int counter;
	private static Timer timerTwo;
	PlayerCharacter() {
		this.setIcon(loadSprites.loadFrontSprite("Player_Sprite_03_South.png"));	
	}
	
	public void updateSprite() {
		// THIS DOES NOT WORK AT ALL, THE REPAINT OF THE PLAYER IS SCUFFED. NEED TO RETHINK THE LOGIC BEHIND IT.
		this.setIcon(loadSprites.loadFrontSprite(dir));
		/*for (Key key: Key.getKeyboolean().values()) {
			if (key.isDown() == true) {
				counter++;
				this.setIcon(loadSprites.loadFrontSprite(dir));
				break;
			}		
		} */
		timerTwo = new Timer(10, new ActionListener() {			
		      public void actionPerformed(ActionEvent e) {  
		    		  
		    	  if (counter < 25)
						testWorld.getP1().setDir("Player_Sprite_02_West.png");
		    	  else if (counter < 50)
						testWorld.getP1().setDir("Player_Sprite_03_West.png");
		    	  else if (counter < 75)
						testWorld.getP1().setDir("Player_Sprite_01_West.png");
		    	  else if (counter < 100){
						testWorld.getP1().setDir("Player_Sprite_03_West.png");
						//PlayerCharacter.setCounter(0);
						System.out.println(c);
						counter = 0;	
						((Timer) e.getSource()).stop();
					}
			
			}
		});
		timerTwo.start();
			
	}



	public static void setCounter(int counter) {
		PlayerCharacter.counter = counter;
	}

	public int getCounter() {
		return counter;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public void setPlayerSprite() {
		this.setIcon(loadSprites.loadFrontSprite("Player_Sprite_03_South.png"));
	}
}




/* OLD CODE THAT WAS USE IN INGAMEWINDOW UNDER THE GAMECONTROLLISTENER
 * 
 * if (k.getKeyCode() == 37) {	
					if (c % 3 == 0)
						testWorld.getP1().setDir("Player_Sprite_03_West.png");	
					else if (c % 2 == 0)
						testWorld.getP1().setDir("Player_Sprite_01_West.png");
					else 
						testWorld.getP1().setDir("Player_Sprite_02_West.png");
					//testWorld.updatePlayerBounds(testWorld.getP1().getX() - 5, testWorld.getP1().getY());
				} // Left
				else if (k.getKeyCode() == 38) {
					if (c % 10 == 0) {
						testWorld.getP1().setDir("Player_Sprite_03_North.png");
					}
					else if (c % 5 == 0)
						testWorld.getP1().setDir("Player_Sprite_01_North.png");
					else 
						testWorld.getP1().setDir("Player_Sprite_02_North.png");
					//testWorld.updatePlayerBounds(testWorld.getP1().getX(), testWorld.getP1().getY() - 5);
				} // up
				else if (k.getKeyCode() == 39) {
					if (c % 3 == 0)
						testWorld.getP1().setDir("Player_Sprite_03_East.png");
					else if (c % 2 == 0)
						testWorld.getP1().setDir("Player_Sprite_01_East.png");
					else 
						testWorld.getP1().setDir("Player_Sprite_02_East.png");
					//testWorld.updatePlayerBounds(testWorld.getP1().getX() + 5, testWorld.getP1().getY());
				} // right
				else if (k.getKeyCode() == 40) {
					if (c % 3 == 0) {
						testWorld.getP1().setDir("Player_Sprite_03_South.png");
					}
					else if (c % 2 == 0)
						testWorld.getP1().setDir("Player_Sprite_01_South.png");
					else 
						testWorld.getP1().setDir("Player_Sprite_02_South.png");
					//testWorld.updatePlayerBounds(testWorld.getP1().getX(), testWorld.getP1().getY() + 5);
				} // down
				
 */


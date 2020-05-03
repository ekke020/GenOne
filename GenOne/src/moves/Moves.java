package moves;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.Timer;

import inGameInterface.BattleElements;
import inGamePlayer.Player;
import pokemon.Monsters;
import tools.RotateLabel;

public class Moves implements java.io.Serializable{


	private static final long serialVersionUID = 1L;
	protected Timer timer;
	protected JLabel attackMove;
	protected JLabel attackMoveTwo;
	protected JLabel attackMoveThree;
	protected JLabel attackMoveFour;
	
	protected RotateLabel rotateLabel;
	
	protected int power = 0; // Hur mycket kraft attacken har.
	protected int Accuracy = 0; // Hur stor chans attacken har att träffa.
	protected int categoryNum = 0; // Vilken kategori attacken har ifall det är en status attack.
	protected int priority = 0; // vilken prioritering attacken har.
	protected int maxPP;
	protected int pp;
	
	protected int x;
	protected int y;
	protected int t;
	
	protected String damageType = null; // Om attacken är special eller physical.
	protected String name = null; // Attackens namn.
	protected String type = null; // Attackens typ.
	protected String tooltip = null; // inget som används.
	protected String category = null; // Om det är en status attack eller en damage attack.
	protected String id = null; // Håller länken till attacken.
	public int getMaxPP() {
		return this.maxPP;
	}
	public void setMaxPP(int maxPP) {
		this.maxPP = maxPP;
	}
	public int getPP() {
		return this.pp;
	}
	public void setMinPP(int minPP) {
		this.pp -= minPP;
	}
	public String getName() {
		return this.name;
	} // Returnerar attackens namn.
	public String getCategory() {
		return this.category;
	} // Returnerar vilken kategori attacken har.
	public String getDamageType() {
		return this.damageType;
	} // Returnerar attackens typ.
	public String getID() {
		return this.id;
	} // Returnerar attackens ID.
	public int getPriority() {
		return this.priority;
	} // Returnerar vilken prioritet attacken har.
	public int getCategoryNum() {
		return this.categoryNum;
	} // Returnerar vilken kategori attacken har.
	public String getType() {
		return this.type;
	} // Teturnerar attackens typ.
	public int getPower() {
		return this.power;
	}
	public int getAccuracy() {
		return this.Accuracy;
	}
	public Timer getTimer() {
		return this.timer;
	}
	public void damageEventPlayer(BattleElements b1, Monsters m1, Player p1, int y) {
		
	}
	public void damageEvent(BattleElements b1, Monsters m1, Player p1, int x) {
		
	}
	public void statusEventPlayer(BattleElements b1) {
		
	}
	public void statusEvent(BattleElements b1) {
		
	}
	protected void moveAttack(int x, int y, int i, int z, BattleElements b1) {
		attackMove.setBounds(x, y, i, z);	
		b1.getBattleLpane().revalidate();
		b1.getBattleLpane().repaint();
	}
	protected void moveAttackTwo(int x, int y, int i, int z, BattleElements b1) {
		attackMoveTwo.setBounds(x, y, i, z);	
		b1.getBattleLpane().revalidate();
		b1.getBattleLpane().repaint();
	}
	protected void moveAttackThree(int x, int y, int i, int z, BattleElements b1) {
		attackMoveThree.setBounds(x, y, i, z);	
		b1.getBattleLpane().revalidate();
		b1.getBattleLpane().repaint();
	}
	protected void moveAttackFour(int x, int y, int i, int z, BattleElements b1) {
		attackMoveFour.setBounds(x, y, i, z);	
		b1.getBattleLpane().revalidate();
		b1.getBattleLpane().repaint();
	}
	protected ImageIcon loadAttackMove(String id, int x, int y) {
		String userDirectory = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Version 1.0 Folder" + File.separator + "Game assets" + File.separator + "AttackSprites";
		File custom = new File(userDirectory);
		String absoluteP = custom.getAbsolutePath();
		String imgFile = absoluteP + File.separator + id;
		ImageIcon icon = new ImageIcon(imgFile);
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(x, y,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		icon = new ImageIcon(newimg);
		System.out.println(id);
        return icon;
	}
}





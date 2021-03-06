package inGameInterface;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.RoundRectangle2D;
import java.io.File;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import inGamePlayer.Player;
import items.Items;
import pokemon.Monsters;

public class BattleElements extends JPanel {
	
	private InGameListener inGameListener;
	
	private HealthBar playerHealthBar;
	private HealthBar enemyHealthBar;
	private Lines lines;
	private Circle circle;
	private TestLine testLine;
	
	private JLabel playerSprite;
	private JLabel enemySprite;
	private JLabel attackMove;
	
	private JTextField playerHealthField;
	private JTextField playerNameField;
	private JTextField playerLvlField;
	
	
	private JTextField enemyNameField;
	private JTextField enemyLvlField;
	
	private JLayeredPane battleLpane;

	private Font gameFont;
	private Color transparent;
	private InGameFont inGameFont;
	
	private int x = 500;
	private int y = 0;
	private int i = 0;
	private Timer timer;
	private boolean menu = false;
	private int c = 110;
	private int z = 250;
	private int hp;
	private int hptwo;
	private int size;
	
	public boolean isMenu() {
		return menu;
	}
	public void setMenu(boolean menu) {
		this.menu = menu;
	}
	public Timer getTimer() {
		return timer;
	}
	public JLabel getEnemySprite() {
		return enemySprite;
	}
	public void setEnemySprite(JLabel enemySprite) {
		this.enemySprite = enemySprite;
	}
	public JLabel getPlayerSprite() {
		return playerSprite;
	}
	public void setPlayerSprite(JLabel playerSprite) {
		this.playerSprite = playerSprite;
	}
	public JLayeredPane getBattleLpane() {
		return battleLpane;
	}
	
	public void playerToPokemon(Player p1, Monsters m1) {
		timer = new Timer(2, new ActionListener() {
			
			@Override
		      public void actionPerformed(ActionEvent e) {

				if (x > -172 )
					playerSprite.setBounds(x, 190, 170, 180);
				if (x == -172)
					add(circle);	
				if (x != -200) {
					x--;
				}
				if (!(x > -172)) {
					c--;
					z--;
					circle.setBounds(c, z, 170, 140);
					circle.grow();	
					//System.out.println("it grew");
					
				}
				
		        repaint();
		        if (x == -200) {
		        	remove(circle);
		        	playerSprite.setIcon(loadBackSprite(p1));
		    		playerSprite.setBounds(50, 190, 170, 140);
		    		addInterfaceElements(p1, m1);
		    		
		          ((Timer) e.getSource()).stop();
		         
		        }
		      }
		    
			
		});
		timer.start();

	}
	public void pokemonToPokemon(Player p1, int p, TextUI textUI) {
		x = 180;
		z = 250;
		i = 50;
		c = 170;
		circle = new Circle();
		circle.setBounds(50, 190, 170, 140);
		timer = new Timer(5, new ActionListener() {
			/// FIX EVERYTHING IN HERE!
			@Override
			public void actionPerformed(ActionEvent e) {

				if (playerSprite.getBounds().width > 1) {
					playerSprite.setBounds(i, c, x, x); // x == 320
					i--;
					if (playerSprite.getBounds().width % 2 == 0)
						c++;
				}
				if (playerSprite.getBounds().width == 1) {
					remove(playerHealthBar);
		    		remove(playerNameField);
		    		remove(playerLvlField);
		    		remove(playerHealthField);
					System.out.println(x);
					c = 110;
					playerSprite.setBounds(playerSprite.getBounds().x, playerSprite.getBounds().y, 0, playerSprite.getBounds().height);
					textUI.revalidateText();
					textUI.setNewText("Go! " + p1.getActiveTeam(p).getName() + "!", 0, false);
				}
				if (playerSprite.getBounds().width == 0) {
					c--;
					z--;
					add(circle);
					circle.setBounds(c, z, 170, 140);
					circle.grow();	
					//System.out.println("it grew");
				}
				x--;
		        repaint();
		        if (x == -60) {
		        	remove(circle);
		        	p1.swap(p, 0);
		        	playerSprite.setIcon(loadBackSprite(p1));
		    		playerSprite.setBounds(50, 190, 170, 140);
		    		updatePlayerInterFaceElements(p1);
		          ((Timer) e.getSource()).stop();
		         
		        }
			}
		    
			
		});
		timer.start();
	}
	public void npcDown() {
		x = 180;
		i = 50;
		transparent = new Color(0, 0, 0, 0);
		timer = new Timer(1, new ActionListener() {
			
			@Override
		      public void actionPerformed(ActionEvent e) {
				

				if (enemySprite.getBounds().x >= 0) {
					enemySprite.setBounds(320, i, 180, x); // x == 320
					x--;
					i++;
				}				
		        repaint();
		        if (x == -4) { // x >= 90
		        	remove(enemyHealthBar);
		        	remove(lines);
		    		remove(enemyNameField);
		    		remove(enemyLvlField);
		    		repaint();
		        	((Timer) e.getSource()).stop();
		         
		        }
		      }
		    
			
		});
		timer.start();
	}
	public void playerDown() {
		y = playerSprite.getY();
		
		transparent = new Color(0, 0, 0, 0);
		timer = new Timer(1, new ActionListener() {
			
			@Override
		      public void actionPerformed(ActionEvent e) {
				

				if (playerSprite.getBounds().y < 400) {
					y++;
					playerSprite.setBounds(50, y, 170, 140); // y == 190
					
				}				
		        repaint();
		        if (y == 400) { // x >= 90
		        	remove(playerHealthBar);
		        	remove(playerHealthField);
		    		remove(playerNameField);
		    		remove(playerLvlField);
		    		repaint();
		        	((Timer) e.getSource()).stop();
		         
		        }
		      }
		    
			
		});
		timer.start();
	}
	public void shrinkPokemon(Monsters m1) {
		 x = 320;
		 y = 50;
		 size = 120;
		 timer = new Timer(5, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				

				if (x % 2 == 0) {
					y++;
				}
				if (size % 2 == 0)
					x++;
				size--;
				if (size != 0)
					enemySprite.setIcon(loadFrontSprite(m1, size, size));
				enemySprite.setBounds(x, y, 180, 180);
		        repaint();
		        if (size == 0) { // x >= 90
		        	//System.out.println("this is X: " + x + "this is Y: " + y);
		        	 ((Timer) e.getSource()).stop();
		         
		        }
			}
		    
			
		 });
		 timer.start();
	}
	public void enlargePokemon(Monsters m1) {
		 x = 380;
		 y = 110;
		 size = 0;
		 timer = new Timer(5, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				

				if (x % 2 == 0) {
					y--;
				}
				if (size % 2 == 0)
					x--;
				size++;
				if (size != 0)
					enemySprite.setIcon(loadFrontSprite(m1, size, size));
				enemySprite.setBounds(x, y, 180, 180);
		        repaint();
		        if (size == 120) { // x >= 90
		        //	enemySprite.setBounds(320, 50, 180, 180);
		        	 ((Timer) e.getSource()).stop();
		         
		        }
			}
		    
			
		 });
		 timer.start();
	}
	public void damageTaken(Monsters m1, Player p1, int x) {
		y = 0;
		timer = new Timer(10, new ActionListener() {
			
			@Override
		      public void actionPerformed(ActionEvent e) {
				
				y++;
				if (y == 10) 
					enemySprite.setVisible(false);
				if (y == 20) 
					enemySprite.setVisible(true);
				if (y == 30) 
					enemySprite.setVisible(false);
				if (y == 40) 
					enemySprite.setVisible(true);
				if (y == 50) 
					enemySprite.setVisible(false);
				if (y == 60) 
					enemySprite.setVisible(true);
				if (y == 70) 
					enemySprite.setVisible(false);
				if (y == 80) 
					enemySprite.setVisible(true);
				if (y == 85) 
					enemySprite.setVisible(false);
				if (y == 100) 
					enemySprite.setVisible(true);
		        repaint();
		        if (y > 100) { // x >= 90
		        	enemySprite.setVisible(true);
		        	updateHealthNPC(m1, (int)m1.pDamage(p1, m1, x));
		        	((Timer) e.getSource()).stop();      
		        }
		      }
		    
			
		});
		timer.start();

	}
	public void damageTakenPlayer(Monsters m1, Player p1, int x) {
		y = 0;
		timer = new Timer(10, new ActionListener() {
			
			@Override
		      public void actionPerformed(ActionEvent e) {
				
				y++;
				if (y == 10) 
					playerSprite.setVisible(false);
				if (y == 20) 
					playerSprite.setVisible(true);
				if (y == 30) 
					playerSprite.setVisible(false);
				if (y == 40) 
					playerSprite.setVisible(true);
				if (y == 50) 
					playerSprite.setVisible(false);
				if (y == 60) 
					playerSprite.setVisible(true);
				if (y == 70) 
					playerSprite.setVisible(false);
				if (y == 80) 
					playerSprite.setVisible(true);
				if (y == 85) 
					playerSprite.setVisible(false);
				if (y == 100) 
					playerSprite.setVisible(true);
		        repaint();
		        if (y > 100) { // x >= 90
		        	playerSprite.setVisible(true);
		        	updateHealthPlayer(p1, m1.mDamage(p1, m1, x));
		        	((Timer) e.getSource()).stop();      
		        }
		      }
		    
			
		});
		timer.start();

	}
	private void imageSlide() {
		timer = new Timer(2, new ActionListener() {
		
			@Override
		      public void actionPerformed(ActionEvent e) {
				playerSprite.setBounds(x, 190, 170, 180);
				enemySprite.setBounds(y, 50, 180, 180);
				if (x != 50) {
					x--;
				}
				if (y != 320) {
					y++;
				}
		        repaint();
		        
		        if (x == 50 && y == 320) {
		        	menu = true;
		        	y = 0;
		          ((Timer) e.getSource()).stop();
		         
		        }
		      }
		    
			
		});
		timer.start();

	}

	BattleElements(Player p1, Monsters m1) {

		circle = new Circle();
		circle.setBounds(50, 190, 170, 140);
		setLayout(null);
		setBackground(Color.white);
		transparent = new Color( 0, 0, 0, 0);
		
		battleLpane = new JLayeredPane();
		battleLpane.setBounds(0, 0, 500, 500);
		
		add(battleLpane);
		playerSprite = new JLabel();
		playerSprite.setIcon(loadPlayerBackSprite(180, 180)); //loadBackSprite(p1)
		//playerSprite.setBounds(50, 190, 170, 140);
		battleLpane.add(playerSprite, JLayeredPane.DEFAULT_LAYER);
		
		enemySprite = new JLabel();
		enemySprite.setIcon(loadFrontSprite(m1, 120, 120));
		//enemySprite.setBounds(320, 50, 180, 180);
		battleLpane.add(enemySprite, JLayeredPane.DEFAULT_LAYER);
		imageSlide();
		
	}
	private void updatePlayerInterFaceElements(Player p1) {
		
		playerHealthBar = new HealthBar(p1.getFirstActiveTeam().getHP(), p1.getFirstActiveTeam().getMaxHP());
		playerHealthBar.setBounds(325, 285, 250, 100);
		add(playerHealthBar);
		
		playerHealthField = new JTextField(p1.getFirstActiveTeam().getHP() + " / " + p1.getFirstActiveTeam().getMaxHP());
		inGameFont = new InGameFont(gameFont, 1);
		playerHealthField.setBorder(BorderFactory.createEmptyBorder());
		playerHealthField.setBackground(transparent);
		playerHealthField.setEditable(false);
		playerHealthField.setBounds(380, 300, 150, 20);
		playerHealthField.setFont(inGameFont.getFont());
		playerHealthField.setHorizontalAlignment(SwingConstants.LEADING);
		add(playerHealthField);
		
		playerNameField = new JTextField(p1.getFirstActiveTeam().getName());
		inGameFont = new InGameFont(gameFont, 0);
		playerNameField.setBorder(BorderFactory.createEmptyBorder());
		playerNameField.setBackground(transparent);
		playerNameField.setEditable(false);
		playerNameField.setFont(inGameFont.getFont());
		playerNameField.setBounds(300, 240, 200, 30);
		add(playerNameField);

		playerLvlField = new JTextField(":L " + p1.getFirstActiveTeam().getLevel());
		inGameFont = new InGameFont(gameFont, 1);
		playerLvlField.setBorder(BorderFactory.createEmptyBorder());
		playerLvlField.setBackground(transparent);
		playerLvlField.setEditable(false);
		playerLvlField.setFont(inGameFont.getFont());
		playerLvlField.setBounds(400, 263, 80, 20);
		add(playerLvlField);
	
	}
	private void addInterfaceElements(Player p1 , Monsters m1) {
		
		playerHealthBar = new HealthBar(p1.getFirstActiveTeam().getHP(), p1.getFirstActiveTeam().getMaxHP());
		playerHealthBar.setBounds(325, 285, 250, 100);
		add(playerHealthBar);
		
		enemyHealthBar = new HealthBar(m1.getHP(), m1.getMaxHP());
		enemyHealthBar.setBounds(70, 45, 190, 75);
		add(enemyHealthBar);

		lines = new Lines();
		lines.setBounds(43, 40, 210, 40);
		add(lines);
		
		playerHealthField = new JTextField(p1.getFirstActiveTeam().getHP() + " / " + p1.getFirstActiveTeam().getMaxHP());
		inGameFont = new InGameFont(gameFont, 1);
		playerHealthField.setBorder(BorderFactory.createEmptyBorder());
		playerHealthField.setBackground(transparent);
		playerHealthField.setEditable(false);
		playerHealthField.setBounds(380, 300, 150, 20);
		playerHealthField.setFont(inGameFont.getFont());
		playerHealthField.setHorizontalAlignment(SwingConstants.LEADING);
		add(playerHealthField);
		
		playerNameField = new JTextField(p1.getFirstActiveTeam().getName());
		inGameFont = new InGameFont(gameFont, 0);
		playerNameField.setBorder(BorderFactory.createEmptyBorder());
		playerNameField.setBackground(transparent);
		playerNameField.setEditable(false);
		playerNameField.setFont(inGameFont.getFont());
		playerNameField.setBounds(300, 240, 200, 30);
		add(playerNameField);
		
		enemyNameField = new JTextField(m1.getName());
		inGameFont = new InGameFont(gameFont, 0);
		enemyNameField.setBorder(BorderFactory.createEmptyBorder());
		enemyNameField.setBackground(transparent);
		enemyNameField.setEditable(false);
		enemyNameField.setFont(inGameFont.getFont());
		enemyNameField.setBounds(30, 0, 200, 30);
		add(enemyNameField);
		
		playerLvlField = new JTextField(":L " + p1.getFirstActiveTeam().getLevel());
		inGameFont = new InGameFont(gameFont, 1);
		playerLvlField.setBorder(BorderFactory.createEmptyBorder());
		playerLvlField.setBackground(transparent);
		playerLvlField.setEditable(false);
		playerLvlField.setFont(inGameFont.getFont());
		playerLvlField.setBounds(400, 263, 80, 20);
		add(playerLvlField);
		
		enemyLvlField = new JTextField(":L " + m1.getLevel());
		inGameFont = new InGameFont(gameFont, 1);
		enemyLvlField.setBorder(BorderFactory.createEmptyBorder());
		enemyLvlField.setBackground(transparent);
		enemyLvlField.setEditable(false);
		enemyLvlField.setFont(inGameFont.getFont());
		enemyLvlField.setBounds(100, 25, 80, 20);
		add(enemyLvlField);			
	}
	private ImageIcon loadBackSprite(Player p1) {
		String userDirectory = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Version 1.0 Folder" + File.separator + "Game assets" + File.separator + "Sprites" + File.separator + "back";
		File custom = new File(userDirectory);
		String absoluteP = custom.getAbsolutePath();
		String imgFile = absoluteP +  File.separator + p1.getFirstActiveTeam().getID();
		ImageIcon icon = new ImageIcon(imgFile);
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(180, 180,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		icon = new ImageIcon(newimg);
	
        return icon;
	}
	private ImageIcon loadPlayerBackSprite(int x, int y) {
		String userDirectory = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Version 1.0 Folder" + File.separator + "Game assets" + File.separator + "Sprites" + File.separator + "characters";
		File custom = new File(userDirectory);
		String absoluteP = custom.getAbsolutePath();
		String imgFile = absoluteP +  File.separator + "Player_Back.png";
		ImageIcon icon = new ImageIcon(imgFile);
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(x, y,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		icon = new ImageIcon(newimg);
	
        return icon;
	}
	private ImageIcon loadFrontSprite(Monsters m1, int x, int y) {
		String userDirectory = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Version 1.0 Folder" + File.separator + "Game assets" + File.separator + "Sprites";
		File custom = new File(userDirectory);
		String absoluteP = custom.getAbsolutePath();
		String imgFile = absoluteP +  File.separator + m1.getID();
		ImageIcon icon = new ImageIcon(imgFile);
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(x, y,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		icon = new ImageIcon(newimg);
		
        return icon;
	}
	private void updateHealthNPC(Monsters m1,int x) {
		hp = x;
		hptwo = 1;
		Timer timer = new Timer(65, new ActionListener() { //45

			public void actionPerformed(ActionEvent e) {
				if (hp >= 1) {
					m1.setHP(hptwo);
					hp--;
				}	
				enemyHealthBar.updateHP(m1.getHP(), m1.getMaxHP());
				repaint();
				if (hp == 0) {
					((Timer) e.getSource()).stop();
					
				}	
			}
			
		});
		timer.start();
		
	}
	private void updateHealthPlayer(Player p1,int x) {
		hp = x;
		hptwo = 1;
		Timer timer = new Timer(65, new ActionListener() { //45

			public void actionPerformed(ActionEvent e) {
				if (hp >= 1) {
					if (p1.getFirstActiveTeam().getHP() > 0) {
						p1.getFirstActiveTeam().setHP(hptwo);
						hp--;
					}
					else if (p1.getFirstActiveTeam().getHP() == 0) 
						hp = 0;	
				}	
				playerHealthBar.updateHP(p1.getFirstActiveTeam().getHP(), p1.getFirstActiveTeam().getMaxHP());
				playerHealthField.setText(p1.getFirstActiveTeam().getHP() + " / " + p1.getFirstActiveTeam().getMaxHP());
				repaint();
				if (hp == 0) {
					((Timer) e.getSource()).stop();
					
				}	
			}
			
		});
		timer.start();
		
	}
	public void updateInterface(Player p1, int x) {
		playerHealthBar.updateHP(p1.getActiveTeam(x).getHP(), p1.getActiveTeam(x).getMaxHP());
		playerHealthField.setText(p1.getActiveTeam(x).getHP() + " / " + p1.getActiveTeam(x).getMaxHP());
		revalidate();
		repaint();
	}
	
}

class HealthBar extends JPanel {
		

	private double hpPercentage;
	private Color healthColor;

	HealthBar(int x, int y) {	
			hpPercentage = ((double)x/(double)y);
			
		}
	public void updateHP(int x, int y) {
		hpPercentage = (double)x/(double)y;//Math.round((double)x/(double)y * 10) / 10.0;
		//System.out.println(hpPercentage);
	}
	public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			healthColor = new Color(81, 90, 90, 100);
		    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		             RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(Color.BLACK);
			g2.drawRoundRect(0, 0, 160, 8, 15, 15);
			g2.setColor(healthColor);
			g2.fill(new RoundRectangle2D.Double(0, 0, (hpPercentage * 160), 8, 15, 15));
			//System.out.println(hpPercentage);
			/*g2.setColor(Color.BLACK);
			g2.drawRoundRect(0, 0, 160, 8, 15, 15);
			g2.setColor(healthColor);
			g2.fillRoundRect(0, 0, (int)(160 * hpPercentage), 8, 15, 15);
			System.out.println(hpPercentage);*/
			
		 }

}
class Lines extends JPanel {
	
	public void paint(Graphics g) {
		        
		Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(5));
        g2.setColor(Color.darkGray);
        g2.draw(new Line2D.Float(200, 29, 190, 24));
        
		Graphics2D g3 = (Graphics2D) g;
        g3.setStroke(new BasicStroke(8));
        g3.setColor(Color.darkGray);
        g3.draw(new Line2D.Float(0, 28, 0, 0));
        
		Graphics2D g4 = (Graphics2D) g;
        g4.setStroke(new BasicStroke(5));
        g4.setColor(Color.darkGray);
        g4.draw(new Line2D.Float(0, 29, 200, 29));
    	

	}
}

class TestLine extends JPanel {
	private Color transparent;

	TestLine() {
		transparent = new Color( 0, 0, 0, 0);
		setBackground(transparent);
	}
	
	public void paint(Graphics g) {
		
		Graphics2D g4 = (Graphics2D) g;
        g4.setStroke(new BasicStroke(5));
        g4.setColor(Color.darkGray);
        g4.draw(new Line2D.Float(130, 300, 320, 110));
        
		Graphics2D g5 = (Graphics2D) g;
		g5.setStroke(new BasicStroke(5));
		g5.setColor(Color.darkGray);
		g5.draw(new Line2D.Float(130, 300, 320, 200));
		
		Graphics2D g6 = (Graphics2D) g;
		g6.setStroke(new BasicStroke(5));
		g6.setColor(Color.darkGray);
		g6.draw(new Line2D.Float(130, 300, 320, 300));
		
		Graphics2D g7 = (Graphics2D) g;
		g7.setStroke(new BasicStroke(5));
		g7.draw(new CubicCurve2D.Double(130.0,300.0, 200.0, 70.0, 300.0, 150.0, 400.0, 190.0));
	}
}
class Circle extends JPanel {
	private int x = 10;
	private int y = 10;
	private int size = 10;
	Circle () {
		//this.x = x;
		//this.y = y;
	//	this.size = size;
	}
	public void grow(){
        size ++;
    }
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.drawOval(x + size/2, y + size/2, size, size);
		g2.setStroke(new BasicStroke(5));
	}
}
class Tooltip extends JPanel {
	
	private Font gameFont;

	private InGameFont inGameFont;
	private JTextArea tooltipArea;
	
	Tooltip(Player p1, int x) {
			        
		setLayout(null);
		setBackground(Color.white);
		
		tooltipArea = new JTextArea();
		inGameFont = new InGameFont(gameFont, 2);
		tooltipArea.setFont(inGameFont.getFont());
		//tooltipArea.setBorder(BorderFactory.createLoweredBevelBorder());
		tooltipArea.setEditable(false);
		//90, 45
		tooltipArea.setBounds(5, 5, 80, 35);
		tooltipArea.setText("TYPE/\n  " +  p1.getFirstActiveTeam().getMoves(x).getType() + "\n    " + p1.getFirstActiveTeam().getMoves(x).getPP() + "/" + p1.getFirstActiveTeam().getMoves(x).getMaxPP());
		add(tooltipArea);
	}
}
class TextUI extends JPanel {
	
	private InGameMouseClickListener inGameMouseClickListener;
	private Font gameFont;
	private InGameFont inGameFont;
	
	private JTextArea gameArea;
	private int charIndex = 0;
	
	private String text;
	private boolean cont = false;
	private boolean auto = false;
	private Timer timer;
	
	public Timer getTimer() {
		return timer;
	}
	public boolean isAuto() {
		return auto;
	}
	public void setAuto(boolean auto) {
		this.auto = auto;
	}
	public boolean isCont() {
		return cont;
	}
	public void setCont(boolean cont) {
		this.cont = cont;
	}
	TextUI() {
		
		EmptyBorder emptyBorder = new EmptyBorder(30,30,10,10);
		BevelBorder lowerBevel = new BevelBorder(BevelBorder.LOWERED);
		LineBorder outerLine = new LineBorder(Color.DARK_GRAY);
		
        CompoundBorder outter = new CompoundBorder(outerLine, lowerBevel);
        CompoundBorder inner = new CompoundBorder(outter, emptyBorder);
        
		setLayout(null);
		setBackground(Color.white);
		
		gameArea = new JTextArea();
		gameArea.setBounds(20, 10, 440, 120);
		gameArea.setEditable(false);
		gameArea.setBorder(inner);
		gameArea.addMouseListener(new MouseAdapter() {
			
			 public void mouseClicked(MouseEvent me) {
				 
				 InGameMouseClick mc = new InGameMouseClick(this);
				 
				 if (inGameMouseClickListener != null) {
					 inGameMouseClickListener.formEventOccurred(mc);
					}	
			 
			 }
		});
		
		add(gameArea);
	}
	private void timerText(boolean cont) {
		 timer = new Timer(40, new ActionListener() {
		
			@Override
		      public void actionPerformed(ActionEvent e) {
				try {
					gameArea.append(Character.toString(text.charAt(charIndex)));
				} catch (StringIndexOutOfBoundsException ex) {
					ex.printStackTrace();
				}
		        charIndex++;
		        if (charIndex >= text.length()) {
		        	setCont(cont);
		          ((Timer) e.getSource()).stop();
		        }
		      }
		    
			
		});
		timer.start();
	}
	public void setNewText(String text, int x, boolean cont) {
		this.charIndex = 0;
		inGameFont = new InGameFont(gameFont, x);
		gameArea.setFont(inGameFont.getFont());
		this.text = text.replaceAll("..(?!$)", "$0 ");
		timerText(cont);
	}
	public void setInstantText(String text) {
		gameArea.setFont(inGameFont.getFont());
		gameArea.setText(text.replaceAll("..(?!$)", "$0 "));
	}
	public Runnable revalidateText() {
		gameArea.setText("");
		return null;
	}
	public void setInGameMouseClickListener(InGameMouseClickListener listener) {
			this.inGameMouseClickListener = listener;
	}
	
}
class PlayerMainMenuUI extends JPanel {
		
	private InGameListener inGameListener;
	
	private JButton run; 
	private JButton fight;
	private JButton inventory;
	private JButton team;
		
	private Font gameFont;
	
	private InGameFont inGameFont;
	
	PlayerMainMenuUI() {
			
		setLayout(null);
		setBackground(Color.white);
		
		
		inGameFont = new InGameFont(gameFont, 2);
		// 146 width, 112 height
		team = new JButton("Team");
		team.setFont(inGameFont.getFont());
		//team.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		team.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				InGameClicks gc = new InGameClicks(this, 2);
					
				if (inGameListener != null) {
					inGameListener.formEventOccurred(gc);
				}	
					
			}
				
		});
		team.setBounds(73, 5, 70, 54);
		add(team);
			
		run = new JButton("Flee");
		run.setFont(inGameFont.getFont());
		//run.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		run.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				InGameClicks gc = new InGameClicks(this, 4, "Got away safely!");
					
				if (inGameListener != null) {
					inGameListener.formEventOccurred(gc);
				}	
					
			}
				
		});
		run.setBounds(73, 57, 70, 54);
		add(run);
			
		fight = new JButton("Fight");
		fight.setFont(inGameFont.getFont());
		//fight.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		fight.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				InGameClicks gc = new InGameClicks(this, 1);
					
				if (inGameListener != null) {
					inGameListener.formEventOccurred(gc);
				}	
					
			}
				
		});
		fight.setBounds(3, 5, 70, 54);
		add(fight);
			
		inventory = new JButton("Item");
		inventory.setFont(inGameFont.getFont());
		//inventory.setBorder(BorderFactory.createDashedBorder(null));
		inventory.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				InGameClicks gc = new InGameClicks(this, 3);
					
				if (inGameListener != null) {
					inGameListener.formEventOccurred(gc);
				}	
					
			}
				
		});
		inventory.setBounds(3, 57, 70, 54);
		add(inventory);
					
	}
	
	public void setInGameListener(InGameListener listener) {
		this.inGameListener = listener;
	}
}

class PlayerAttackMenuUI extends JPanel {
	
	private InGameListener inGameListener;
	private InGameTooltipListener inGameTooltipListener;
	private JButton attackOne; 
	private JButton attackTwo;
	private JButton attackThree;
	private JButton attackFour;
	private JButton back;
	private Font gameFont;
	
	private InGameFont inGameFont;
	
	PlayerAttackMenuUI(Player p1) {
				
			setLayout(null);
			setBackground(Color.white);
			
			inGameFont = new InGameFont(gameFont, 2);
			
			// 240, 15, 226, 112
			if (p1.getFirstActiveTeam().getMoves(0) != null) {
				attackOne = new JButton(p1.getFirstActiveTeam().getMoves(0).getName());
				attackOne.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						InGameClicks gc = new InGameClicks(this, 1);
							
						if (inGameListener != null) {
							inGameListener.formEventOccurred(gc);
						}	
							
					}
						
				});
				attackOne.addMouseListener(new MouseAdapter() {
					
					 
					 public void mouseEntered(MouseEvent me) {
						
						 InGameTooltip igt = new InGameTooltip(this, 1);
						 
						 if (inGameTooltipListener != null) {
							 inGameTooltipListener.formEventOccurred(igt);
							}	
					 }
					 public void mouseExited(MouseEvent me) {
						 
						 InGameTooltip igt = new InGameTooltip(this, 5);
						 
						 if (inGameTooltipListener != null) {
							 inGameTooltipListener.formEventOccurred(igt);
							}	
					 
					 }		
				});
				attackOne.setBounds(10, 10, 160, 20);
				attackOne.setFont(inGameFont.getFont());
				attackOne.setHorizontalAlignment(SwingConstants.LEFT);
				add(attackOne);	
			}
			if (p1.getFirstActiveTeam().getMoves(1) != null) {
				attackTwo = new JButton(p1.getFirstActiveTeam().getMoves(1).getName());
				attackTwo.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						InGameClicks gc = new InGameClicks(this, 2);
							
						if (inGameListener != null) {
							inGameListener.formEventOccurred(gc);
						}	
							
					}
						
				});
				attackTwo.addMouseListener(new MouseAdapter() {
					
					 
					 public void mouseEntered(MouseEvent me) {
						
						 InGameTooltip igt = new InGameTooltip(this, 2);
						 
						 if (inGameTooltipListener != null) {
							 inGameTooltipListener.formEventOccurred(igt);
							}	
					 }
					 public void mouseExited(MouseEvent me) {
						 
						 InGameTooltip igt = new InGameTooltip(this, 5);
						 
						 if (inGameTooltipListener != null) {
							 inGameTooltipListener.formEventOccurred(igt);
							}	
					 
					 }		
				});
				attackTwo.setBounds(10, 35, 160, 20);
				attackTwo.setFont(inGameFont.getFont());
				attackTwo.setHorizontalAlignment(SwingConstants.LEFT);
				add(attackTwo);
			}
			if (p1.getFirstActiveTeam().getMoves(2) != null) {
				attackThree = new JButton(p1.getFirstActiveTeam().getMoves(2).getName());
				attackThree.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						InGameClicks gc = new InGameClicks(this, 3);
							
						if (inGameListener != null) {
							inGameListener.formEventOccurred(gc);
						}	
							
					}
						
				});
				attackThree.addMouseListener(new MouseAdapter() {
					
					 
					 public void mouseEntered(MouseEvent me) {
						
						 InGameTooltip igt = new InGameTooltip(this, 3);
						 
						 if (inGameTooltipListener != null) {
							 inGameTooltipListener.formEventOccurred(igt);
							}	
					 }
					 public void mouseExited(MouseEvent me) {
						 
						 InGameTooltip igt = new InGameTooltip(this, 5);
						 
						 if (inGameTooltipListener != null) {
							 inGameTooltipListener.formEventOccurred(igt);
							}	
					 
					 }		
				});
				attackThree.setBounds(10, 60, 160, 20);
				attackThree.setFont(inGameFont.getFont());
				attackThree.setHorizontalAlignment(SwingConstants.LEFT);
				add(attackThree);
			}
			if (p1.getFirstActiveTeam().getMoves(3) != null) {
				attackFour = new JButton(p1.getFirstActiveTeam().getMoves(3).getName());
				attackFour.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						InGameClicks gc = new InGameClicks(this, 4);
							
						if (inGameListener != null) {
							inGameListener.formEventOccurred(gc);
						}	
							
					}
						
				});
				attackFour.addMouseListener(new MouseAdapter() {
					
					 
					 public void mouseEntered(MouseEvent me) {
						
						 InGameTooltip igt = new InGameTooltip(this, 4);
						 
						 if (inGameTooltipListener != null) {
							 inGameTooltipListener.formEventOccurred(igt);
							}	
					 }
					 public void mouseExited(MouseEvent me) {
						 
						 InGameTooltip igt = new InGameTooltip(this, 5);
						 
						 if (inGameTooltipListener != null) {
							 inGameTooltipListener.formEventOccurred(igt);
							}	
					 
					 }		
				});
				attackFour.setBounds(10, 85, 160, 20);
				attackFour.setFont(inGameFont.getFont());
				attackFour.setHorizontalAlignment(SwingConstants.LEFT);
				add(attackFour);
			}
			
			back = new JButton("R");
			back.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					InGameClicks gc = new InGameClicks(this, 5);
						
					if (inGameListener != null) {
						inGameListener.formEventOccurred(gc);
					}	
						
				}
					
			});
			back.setBounds(180, 65, 40, 40);
			back.setFont(inGameFont.getFont());	
			add(back);
	}
	
	public void setInGameListener(InGameListener listener) {
		this.inGameListener = listener;
	}
	public void setInGameTooltipListener(InGameTooltipListener listener) {
		this.inGameTooltipListener = listener;
	}
	
}

class CombatVsWild {
	private ActionListener listener;
	private Timer delaytimer;
	private int mAttack;
	private int button;
	private boolean turnOver = false;
	private boolean battleOver = false;
	private boolean battleWin = false;
	private boolean hit = false;
	private boolean mHit = false;
	CombatVsWild() {
		
	}
	public void setTurnOver(boolean turnOver) {
		this.turnOver = turnOver;
	}
	public void setButton(int x) {
		this.button = x;
	}
	public Timer getTimer() {
		return this.delaytimer;
	}
	public boolean isBattleOver() {
		return this.battleOver;
	}
	public void setBattleOver(boolean battleOver) {
		this.battleOver = battleOver;
	}
	public boolean isTurnOver() {
		return this.turnOver;
	}
	public boolean isBattleWon() {
		return this.battleWin;
	}
	public void playerAndAI(Player p1, Monsters m1, TextUI textUI, BattleElements battlePanel) {
		boolean first = attackTurn(p1,m1);
		hit = p1.getFirstActiveTeam().phit(p1, button);
		if (first == true) {
			textUI.setNewText(p1.getFirstActiveTeam().getName() + "\n\nUsed " + p1.getFirstActiveTeam().getMoves(button).getName(), 0, false);
			
			listener = new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {            	
	                if (!(textUI.getTimer().isRunning())) {
	                	delaytimer.stop();
	                	if (hit == true) {
	                		if (p1.getFirstActiveTeam().getMoves(button).getCategory().equalsIgnoreCase("Damage")) {
	                			p1.getFirstActiveTeam().getMoves(button).damageEvent(battlePanel, m1, p1, button);
	                		}
	                		else if (p1.getFirstActiveTeam().getMoves(button).getCategory().equalsIgnoreCase("Status")) {
	                			p1.getFirstActiveTeam().getMoves(button).statusEvent(battlePanel);
	                		}
	                		
	                	}
	                	listener = new ActionListener() {
	        	            @Override
	        	            public void actionPerformed(ActionEvent e) {
	        	            	// It crashes incase the players attack misses.
	        	                if (!(p1.getFirstActiveTeam().getMovesTimer(button).isRunning())) {
	        	                	if (hit == true) {
	        	                		if (p1.getFirstActiveTeam().getMoves(button).getCategory().equalsIgnoreCase("Damage")) {
	        	                			//battlePanel.updateHealthNPC(m1, (int)m1.pDamage(p1, m1, button));
	        	                			//delaytimer.stop();
	        	                			if (p1.getFirstActiveTeam().effectiveness(m1.getType(), p1.getFirstActiveTeam().getMoves(button).getType()).equalsIgnoreCase("super")) {
	        	        						textUI.revalidateText();
	        	        						textUI.setNewText("It's super\n\neffective!", 0, false);
	        	        						delaytimer.stop();
	        	                			}
	        	                			else if (p1.getFirstActiveTeam().effectiveness(m1.getType(), p1.getFirstActiveTeam().getMoves(button).getType()).equalsIgnoreCase("weak")) {
	        	        						textUI.revalidateText();
	        	        						textUI.setNewText("It's not very\n\neffective...", 0, false);
	        	        						delaytimer.stop();
	        	                			}
	        	                			else if (p1.getFirstActiveTeam().effectiveness(m1.getType(), p1.getFirstActiveTeam().getMoves(button).getType()).equalsIgnoreCase("normal")) {
	        	                				delaytimer.stop();
	        	                			}
	        	                			// add some more delay between the super effective text and enemies next move.
	        	                		} // if move == physical
	        	                		else if (p1.getFirstActiveTeam().getMoves(button).getCategory().equalsIgnoreCase("Status")) {
	        	                			textUI.revalidateText();
											if (p1.getFirstActiveTeam().getMoves(button).getCategoryNum() == 1) {
												if (m1.setAttackStage(p1.getFirstActiveTeam().getMoves(button).getPower()) == true) {
													textUI.setNewText(m1.getName() + "'s\n\nATTACK fell!", 0, false);
													delaytimer.stop();
												
												}
												
												else {
													textUI.setNewText("It failed...", 0, false);
													delaytimer.stop();
												}
												
											} // if its attack category
											else if (p1.getFirstActiveTeam().getMoves(button).getCategoryNum() == 2) {
												textUI.setNewText(m1.getName() + "'s\n\nSPEED fell!", 0, false);
												delaytimer.stop();
											} // if its speed category
											else if (p1.getFirstActiveTeam().getMoves(button).getCategoryNum() == 3) {
												textUI.setNewText(m1.getName() + "'s\n\nDEFENSE fell!", 0, false);
												delaytimer.stop();
											} // if its defense category
	        	                		} // if move == status
	        	                		
	        	                	} // if hit == true
	        	                	else {
	        	                		textUI.revalidateText();
	        	                		textUI.setNewText("It missed...", 0, false);
	        	                		delaytimer.stop();
	        	                	}
	        	                	listener = new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											if (!(battlePanel.getTimer().isRunning()) && !(textUI.getTimer().isRunning())) {
												textUI.revalidateText();
												if (m1.getHP() > 0) {
													delaytimer.stop();
													textUI.setNewText(m1.getName() + "\n\nUsed " + m1.getMoves(mAttack).getName(), 0, false);
													if (m1.mhit(m1, mAttack) == true) {
														listener = new ActionListener() {

															@Override
															public void actionPerformed(ActionEvent e) {
																if (!(battlePanel.getTimer().isRunning())) {
																	
																	if (m1.getMoves(mAttack).getCategory().equalsIgnoreCase("Damage")) {
																		//battlePanel.damageEventPlayer(); TEST HERE !
																		m1.getMoves(mAttack).damageEventPlayer(battlePanel, m1, p1, mAttack);
																		delaytimer.stop();
																		listener = new ActionListener() {

																			@Override
																			public void actionPerformed(ActionEvent e) {
																				if (!(battlePanel.getTimer().isRunning())) {
																				//	battlePanel.updateHealthPlayer(p1, m1.mDamage(p1, m1, mAttack ));
																					delaytimer.stop();
																					listener = new ActionListener() {

																						@Override
																						public void actionPerformed(ActionEvent e) {
																							if (!(battlePanel.getTimer().isRunning())) {
																								
																								if (p1.getFirstActiveTeam().getHP() >= 1) {
																									if (m1.effectiveness(p1.getFirstActiveTeam().getType(), m1.getMoves(mAttack).getType()).equalsIgnoreCase("super")) {
																										textUI.revalidateText();
																										textUI.setNewText("It's super\n\neffective!", 0, true);
																										turnOver = true;
																										delaytimer.stop();
																									}
																									else if (m1.effectiveness(p1.getFirstActiveTeam().getType(), m1.getMoves(mAttack).getType()).equalsIgnoreCase("weak")) {
																										textUI.revalidateText();
																										textUI.setNewText("It's not very\n\neffective...", 0, true);
																										turnOver = true;
																										delaytimer.stop();
																									}
																									else if (m1.effectiveness(p1.getFirstActiveTeam().getType(), m1.getMoves(mAttack).getType()).equalsIgnoreCase("normal")) {
																										System.out.println("Turn Over!");
																										textUI.setCont(true);
																										turnOver = true;
																										System.out.println(turnOver);
																										delaytimer.stop();
																									}
																								}
																								else if (p1.getFirstActiveTeam().getHP() <= 0) {
																									delaytimer.stop();
																									p1.getFirstActiveTeam().xpDisable();
																									battlePanel.playerDown();
																									listener = new ActionListener() {

																										@Override
																										public void actionPerformed(ActionEvent e) {
																											if (!(battlePanel.getTimer().isRunning())) {
																												delaytimer.stop();
																												textUI.revalidateText();
																												textUI.setNewText(p1.getFirstActiveTeam().getName() + "\n\nfainted!", 0, false);
																												// Works!
																												listener = new ActionListener() {

																													@Override
																													public void actionPerformed(ActionEvent e) {
																														if (!(textUI.getTimer().isRunning())) {
																															delaytimer.stop();
																															if (p1.isTeamAlive() == true) {
																																System.out.println("ALIVE");
																																//Add teamSwap interface!
																															}
																															else if (p1.isTeamAlive() == false) {
																																System.out.println("LOST");
																																battleOver = true;
																																battleWin = false;
																																textUI.revalidateText();
																																textUI.setNewText(p1.getName() + " is out of\n\nusable POKEMON!", 0, true);
																															}
																														}
																															
																														
																													}
																										    		
																										    	};
																												delaytimer = new Timer(2000,listener);
																												delaytimer.start();
																												
																											}
																												/* THIS IS NOT DONE! 
																												 * NEW MENU FOR TEAMSWAP NEEDS TO BE ADDED
																												 * */
																											
																										}
																							    		
																							    	};
																									delaytimer = new Timer(1000,listener);
																									delaytimer.start();
																								} // Add interface for teamSwap
																							}
																								
																							
																						}
																			    		
																			    	}; // check if player is alive
																					delaytimer = new Timer(1500,listener);
																					delaytimer.start();

																				}
																					
																				
																			}
																    		
																    	}; // NPC deals damage
																		delaytimer = new Timer(1500,listener);
																		delaytimer.start();
																	} // NPC used a damaging move
																	else if (m1.getMoves(mAttack).getCategory().equalsIgnoreCase("Status")) {
																		m1.getMoves(mAttack).statusEventPlayer(battlePanel);
																		delaytimer.stop();
																		//m1.mStatus(m1, p1, mAttack);
																		listener = new ActionListener() {
																			@Override
																			public void actionPerformed(ActionEvent e) {
																				if (!(m1.getMovesTimer(mAttack).isRunning())) {
																					textUI.revalidateText();
																					if (m1.getMoves(mAttack).getCategoryNum() == 1) {
																						turnOver = true;
																						if (p1.getFirstActiveTeam().setAttackStage(m1.getMoves(mAttack).getPower()) == true)
																							textUI.setNewText(p1.getFirstActiveTeam().getName() + "'s\n\nATTACK fell!", 0, true);
																						else
																							textUI.setNewText("It failed...", 0, true);
																								
																						delaytimer.stop();
																					}
																					if (m1.getMoves(mAttack).getCategoryNum() == 2) {
																						turnOver = true;
																						textUI.setNewText(p1.getFirstActiveTeam().getName() + "'s\n\nDEFENSE fell!", 0, true);
																						delaytimer.stop();
																					}
																					if (m1.getMoves(mAttack).getCategoryNum() == 3) {
																						turnOver = true;
																						textUI.setNewText(p1.getFirstActiveTeam().getName() + "'s\n\nSPEED fell!", 0, true);
																						delaytimer.stop();
																					}
																				}
																					
																				
																			}
																    		
																    	};
																		delaytimer = new Timer(1500,listener);
																		delaytimer.start();
																		
																	} // NPC used a status move
																}
																	
																
															}
												    		
														}; // NPC turn to attack
														delaytimer = new Timer(1500,listener);
														delaytimer.start();
													}
													else {
														listener = new ActionListener() {
															
															@Override
															public void actionPerformed(ActionEvent e) {
																if (!(battlePanel.getTimer().isRunning())) {
																	textUI.revalidateText();
																	turnOver = true;
																	textUI.setNewText("It missed...", 0, true);
																	delaytimer.stop();
																}
																	
																
															}
												    		
												    	};
														delaytimer = new Timer(1500,listener);
														delaytimer.start();
													}
													
												
												}
												else if (m1.getHP() <= 0) {
													delaytimer.stop();
													battlePanel.npcDown();
													listener = new ActionListener() {

														@Override
														public void actionPerformed(ActionEvent e) {
															if (!(battlePanel.getTimer().isRunning())) {
																delaytimer.stop();
																battleOver = true;
																textUI.revalidateText();
																textUI.setNewText("Enemy " + m1.getName() + "\n\nfainted!", 0, true);
															}
																
															
														}
											    		
											    	};
													delaytimer = new Timer(1000,listener);
													delaytimer.start();
													} // if NPC died
											}
					
										}
	        	                		
	        	                	}; // check if NPC is alive
	        	        			delaytimer = new Timer(2000,listener);
	        	        			delaytimer.start();
	        	                }

	        	            }
	        	        }; // Player deals damage
	        			delaytimer = new Timer(500,listener);
	        			delaytimer.start();
	                }

	            }
	        }; // if player goes first
			delaytimer = new Timer(10,listener);
			delaytimer.start();
			
		}
		else if (first == false) {
			textUI.setNewText(m1.getName() + "\n\nUsed " + m1.getMoves(mAttack).getName(), 0, false);
			hit = p1.getFirstActiveTeam().phit(p1, button);
			listener = new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {            	
	                if (!(textUI.getTimer().isRunning())) {
	                	delaytimer.stop();
	                	if (m1.mhit(m1, mAttack) == true) {
	                		mHit = true;
	                		if (m1.getMoves(mAttack).getCategory().equalsIgnoreCase("Damage")) {
	                			m1.getMoves(mAttack).damageEventPlayer(battlePanel, m1, p1, mAttack);
	                		}
	                		else if (m1.getMoves(mAttack).getCategory().equalsIgnoreCase("Status")) {
	                			m1.getMoves(mAttack).statusEventPlayer(battlePanel);
	                		}
	                		
	                	} // done
	                	else {
	                		textUI.revalidateText();
	                		textUI.setNewText("It missed...", 0, false);
	                		delaytimer.stop();
	                	}
	                	listener = new ActionListener() {
	        	            @Override
	        	            public void actionPerformed(ActionEvent e) {
	        	         
	        	                if (!(m1.getMovesTimer(mAttack).isRunning())) { // DOES NOT WORK!
	        	                	System.out.println(mAttack);
	        	                	if (mHit == true) {
	        	                		if (m1.getMoves(mAttack).getCategory().equalsIgnoreCase("Damage")) {
	        	                			//battlePanel.updateHealthPlayer(p1, m1.mDamage(p1, m1, mAttack ));
	        	                			//delaytimer.stop();
	        	                			if (m1.effectiveness(p1.getFirstActiveTeam().getType(), m1.getMoves(mAttack).getType()).equalsIgnoreCase("super")) {
	        	        						textUI.revalidateText();
	        	        						textUI.setNewText("It's super\n\neffective!", 0, false);
	        	        						delaytimer.stop();
	        	                			}
	        	                			else if (m1.effectiveness(p1.getFirstActiveTeam().getType(), m1.getMoves(mAttack).getType()).equalsIgnoreCase("weak")) {
	        	        						textUI.revalidateText();
	        	        						textUI.setNewText("It's not very\n\neffective...", 0, false);
	        	        						delaytimer.stop();
	        	                			}
	        	                			else if (m1.effectiveness(p1.getFirstActiveTeam().getType(), m1.getMoves(mAttack).getType()).equalsIgnoreCase("normal")) {
	        	                				delaytimer.stop();
	        	                			}
	        	                			// add some more delay between the super effective text and enemies next move.
	        	                		} // if move == physical
	        	                		else if (m1.getMoves(mAttack).getCategory().equalsIgnoreCase("Status")) {
	        	                			textUI.revalidateText();
											if (m1.getMoves(mAttack).getCategoryNum() == 1) {
												if (p1.getFirstActiveTeam().setAttackStage(m1.getMoves(mAttack).getPower()) == true) {
													textUI.setNewText(p1.getFirstActiveTeam().getName()  + "'s\n\nATTACK fell!", 0, false);
													delaytimer.stop();
												
												}
												
												else {
													textUI.setNewText("It failed...", 0, false);
													delaytimer.stop();
												}
												
											} // if its attack category
											else if (m1.getMoves(mAttack).getCategoryNum() == 2) {
												textUI.setNewText(p1.getFirstActiveTeam().getName()  + "'s\n\nSPEED fell!", 0, false);
												delaytimer.stop();
											} // if its speed category
											else if (m1.getMoves(mAttack).getCategoryNum() == 3) {
												textUI.setNewText(p1.getFirstActiveTeam().getName() + "'s\n\nDEFENSE fell!", 0, false);
												delaytimer.stop();
											} // if its defense category
	        	                		} // if move == status
	        	                		
	        	                	} // if hit == true
	        	                	// Done
	        	                	listener = new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											if (!(battlePanel.getTimer().isRunning()) && !(textUI.getTimer().isRunning())) {
												textUI.revalidateText();
												if (p1.getFirstActiveTeam().getHP() > 0) {
													delaytimer.stop();
													textUI.setNewText(p1.getFirstActiveTeam().getName() + "\n\nUsed " + p1.getFirstActiveTeam().getMoves(button).getName(), 0, false);
													if (hit == true) {
														listener = new ActionListener() {

															@Override
															public void actionPerformed(ActionEvent e) {
																if (!(battlePanel.getTimer().isRunning())) {
																	
																	if (p1.getFirstActiveTeam().getMoves(button).getCategory().equalsIgnoreCase("Damage")) {
																		p1.getFirstActiveTeam().getMoves(button).damageEvent(battlePanel, m1, p1, button);
																		delaytimer.stop();
																		listener = new ActionListener() {

																			@Override
																			public void actionPerformed(ActionEvent e) {
																				if (!(battlePanel.getTimer().isRunning())) {
																					//battlePanel.updateHealthNPC(m1, (int)m1.pDamage(p1, m1, button));
																					delaytimer.stop();
																					listener = new ActionListener() {

																						@Override
																						public void actionPerformed(ActionEvent e) {
																							if (!(p1.getFirstActiveTeam().getMovesTimer(button).isRunning())) {
																								
																								if (m1.getHP() >= 1) {
																									if (p1.getFirstActiveTeam().effectiveness(m1.getType(), p1.getFirstActiveTeam().getMoves(button).getType()).equalsIgnoreCase("super")) {
																										textUI.revalidateText();
																										textUI.setNewText("It's super\n\neffective!", 0, true);
																										turnOver = true;
																										delaytimer.stop();
																									}
																									else if (p1.getFirstActiveTeam().effectiveness(m1.getType(), p1.getFirstActiveTeam().getMoves(button).getType()).equalsIgnoreCase("weak")) {
																										textUI.revalidateText();
																										textUI.setNewText("It's not very\n\neffective...", 0, true);
																										turnOver = true;
																										delaytimer.stop();
																									}
																									else if (p1.getFirstActiveTeam().effectiveness(m1.getType(), p1.getFirstActiveTeam().getMoves(button).getType()).equalsIgnoreCase("normal")) {
																										System.out.println("Turn Over!");
																										textUI.setCont(true);
																										turnOver = true;
																										System.out.println(turnOver);
																										delaytimer.stop();
																									}
																								}
																								else if (m1.getHP() <= 0) {
																									battlePanel.npcDown();
																									delaytimer.stop();
																									listener = new ActionListener() {

																										@Override
																										public void actionPerformed(ActionEvent e) {
																											if (!(battlePanel.getTimer().isRunning())) {
																												delaytimer.stop();
																												battleOver = true;
																												battleWin = true;
																												textUI.revalidateText();
																												textUI.setNewText("Enemy " + m1.getName() + "\n\nfainted!", 0, true);
																											}
																												
																											
																										}
																							    		
																							    	};
																									delaytimer = new Timer(1000,listener);
																									delaytimer.start();
																								} // Add interface for teamSwap
																							}
																								
																							
																						}
																			    		
																			    	}; // check if NPC is alive
																					delaytimer = new Timer(1500,listener);
																					delaytimer.start();

																				}
																					
																				
																			}
																    		
																    	}; // Player deals damage
																		delaytimer = new Timer(1500,listener);
																		delaytimer.start();
																	} // Player used a damaging move
																	else if (p1.getFirstActiveTeam().getMoves(button).getCategory().equalsIgnoreCase("Status")) {
																		p1.getFirstActiveTeam().getMoves(button).statusEvent(battlePanel);
																		delaytimer.stop();
																		//m1.mStatus(m1, p1, mAttack);
																		listener = new ActionListener() {
																			@Override
																			public void actionPerformed(ActionEvent e) {
																				if (!(battlePanel.getTimer().isRunning())) {
																					textUI.revalidateText();
																					if (p1.getFirstActiveTeam().getMoves(button).getCategoryNum() == 1) {
																						turnOver = true;
																						if (m1.setAttackStage(p1.getFirstActiveTeam().getMoves(button).getPower()) == true) 
																							textUI.setNewText(m1.getName() + "'s\n\nATTACK fell!", 0, true);
																						else
																							textUI.setNewText("It failed...", 0, true);
																								
																						delaytimer.stop();
																					}
																					if (p1.getFirstActiveTeam().getMoves(button).getCategoryNum() == 2) {
																						turnOver = true;
																						textUI.setNewText(m1.getName() + "'s\n\nDEFENSE fell!", 0, true);
																						delaytimer.stop();
																					}
																					if (p1.getFirstActiveTeam().getMoves(button).getCategoryNum() == 3) {
																						turnOver = true;
																						textUI.setNewText(m1.getName() + "'s\n\nSPEED fell!", 0, true);
																						delaytimer.stop();
																					}
																				}
																					
																				
																			}
																    		
																    	};
																		delaytimer = new Timer(1500,listener);
																		delaytimer.start();
																		
																	} // NPC used a status move
																}
																	
																
															}
												    		
														}; // NPC turn to attack
														delaytimer = new Timer(1500,listener);
														delaytimer.start();
													}
													else {
														listener = new ActionListener() {
															
															@Override
															public void actionPerformed(ActionEvent e) {
																if (!(battlePanel.getTimer().isRunning()) && !(textUI.getTimer().isRunning())) {
																	textUI.revalidateText();
																	turnOver = true;
																	textUI.setNewText("It missed...", 0, true);
																	delaytimer.stop();
																}
																	
																
															}
												    		
												    	};
														delaytimer = new Timer(1500,listener);
														delaytimer.start();
													}
													
												
												}
												else if (p1.getFirstActiveTeam().getHP() <= 0) {
													delaytimer.stop();
													p1.getFirstActiveTeam().xpDisable();
													battlePanel.playerDown();
													listener = new ActionListener() {

														@Override
														public void actionPerformed(ActionEvent e) {
															if (!(battlePanel.getTimer().isRunning())) {
																delaytimer.stop();
																textUI.revalidateText();
																textUI.setNewText(p1.getFirstActiveTeam().getName() + "\n\nfainted!", 0, false);
																// Works!
																listener = new ActionListener() {

																	@Override
																	public void actionPerformed(ActionEvent e) {
																		if (!(textUI.getTimer().isRunning())) {
																			delaytimer.stop();
																			if (p1.isTeamAlive() == true) {
																				System.out.println("ALIVE");
																				//Add teamSwap interface!
																			}
																			else if (p1.isTeamAlive() == false) {
																				System.out.println("LOST");
																				battleOver = true;
																				battleWin = false;
																				textUI.revalidateText();
																				textUI.setNewText(p1.getName() + " is out of\n\nusable POKEMON!", 0, true);
																			}
																		}
																			
																		
																	}
														    		
														    	};
																delaytimer = new Timer(2000,listener);
																delaytimer.start();
																
															}
																/* THIS IS NOT DONE! 
																 * NEW MENU FOR TEAMSWAP NEEDS TO BE ADDED
																 * */
															
														}
											    		
											    	};
													delaytimer = new Timer(1000,listener);
													delaytimer.start();
													} // if Player died 
											}
					
										}
	        	                		
	        	                	}; // check if player is alive
	        	        			delaytimer = new Timer(1250,listener);
	        	        			delaytimer.start();
	        	                }

	        	            }
	        	        }; // NPC deals damage
	        			delaytimer = new Timer(500,listener);
	        			delaytimer.start();
	                }

	            }
	        }; // if NPC goes first
			delaytimer = new Timer(10,listener);
			delaytimer.start();
		}
		
	}
	public void onlyAI(Player p1, Monsters m1, TextUI textUI, BattleElements battlePanel) {
		textUI.setCont(false);
		textUI.revalidateText();
		mAttack = m1.attackGenerator(m1);
		textUI.setNewText(m1.getName() + "\n\nUsed " + m1.getMoves(mAttack).getName(), 0, false);
  
		listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {            	
                if (!(textUI.getTimer().isRunning())) {
                	delaytimer.stop();
                	if (m1.mhit(m1, mAttack) == true) {
                		mHit = true;
                		if (m1.getMoves(mAttack).getCategory().equalsIgnoreCase("Damage")) {
                			m1.getMoves(mAttack).damageEventPlayer(battlePanel, m1, p1, mAttack);
                		}
                		else if (m1.getMoves(mAttack).getCategory().equalsIgnoreCase("Status")) {
                			m1.getMoves(mAttack).statusEventPlayer(battlePanel);
                		}
                		
                	} // done
                	else {
                		textUI.revalidateText();
                		textUI.setNewText("It missed...", 0, false);
                		delaytimer.stop();
                	}
                	listener = new ActionListener() {
        	            @Override
        	            public void actionPerformed(ActionEvent e) {
        	         
        	                if (!(m1.getMovesTimer(mAttack).isRunning())) { 
        	                	if (mHit == true) {
        	                		if (m1.getMoves(mAttack).getCategory().equalsIgnoreCase("Damage")) {

        	                			if (m1.effectiveness(p1.getFirstActiveTeam().getType(), m1.getMoves(mAttack).getType()).equalsIgnoreCase("super")) {
        	        						textUI.revalidateText();
        	        						textUI.setNewText("It's super\n\neffective!", 0, false);
        	        						delaytimer.stop();
        	                			}
        	                			else if (m1.effectiveness(p1.getFirstActiveTeam().getType(), m1.getMoves(mAttack).getType()).equalsIgnoreCase("weak")) {
        	        						textUI.revalidateText();
        	        						textUI.setNewText("It's not very\n\neffective...", 0, false);
        	        						delaytimer.stop();
        	                			}
        	                			else if (m1.effectiveness(p1.getFirstActiveTeam().getType(), m1.getMoves(mAttack).getType()).equalsIgnoreCase("normal")) {
        	                				delaytimer.stop();
        	                			}
        	                			// add some more delay between the super effective text and enemies next move.
        	                		} // if move == physical
        	                		else if (m1.getMoves(mAttack).getCategory().equalsIgnoreCase("Status")) {
        	                			textUI.revalidateText();
										if (m1.getMoves(mAttack).getCategoryNum() == 1) {
											if (p1.getFirstActiveTeam().setAttackStage(m1.getMoves(mAttack).getPower()) == true) {
												textUI.setNewText(p1.getFirstActiveTeam().getName()  + "'s\n\nATTACK fell!", 0, false);
												delaytimer.stop();
											
											}
											
											else {
												textUI.setNewText("It failed...", 0, false);
												delaytimer.stop();
											}
											
										} // if its attack category
										else if (m1.getMoves(mAttack).getCategoryNum() == 2) {
											textUI.setNewText(p1.getFirstActiveTeam().getName()  + "'s\n\nSPEED fell!", 0, false);
											delaytimer.stop();
										} // if its speed category
										else if (m1.getMoves(mAttack).getCategoryNum() == 3) {
											textUI.setNewText(p1.getFirstActiveTeam().getName() + "'s\n\nDEFENSE fell!", 0, false);
											delaytimer.stop();
										} // if its defense category
        	                		} // if move == status
        	                		
        	                	} // if hit == true
        	                	// Done
        	                	listener = new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										if (!(battlePanel.getTimer().isRunning()) && !(textUI.getTimer().isRunning())) {
											if (p1.getFirstActiveTeam().getHP() <= 0) {
												delaytimer.stop();
												p1.getFirstActiveTeam().xpDisable();
												battlePanel.playerDown();
												listener = new ActionListener() {

													@Override
													public void actionPerformed(ActionEvent e) {
														if (!(battlePanel.getTimer().isRunning())) {
															delaytimer.stop();
															textUI.revalidateText();
															textUI.setNewText(p1.getFirstActiveTeam().getName() + "\n\nfainted!", 0, false);
															// Works!
															listener = new ActionListener() {

																@Override
																public void actionPerformed(ActionEvent e) {
																	if (!(textUI.getTimer().isRunning())) {
																		delaytimer.stop();
																		if (p1.isTeamAlive() == true) {
																			System.out.println("ALIVE");
																			//Add teamSwap interface!
																		}
																		else if (p1.isTeamAlive() == false) {
																			System.out.println("LOST");
																			battleOver = true;
																			battleWin = false;
																			textUI.revalidateText();
																			textUI.setNewText(p1.getName() + " is out of\n\nusable POKEMON!", 0, true);
																		}
																	}
																		
																	
																}
													    		
													    	};
															delaytimer = new Timer(2000,listener);
															delaytimer.start();
															
														}
															/* THIS IS NOT DONE! 
															 * NEW MENU FOR TEAMSWAP NEEDS TO BE ADDED
															 * */
														
													}
										    		
										    	};
												delaytimer = new Timer(1000,listener);
												delaytimer.start();
												} // if Player died 
											else if (p1.getFirstActiveTeam().getHP() >= 1) {
												delaytimer.stop();
												textUI.setCont(true);
												turnOver = true;
												System.out.println(turnOver);
											}
										}
				
									}
        	                		
        	                	}; // check if player is alive
        	        			delaytimer = new Timer(1250,listener);
        	        			delaytimer.start();
        	                }

        	            }
        	        }; // NPC deals damage
        			delaytimer = new Timer(500,listener);
        			delaytimer.start();
                }

            }
        }; // if NPC goes first
		delaytimer = new Timer(10,listener);
		delaytimer.start();
	
	}
	private boolean attackTurn(Player p1, Monsters m1) {
		int x;
		mAttack = m1.attackGenerator(m1);
		int p = p1.getFirstActiveTeam().getMoves(button).getPriority();
		int m = m1.getMoves(mAttack).getPriority();
		
		if (p > m) {
			return true;
		}
		if (p < m) {
			return false;
		}
		if (p == m) {
			if (p1.getFirstActiveTeam().getSpeed() > m1.getSpeed()) {
				return true;
			}
			if (p1.getFirstActiveTeam().getSpeed() == m1.getSpeed()) {
				x = (int)(Math.random() * ((1 - 1) + 4));
				if (x > 1) 
					return true;
				else 
					return false;
			}
			else 
				return false;
		}
		return false;
	}
	
	
	public boolean escape(Player p1, Monsters m1) {
		int y = 20;
		int x = (int)(Math.random() * ((100 - 0) + 1)) + 0;
		if (p1.getFirstActiveTeam().getLevel() > m1.getLevel()) {
			y += 20;
			y += (p1.getFirstActiveTeam().getLevel() - m1.getLevel()) * 2;
			
		} // Har spelaren en högre level får den en bonus
		if (y > x)
			return true;
		else if (y <= x)
			return false;
		
		return false;
	}
	
	public void disableXP(Player p1) {
		int x = 6;
		for (int i = 1; i <= 5; i++) {
			if (p1.getActiveTeam(i) == null) {
				x--;
				}
			} // kollar hur många spelaren har i sitt lag.
		x--;
	
		for (;x >= 0; x--) {
			if (p1.getActiveTeam(x).getHP() >= 1) {
				p1.getActiveTeam(x).xpDisable();
			}	
		} // går igenom alla som finns i laget och stänger av exp.
	} 
}


/* 
 *  Rewrite the battlemethods completely, they are hard to read and quite messy, the game crashes in case one of the pokemons miss their 
 *  attacks.
 *
 * 
 * 
 */
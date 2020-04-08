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
import java.awt.geom.Line2D;
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
import pokemon.Monsters;

public class BattleElements extends JPanel {
	
	private InGameListener inGameListener;
	
	private HealthBar playerHealthBar;
	private HealthBar enemyHealthBar;
	private Lines lines;
	private Circle circle;
	
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
	
	public boolean isMenu() {
		return menu;
	}
	public void setMenu(boolean menu) {
		this.menu = menu;
	}
	public Timer getTimer() {
		return this.timer;
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
				System.out.println(y);
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
				System.out.println(y);
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
		playerSprite.setIcon(loadPlayerBackSprite()); //loadBackSprite(p1)
		//playerSprite.setBounds(50, 190, 170, 140);
		battleLpane.add(playerSprite, JLayeredPane.DEFAULT_LAYER);
		
		enemySprite = new JLabel();
		enemySprite.setIcon(loadFrontSprite(m1));
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
	private ImageIcon loadPlayerBackSprite() {
		String userDirectory = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Version 1.0 Folder" + File.separator + "Game assets" + File.separator + "Sprites" + File.separator + "characters";
		File custom = new File(userDirectory);
		String absoluteP = custom.getAbsolutePath();
		String imgFile = absoluteP +  File.separator + "Player_Back.png";
		ImageIcon icon = new ImageIcon(imgFile);
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(180, 180,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		icon = new ImageIcon(newimg);
	
        return icon;
	}
	private ImageIcon loadFrontSprite(Monsters m1) {
		String userDirectory = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Version 1.0 Folder" + File.separator + "Game assets" + File.separator + "Sprites";
		File custom = new File(userDirectory);
		String absoluteP = custom.getAbsolutePath();
		String imgFile = absoluteP +  File.separator + m1.getID();
		ImageIcon icon = new ImageIcon(imgFile);
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		icon = new ImageIcon(newimg);
		
        return icon;
	}
	private void updateHealthNPC(Monsters m1,int x) {
		hp = x;
		System.out.println("x" + x);
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
		System.out.println("x" + x);
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
				System.out.println(charIndex);
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
class BattleTeamMenu extends JPanel{
	
	private InGameListener inGameListener;
	private Font gameFont;
	private Color transparent;
	private JLabel p1s;
	private InGameFont inGameFont;
	private HealthBar p1Bar;
	
	private JTextField p1n;
	private JTextField hpt;
	private JTextField hp1;
	private JTextField lvl1;
	
	private JButton stats;
	private JButton swap;
	private JButton back;
	BattleTeamMenu(Player p1) {
		setLayout(null);
		setBackground(Color.white);
		transparent = new Color( 0, 0, 0, 0);
		
		p1s = new JLabel(loadMenuSprite(p1.getActiveTeam(0).getMenuID()));
		p1s.setBounds(10, 2, 45, 45);
		add(p1s); // Menu sprite
		inGameFont = new InGameFont(gameFont, 1);
		hpt = new JTextField("HP:");
		hpt.setBounds(80, 31, 40, 10);
		hpt.setBorder(BorderFactory.createEmptyBorder());
		hpt.setBackground(transparent);
		hpt.setFont(inGameFont.getFont());
		hpt.setEditable(false);
		add(hpt); // HP
		p1Bar = new HealthBar(p1.getActiveTeam(0).getHP(), p1.getActiveTeam(0).getMaxHP());
		p1Bar.setBounds(120, 32, 190, 75);
		add(p1Bar); // HP bar
		inGameFont = new InGameFont(gameFont, 0);
		p1n = new JTextField(p1.getActiveTeam(0).getName());
		p1n.setBorder(BorderFactory.createEmptyBorder());
		p1n.setEditable(false);
		p1n.setFont(inGameFont.getFont());
		p1n.setBounds(70, 2, 200, 30);
		add(p1n); // Name
		inGameFont = new InGameFont(gameFont, 1);
		hp1 = new JTextField(p1.getActiveTeam(0).getHP() + " / " + p1.getActiveTeam(0).getMaxHP());
		hp1.setBounds(300, 31, 108, 10);
		hp1.setBorder(BorderFactory.createEmptyBorder());
		hp1.setBackground(transparent);
		hp1.setFont(inGameFont.getFont());
		hp1.setEditable(false);
		add(hp1); // HP / MAXHP
		inGameFont = new InGameFont(gameFont, 0);
		lvl1 = new JTextField(":L " + p1.getActiveTeam(0).getLevel());
		lvl1.setBounds(285, 5, 93, 20);
		lvl1.setBorder(BorderFactory.createEmptyBorder());
		lvl1.setBackground(transparent);
		lvl1.setFont(inGameFont.getFont());
		lvl1.setEditable(false);
		add(lvl1); // Level
		inGameFont = new InGameFont(gameFont, 4);
		swap = new JButton("SWAP");
		swap.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				InGameClicks gc = new InGameClicks(this, 0.0);
					
				if (inGameListener != null) {
					inGameListener.formEventOccurred(gc);
				}	
					
			}
				
		});
		swap.setFont(inGameFont.getFont());
		swap.setHorizontalAlignment(SwingConstants.CENTER);
		swap.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		swap.setBounds(407, 6, 45, 40);
		add(swap);
		stats = new JButton("STATS");
		stats.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				InGameClicks gc = new InGameClicks(this, 0.1);
					
				if (inGameListener != null) {
					inGameListener.formEventOccurred(gc);
				}	
					
			}
				
		});
		stats.setFont(inGameFont.getFont());
		stats.setHorizontalAlignment(SwingConstants.CENTER);
		stats.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		stats.setBounds(454, 6, 45, 40);
		add(stats);
		/////// END OF FIRST ROW ///////
		if (p1.getActiveTeam(1) != null) {
			p1s = new JLabel(loadMenuSprite(p1.getActiveTeam(1).getMenuID()));
			p1s.setBounds(10, 57, 45, 45);
			add(p1s); // Menu sprite
			inGameFont = new InGameFont(gameFont, 1);
			hpt = new JTextField("HP:");
			hpt.setBounds(80, 86, 40, 10);
			hpt.setBorder(BorderFactory.createEmptyBorder());
			hpt.setBackground(transparent);
			hpt.setFont(inGameFont.getFont());
			hpt.setEditable(false);
			add(hpt); // HP
			p1Bar = new HealthBar(p1.getActiveTeam(1).getHP(), p1.getActiveTeam(1).getMaxHP());
			p1Bar.setBounds(120, 87, 190, 75);
			add(p1Bar); // Healthbar
			inGameFont = new InGameFont(gameFont, 0);
			p1n = new JTextField(p1.getActiveTeam(1).getName());
			p1n.setBorder(BorderFactory.createEmptyBorder());
			p1n.setEditable(false);
			p1n.setFont(inGameFont.getFont());
			p1n.setBounds(70, 57, 200, 30);
			add(p1n); // Name
			inGameFont = new InGameFont(gameFont, 1);
			hp1 = new JTextField(p1.getActiveTeam(1).getHP() + " / " + p1.getActiveTeam(1).getMaxHP());
			hp1.setBounds(300, 86, 108, 10);
			hp1.setBorder(BorderFactory.createEmptyBorder());
			hp1.setBackground(transparent);
			hp1.setFont(inGameFont.getFont());
			hp1.setEditable(false);
			add(hp1); // HP / MAXHP
			inGameFont = new InGameFont(gameFont, 0);
			lvl1 = new JTextField(":L " + p1.getActiveTeam(1).getLevel());
			lvl1.setBounds(285, 60, 93, 20);
			lvl1.setBorder(BorderFactory.createEmptyBorder());
			lvl1.setBackground(transparent);
			lvl1.setFont(inGameFont.getFont());
			lvl1.setEditable(false);
			add(lvl1); // Level
			inGameFont = new InGameFont(gameFont, 4);
			swap = new JButton("SWAP");
			swap.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					InGameClicks gc = new InGameClicks(this, 1.0);
						
					if (inGameListener != null) {
						inGameListener.formEventOccurred(gc);
					}	
						
				}
					
			});
			swap.setFont(inGameFont.getFont());
			swap.setHorizontalAlignment(SwingConstants.CENTER);
			swap.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			swap.setBounds(407, 61, 45, 40);
			add(swap);
			stats = new JButton("STATS");
			stats.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					InGameClicks gc = new InGameClicks(this, 1.1);
						
					if (inGameListener != null) {
						inGameListener.formEventOccurred(gc);
					}	
						
				}
					
			});
			stats.setFont(inGameFont.getFont());
			stats.setHorizontalAlignment(SwingConstants.CENTER);
			stats.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			stats.setBounds(454, 61, 45, 40);
			add(stats);
		} 		/////// END OF SECOND ROW ///////
		if (p1.getActiveTeam(2) != null) {
			p1s = new JLabel(loadMenuSprite(p1.getActiveTeam(2).getMenuID()));
			p1s.setBounds(10, 112, 45, 45);
			add(p1s); // Menu sprite
			inGameFont = new InGameFont(gameFont, 1);
			hpt = new JTextField("HP:");
			hpt.setBounds(80, 141, 40, 10);
			hpt.setBorder(BorderFactory.createEmptyBorder());
			hpt.setBackground(transparent);
			hpt.setFont(inGameFont.getFont());
			hpt.setEditable(false);
			add(hpt); // HP
			p1Bar = new HealthBar(p1.getActiveTeam(2).getHP(), p1.getActiveTeam(2).getMaxHP());
			p1Bar.setBounds(120, 142, 190, 75);
			add(p1Bar); // HealthBar
			inGameFont = new InGameFont(gameFont, 0);
			p1n = new JTextField(p1.getActiveTeam(2).getName());
			p1n.setBorder(BorderFactory.createEmptyBorder());
			p1n.setEditable(false);
			p1n.setFont(inGameFont.getFont());
			p1n.setBounds(70, 112, 200, 30);
			add(p1n); // Name
			inGameFont = new InGameFont(gameFont, 1);
			hp1 = new JTextField(p1.getActiveTeam(2).getHP() + " / " + p1.getActiveTeam(2).getMaxHP());
			hp1.setBounds(300, 141, 108, 10);
			hp1.setBorder(BorderFactory.createEmptyBorder());
			hp1.setBackground(transparent);
			hp1.setFont(inGameFont.getFont());
			hp1.setEditable(false);
			add(hp1); // HP / MAXHP
			inGameFont = new InGameFont(gameFont, 0);
			lvl1 = new JTextField(":L " + p1.getActiveTeam(2).getLevel());
			lvl1.setBounds(285, 115, 93, 20);
			lvl1.setBorder(BorderFactory.createEmptyBorder());
			lvl1.setBackground(transparent);
			lvl1.setFont(inGameFont.getFont());
			lvl1.setEditable(false);
			add(lvl1); // Level
			inGameFont = new InGameFont(gameFont, 4);
			swap = new JButton("SWAP");
			swap.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					InGameClicks gc = new InGameClicks(this, 2.0);
						
					if (inGameListener != null) {
						inGameListener.formEventOccurred(gc);
					}	
						
				}
					
			});
			swap.setFont(inGameFont.getFont());
			swap.setHorizontalAlignment(SwingConstants.CENTER);
			swap.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			swap.setBounds(407, 116, 45, 40);
			add(swap);
			stats = new JButton("STATS");
			stats.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					InGameClicks gc = new InGameClicks(this, 2.1);
						
					if (inGameListener != null) {
						inGameListener.formEventOccurred(gc);
					}	
						
				}
					
			});
			stats.setFont(inGameFont.getFont());
			stats.setHorizontalAlignment(SwingConstants.CENTER);
			stats.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			stats.setBounds(454, 116, 45, 40);
			add(stats);
		} 		/////// END OF THIRD ROW ///////
		if (p1.getActiveTeam(3) != null) {
			p1s = new JLabel(loadMenuSprite(p1.getActiveTeam(3).getMenuID()));
			p1s.setBounds(10, 167, 45, 45);
			add(p1s); // Menu sprite
			inGameFont = new InGameFont(gameFont, 1);
			hpt = new JTextField("HP:");
			hpt.setBounds(80, 196, 40, 10);
			hpt.setBorder(BorderFactory.createEmptyBorder());
			hpt.setBackground(transparent);
			hpt.setFont(inGameFont.getFont());
			hpt.setEditable(false);
			add(hpt); // HP
			p1Bar = new HealthBar(p1.getActiveTeam(3).getHP(), p1.getActiveTeam(3).getMaxHP());
			p1Bar.setBounds(120, 197, 190, 75);
			add(p1Bar);
			inGameFont = new InGameFont(gameFont, 0);
			p1n = new JTextField(p1.getActiveTeam(3).getName());
			p1n.setBorder(BorderFactory.createEmptyBorder());
			p1n.setEditable(false);
			p1n.setFont(inGameFont.getFont());
			p1n.setBounds(70, 167, 200, 30);
			add(p1n); // Name
			inGameFont = new InGameFont(gameFont, 1);
			hp1 = new JTextField(p1.getActiveTeam(3).getHP() + " / " + p1.getActiveTeam(3).getMaxHP());
			hp1.setBounds(300, 196, 108, 10);
			hp1.setBorder(BorderFactory.createEmptyBorder());
			hp1.setBackground(transparent);
			hp1.setFont(inGameFont.getFont());
			hp1.setEditable(false);
			add(hp1); // HP / MAXHP
			inGameFont = new InGameFont(gameFont, 0);
			lvl1 = new JTextField(":L " + p1.getActiveTeam(3).getLevel());
			lvl1.setBounds(285, 170, 93, 20);
			lvl1.setBorder(BorderFactory.createEmptyBorder());
			lvl1.setBackground(transparent);
			lvl1.setFont(inGameFont.getFont());
			lvl1.setEditable(false);
			add(lvl1); // Level
			inGameFont = new InGameFont(gameFont, 4);
			swap = new JButton("SWAP");
			swap.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					InGameClicks gc = new InGameClicks(this, 3.0);
						
					if (inGameListener != null) {
						inGameListener.formEventOccurred(gc);
					}	
						
				}
					
			});
			swap.setFont(inGameFont.getFont());
			swap.setHorizontalAlignment(SwingConstants.CENTER);
			swap.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			swap.setBounds(407, 171, 45, 40);
			add(swap);
			stats = new JButton("STATS");
			stats.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					InGameClicks gc = new InGameClicks(this, 3.1);
						
					if (inGameListener != null) {
						inGameListener.formEventOccurred(gc);
					}	
						
				}
					
			});
			stats.setFont(inGameFont.getFont());
			stats.setHorizontalAlignment(SwingConstants.CENTER);
			stats.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			stats.setBounds(454, 171, 45, 40);
			add(stats);
		} 		/////// END OF FOURTH ROW ///////
		if (p1.getActiveTeam(4) != null) {
			p1s = new JLabel(loadMenuSprite(p1.getActiveTeam(4).getMenuID()));
			p1s.setBounds(10, 222, 45, 45);
			add(p1s); // Menu sprite
			inGameFont = new InGameFont(gameFont, 1);
			hpt = new JTextField("HP:");
			hpt.setBounds(80, 251, 40, 10);
			hpt.setBorder(BorderFactory.createEmptyBorder());
			hpt.setBackground(transparent);
			hpt.setFont(inGameFont.getFont());
			hpt.setEditable(false);
			add(hpt); // HP
			p1Bar = new HealthBar(p1.getActiveTeam(4).getHP(), p1.getActiveTeam(4).getMaxHP());
			p1Bar.setBounds(120, 252, 190, 75);
			add(p1Bar);
			inGameFont = new InGameFont(gameFont, 0);
			p1n = new JTextField(p1.getActiveTeam(4).getName());
			p1n.setBorder(BorderFactory.createEmptyBorder());
			p1n.setEditable(false);
			p1n.setFont(inGameFont.getFont());
			p1n.setBounds(70, 222, 200, 30);
			add(p1n); // Name
			inGameFont = new InGameFont(gameFont, 1);
			hp1 = new JTextField(p1.getActiveTeam(4).getHP() + " / " + p1.getActiveTeam(4).getMaxHP());
			hp1.setBounds(300, 251, 108, 10);
			hp1.setBorder(BorderFactory.createEmptyBorder());
			hp1.setBackground(transparent);
			hp1.setFont(inGameFont.getFont());
			hp1.setEditable(false);
			add(hp1); // HP / MAXHP
			inGameFont = new InGameFont(gameFont, 0);
			lvl1 = new JTextField(":L " + p1.getActiveTeam(4).getLevel());
			lvl1.setBounds(285, 225, 93, 20);
			lvl1.setBorder(BorderFactory.createEmptyBorder());
			lvl1.setBackground(transparent);
			lvl1.setFont(inGameFont.getFont());
			lvl1.setEditable(false);
			add(lvl1); // Level
			inGameFont = new InGameFont(gameFont, 4);
			swap = new JButton("SWAP");
			swap.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					InGameClicks gc = new InGameClicks(this, 4.0);
						
					if (inGameListener != null) {
						inGameListener.formEventOccurred(gc);
					}	
						
				}
					
			});
			swap.setFont(inGameFont.getFont());
			swap.setHorizontalAlignment(SwingConstants.CENTER);
			swap.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			swap.setBounds(407, 226, 45, 40);
			add(swap);
			stats = new JButton("STATS");
			stats.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					InGameClicks gc = new InGameClicks(this, 4.1);
						
					if (inGameListener != null) {
						inGameListener.formEventOccurred(gc);
					}	
						
				}
					
			});
			stats.setFont(inGameFont.getFont());
			stats.setHorizontalAlignment(SwingConstants.CENTER);
			stats.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			stats.setBounds(454, 226, 45, 40);
			add(stats);
		} 		/////// END OF FIFTH ROW ///////
		if (p1.getActiveTeam(5) != null) {
			p1s = new JLabel(loadMenuSprite(p1.getActiveTeam(5).getMenuID()));
			p1s.setBounds(10, 277, 45, 45);
			add(p1s); // Menu sprite
			inGameFont = new InGameFont(gameFont, 1);
			hpt = new JTextField("HP:");
			hpt.setBounds(80, 306, 40, 10);
			hpt.setBorder(BorderFactory.createEmptyBorder());
			hpt.setBackground(transparent);
			hpt.setFont(inGameFont.getFont());
			hpt.setEditable(false);
			add(hpt); // HP
			p1Bar = new HealthBar(p1.getActiveTeam(5).getHP(), p1.getActiveTeam(5).getMaxHP());
			p1Bar.setBounds(120, 307, 190, 75);
			add(p1Bar);
			inGameFont = new InGameFont(gameFont, 0);
			p1n = new JTextField(p1.getActiveTeam(5).getName());
			p1n.setBorder(BorderFactory.createEmptyBorder());
			p1n.setEditable(false);
			p1n.setFont(inGameFont.getFont());
			p1n.setBounds(70, 277, 200, 30);
			add(p1n); // Name
			inGameFont = new InGameFont(gameFont, 1);
			hp1 = new JTextField(p1.getActiveTeam(5).getHP() + " / " + p1.getActiveTeam(5).getMaxHP());
			hp1.setBounds(300, 306, 108, 10);
			hp1.setBorder(BorderFactory.createEmptyBorder());
			hp1.setBackground(transparent);
			hp1.setFont(inGameFont.getFont());
			hp1.setEditable(false);
			add(hp1); // HP / MAXHP
			inGameFont = new InGameFont(gameFont, 0);
			lvl1 = new JTextField(":L " + p1.getActiveTeam(5).getLevel());
			lvl1.setBounds(285, 280, 93, 20);
			lvl1.setBorder(BorderFactory.createEmptyBorder());
			lvl1.setBackground(transparent);
			lvl1.setFont(inGameFont.getFont());
			lvl1.setEditable(false);
			add(lvl1); // Level
			inGameFont = new InGameFont(gameFont, 4);
			swap = new JButton("SWAP");
			swap.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					InGameClicks gc = new InGameClicks(this, 5.0);
						
					if (inGameListener != null) {
						inGameListener.formEventOccurred(gc);
					}	
						
				}
					
			});
			swap.setFont(inGameFont.getFont());
			swap.setHorizontalAlignment(SwingConstants.CENTER);
			swap.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			swap.setBounds(407, 281, 45, 40);
			add(swap);
			stats = new JButton("STATS");
			stats.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					InGameClicks gc = new InGameClicks(this, 5.1);
						
					if (inGameListener != null) {
						inGameListener.formEventOccurred(gc);
					}	
						
				}
					
			});
			stats.setFont(inGameFont.getFont());
			stats.setHorizontalAlignment(SwingConstants.CENTER);
			stats.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			stats.setBounds(454, 281, 45, 40);
			add(stats);
		} 		/////// END OF SIXTH ROW ///////
	}
	public BattleTeamMenu(TextUI textUI) {
		setLayout(null);
		setBackground(Color.white);
		
		textUI.setInstantText("Choose  a  POKEMON.");
		inGameFont = new InGameFont(gameFont, 1);
		
		back = new JButton("Back");
		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				InGameClicks gc = new InGameClicks(this, 0);
					
				if (inGameListener != null) {
					inGameListener.formEventOccurred(gc);
				}	
					
			}
				
		});
		back.setFont(inGameFont.getFont());
		back.setHorizontalAlignment(SwingConstants.CENTER);
		//back.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
		back.setBounds(0, 0, 70, 54);
		add(back);
	}
	private ImageIcon loadMenuSprite(String id) {
		String userDirectory = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Version 1.0 Folder" + File.separator + "Game assets" + File.separator + "Sprites" + File.separator + "menu";
		File custom = new File(userDirectory);
		String absoluteP = custom.getAbsolutePath();
		String imgFile = absoluteP + File.separator + id;
		ImageIcon icon = new ImageIcon(imgFile);
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(45, 45,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		icon = new ImageIcon(newimg);
		System.out.println(id);
        return icon;
	}
	public void setInGameListener(InGameListener listener) {
		this.inGameListener = listener;
	}
}
class StatsWindow extends JPanel{
	
	private InGameListener inGameListener;
	
	private JToggleButton toggleButton;
	private JButton back;
	private JButton p1;
	private JButton p2;
	
	private JLabel sprite;
	private JLabel stats;
	private JLabel healthStatus;
	private JLabel healthExperience;
	private JLabel generalInfo;
	private JLabel fillLabel;
	private JLabel moves;
	
	private JTextField statsField;
	private JTextField dexNumber;
	private JTextField nameField;
	private JTextField statusField;
	private JTextField hp;
	private JTextField level;
	private JTextField exp;
	private JTextField type;
	private JTextField idNo;
	private JTextField move;
	
	private Font gameFont;
	private InGameFont inGameFont;
	private HealthBar healthBar;
	
	private int lvl;
	
	StatsWindow() {
		
		setLayout(null);
		setBackground(Color.white);
		inGameFont = new InGameFont(gameFont, 1);
		
		/*toggleButton = new JToggleButton("Page 2");
		toggleButton.setFont(inGameFont.getFont());
		toggleButton.setBackground(Color.darkGray);
		toggleButton.setBounds(205, 10, 80, 40);
		toggleButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				InGameClicks gc = new InGameClicks(this, true);
					
				if (inGameListener != null) {
					inGameListener.formEventOccurred(gc);
				}	
					
			}
				
		});
		add(toggleButton); */
		p1 = new JButton("P1");
		p1.setFont(inGameFont.getFont());
		p1.setBackground(Color.darkGray);
		p1.setBounds(205, 10, 40, 40);
		p1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				InGameClicks gc = new InGameClicks(this, 1, false);
					
				if (inGameListener != null) {
					inGameListener.formEventOccurred(gc);
				}	
					
			}
				
		});
		add(p1);
		
		p2 = new JButton("P2");
		p2.setFont(inGameFont.getFont());
		p2.setBackground(Color.darkGray);
		p2.setBounds(250, 10, 40, 40);
		p2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				InGameClicks gc = new InGameClicks(this, 2, true);
					
				if (inGameListener != null) {
					inGameListener.formEventOccurred(gc);
				}	
					
			}
				
		});
		add(p2);
		
		back = new JButton("B");
		back.setFont(inGameFont.getFont());
		back.setBackground(Color.darkGray);
		back.setBounds(455, 10, 40, 40);
		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				InGameClicks gc = new InGameClicks(this, 0);
					
				if (inGameListener != null) {
					inGameListener.formEventOccurred(gc);
				}	
					
			}
				
		});
		add(back);
		
	}
	StatsWindow(Monsters m1, int x) {
		
		setLayout(null);
		setBackground(Color.white);
		
		inGameFont = new InGameFont(gameFont, 5);
		
		EmptyBorder emptyBorder = new EmptyBorder(10,7,10,7);
		BevelBorder lowerBevel = new BevelBorder(BevelBorder.LOWERED);
		LineBorder outerLine = new LineBorder(Color.DARK_GRAY);
		
        CompoundBorder outter = new CompoundBorder(outerLine, lowerBevel);
        CompoundBorder inner = new CompoundBorder(outter, emptyBorder);
        
        //////// SPRITE /////////
		sprite = new JLabel(loadFrontSprite(m1));
		sprite.setBounds(40, 40, 140, 140);
		add(sprite);
		/////// DEXNUMBER ////////
		dexNumber = new JTextField("No. " + m1.getDexNumber());
		dexNumber.setSize(130, 30);
		dexNumber.setBounds(30, 190, 130, 30);
		dexNumber.setFont(inGameFont.getFont());
		dexNumber.setEditable(false);
		dexNumber.setBackground(Color.white);
		dexNumber.setHighlighter(null);
		dexNumber.setBorder(BorderFactory.createEmptyBorder());
		add(dexNumber);
		/////// HEALTH&EXP ////////
		healthExperience = new JLabel();
		healthExperience.setBounds(190, 20, 300, 188); // (40, 40, 140, 140);
		healthExperience.setBorder(BorderFactory.createMatteBorder(0, 0, 10, 5, Color.DARK_GRAY));
		healthExperience.setLayout(null);
		add(healthExperience);
		/////// FILLERS ////////
		fillLabel = new JLabel(" ");
		fillLabel.setBounds(0, 178, 5, 8);
		fillLabel.setOpaque(true);
		fillLabel.setBorder(BorderFactory.createEmptyBorder());
		fillLabel.setBackground(Color.white);
		healthExperience.add(fillLabel);
		fillLabel = new JLabel(" ");
		fillLabel.setBounds(5, 178, 5, 5);
		fillLabel.setOpaque(true);
		fillLabel.setBorder(BorderFactory.createEmptyBorder());
		fillLabel.setBackground(Color.white);
		healthExperience.add(fillLabel);
		fillLabel = new JLabel(" ");
		fillLabel.setBounds(10, 178, 5, 3);
		fillLabel.setOpaque(true);
		fillLabel.setBorder(BorderFactory.createEmptyBorder());
		fillLabel.setBackground(Color.white);
		healthExperience.add(fillLabel);
		fillLabel = new JLabel(" ");
		fillLabel.setBounds(15, 178, 280, 5);
		fillLabel.setOpaque(true);
		fillLabel.setBorder(BorderFactory.createEmptyBorder());
		fillLabel.setBackground(Color.white);
		healthExperience.add(fillLabel);
		/////// NAMEFIELD ////////
		nameField = new JTextField(m1.getName());
		nameField.setSize(295, 27);
		nameField.setBounds(10, 0, 295, 27);
		inGameFont = new InGameFont(gameFont, 3);
		nameField.setFont(inGameFont.getFont());
		nameField.setEditable(false);
		nameField.setBackground(Color.white);
		nameField.setHighlighter(null);
		nameField.setBorder(BorderFactory.createEmptyBorder());
		healthExperience.add(nameField);
		/////// EXPFIELD ////////
		exp = new JTextField("EXP POINTS");
		exp.setSize(250, 27);
		exp.setBounds(10, 54, 250, 27); 
		inGameFont = new InGameFont(gameFont, 3);
		exp.setFont(inGameFont.getFont());
		exp.setEditable(false);
		exp.setBackground(Color.white);
		exp.setHighlighter(null);
		exp.setBorder(BorderFactory.createEmptyBorder());
		healthExperience.add(exp);
		
		exp = new JTextField(Integer.toString(m1.getXP())); 
		exp.setSize(200, 20);
		exp.setBounds(95, 81, 200, 20);
		inGameFont = new InGameFont(gameFont, 0);
		exp.setFont(inGameFont.getFont());
		exp.setEditable(false);
		exp.setBackground(Color.white);
		exp.setHighlighter(null);
		exp.setBorder(BorderFactory.createEmptyBorder());
		exp.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		healthExperience.add(exp);
		/////// LEVELFIELD ////////
		level = new JTextField("LEVEL UP");
		level.setSize(250, 27);
		level.setBounds(10, 108, 250, 27); 
		inGameFont = new InGameFont(gameFont, 3);
		level.setFont(inGameFont.getFont());
		level.setEditable(false);
		level.setBackground(Color.white);
		level.setHighlighter(null);
		level.setBorder(BorderFactory.createEmptyBorder());
		healthExperience.add(level);
		
		lvl = m1.getLevel() + 1;
		level = new JTextField(":L" + lvl);  
		level.setSize(102, 20);
		level.setBounds(193, 135, 102, 20);
		inGameFont = new InGameFont(gameFont, 0);
		level.setFont(inGameFont.getFont());
		level.setEditable(false);
		level.setBackground(Color.white);
		level.setHighlighter(null);
		level.setBorder(BorderFactory.createEmptyBorder());
		level.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		healthExperience.add(level);
		
		level = new JTextField("to");  
		level.setSize(32, 12);
		level.setBounds(167, 143, 32, 12);
		inGameFont = new InGameFont(gameFont, 1);
		level.setFont(inGameFont.getFont());
		level.setEditable(true);
		level.setBackground(Color.white);
		level.setHighlighter(null);
		level.setBorder(BorderFactory.createEmptyBorder());
		healthExperience.add(level);
		
		lvl = m1.getEXPToLVL(m1.getLevel()) - m1.getXP();
		System.out.println(m1.getEXPToLVL(m1.getLevel()) - m1.getXP());
		level = new JTextField(Integer.toString(lvl));  
		level.setSize(171, 20);
		level.setBounds(0, 135, 171, 20);
		inGameFont = new InGameFont(gameFont, 0);
		level.setFont(inGameFont.getFont());
		level.setEditable(true);
		level.setBackground(Color.white);
		level.setHighlighter(null);
		level.setBorder(BorderFactory.createEmptyBorder());
		level.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		healthExperience.add(level);
		//////// MOVEFIELD /////////
		moves = new JLabel();
		moves.setBounds(10, 220, 480, 200);
		moves.setBorder(inner);
		moves.setLayout(null);
		add(moves);
		// Move 1
		move = new JTextField(m1.getMoves(0).getName());  
		move.setSize(420, 20);
		move.setBounds(30, 4, 420, 20);
		inGameFont = new InGameFont(gameFont, 5);
		move.setFont(inGameFont.getFont());
		move.setEditable(true);
		move.setBackground(Color.white);
		move.setHighlighter(null);
		move.setBorder(BorderFactory.createEmptyBorder());
		moves.add(move);
		// PP
		move = new JTextField("PP");  
		move.setSize(55, 20);
		move.setBounds(290, 24, 55, 20);
		inGameFont = new InGameFont(gameFont, 5);
		move.setFont(inGameFont.getFont());
		move.setEditable(true);
		move.setBackground(Color.white);
		move.setHighlighter(null);
		move.setBorder(BorderFactory.createEmptyBorder());
		moves.add(move);
		
		move = new JTextField(m1.getMoves(0).getPP() + "/" + m1.getMoves(0).getMaxPP());  
		move.setSize(140, 20);
		move.setBounds(330, 24, 140, 20);
		inGameFont = new InGameFont(gameFont, 5);
		move.setFont(inGameFont.getFont());
		move.setEditable(true);
		move.setBackground(Color.white);
		move.setHighlighter(null);
		move.setBorder(BorderFactory.createEmptyBorder());
		move.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		moves.add(move);
		// Move 2
		if (m1.getMoves(1) != null) {
			move = new JTextField(m1.getMoves(1).getName());  
			move.setSize(420, 20);
			move.setBounds(30, 54, 420, 20);
			inGameFont = new InGameFont(gameFont, 5);
			move.setFont(inGameFont.getFont());
			move.setEditable(true);
			move.setBackground(Color.white);
			move.setHighlighter(null);
			move.setBorder(BorderFactory.createEmptyBorder());
			moves.add(move);
			// PP
			move = new JTextField("PP");  
			move.setSize(55, 20);
			move.setBounds(290, 74, 55, 20);
			inGameFont = new InGameFont(gameFont, 5);
			move.setFont(inGameFont.getFont());
			move.setEditable(true);
			move.setBackground(Color.white);
			move.setHighlighter(null);
			move.setBorder(BorderFactory.createEmptyBorder());
			moves.add(move);
			
			move = new JTextField(m1.getMoves(1).getPP() + "/" + m1.getMoves(1).getMaxPP());  
			move.setSize(140, 20);
			move.setBounds(330, 74, 140, 20);
			inGameFont = new InGameFont(gameFont, 5);
			move.setFont(inGameFont.getFont());
			move.setEditable(true);
			move.setBackground(Color.white);
			move.setHighlighter(null);
			move.setBorder(BorderFactory.createEmptyBorder());
			move.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			moves.add(move);
		}
		// Move 3
		if (m1.getMoves(2) != null) {
			move = new JTextField(m1.getMoves(2).getName());  
			move.setSize(420, 20);
			move.setBounds(30, 104, 420, 20);
			inGameFont = new InGameFont(gameFont, 5);
			move.setFont(inGameFont.getFont());
			move.setEditable(true);
			move.setBackground(Color.white);
			move.setHighlighter(null);
			move.setBorder(BorderFactory.createEmptyBorder());
			moves.add(move);
			// PP
			move = new JTextField("PP");  
			move.setSize(55, 20);
			move.setBounds(290, 124, 55, 20);
			inGameFont = new InGameFont(gameFont, 5);
			move.setFont(inGameFont.getFont());
			move.setEditable(true);
			move.setBackground(Color.white);
			move.setHighlighter(null);
			move.setBorder(BorderFactory.createEmptyBorder());
			moves.add(move);
			
			move = new JTextField(m1.getMoves(2).getPP() + "/" + m1.getMoves(2).getMaxPP());  
			move.setSize(140, 20);
			move.setBounds(330, 124, 140, 20);
			inGameFont = new InGameFont(gameFont, 5);
			move.setFont(inGameFont.getFont());
			move.setEditable(true);
			move.setBackground(Color.white);
			move.setHighlighter(null);
			move.setBorder(BorderFactory.createEmptyBorder());
			move.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			moves.add(move);
		}
		// Move 4
		if (m1.getMoves(3) != null) {
			move = new JTextField(m1.getMoves(3).getName());  
			move.setSize(420, 20);
			move.setBounds(30, 154, 420, 20);
			inGameFont = new InGameFont(gameFont, 5);
			move.setFont(inGameFont.getFont());
			move.setEditable(true);
			move.setBackground(Color.white);
			move.setHighlighter(null);
			move.setBorder(BorderFactory.createEmptyBorder());
			moves.add(move);
			// PP
			move = new JTextField("PP");  
			move.setSize(55, 20);
			move.setBounds(290, 174, 55, 20);
			inGameFont = new InGameFont(gameFont, 5);
			move.setFont(inGameFont.getFont());
			move.setEditable(true);
			move.setBackground(Color.white);
			move.setHighlighter(null);
			move.setBorder(BorderFactory.createEmptyBorder());
			moves.add(move);
			
			move = new JTextField(m1.getMoves(3).getPP() + "/" + m1.getMoves(3).getMaxPP());  
			move.setSize(140, 20);
			move.setBounds(330, 174, 140, 20);
			inGameFont = new InGameFont(gameFont, 5);
			move.setFont(inGameFont.getFont());
			move.setEditable(true);
			move.setBackground(Color.white);
			move.setHighlighter(null);
			move.setBorder(BorderFactory.createEmptyBorder());
			move.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			moves.add(move);
		}
		
	}
	StatsWindow(Monsters m1) {
		
		setLayout(null);
		setBackground(Color.white);
		inGameFont = new InGameFont(gameFont, 5);
		
		EmptyBorder emptyBorder = new EmptyBorder(10,7,10,7);
		BevelBorder lowerBevel = new BevelBorder(BevelBorder.LOWERED);
		LineBorder outerLine = new LineBorder(Color.DARK_GRAY);
		
        CompoundBorder outter = new CompoundBorder(outerLine, lowerBevel);
        CompoundBorder inner = new CompoundBorder(outter, emptyBorder);
		
        //////// SPRITE /////////
		sprite = new JLabel(loadFrontSprite(m1));
		sprite.setBounds(40, 40, 140, 140);
		add(sprite);
		/////// DEXNUMBER ////////
		dexNumber = new JTextField("No. " + m1.getDexNumber());
		dexNumber.setSize(130, 30);
		dexNumber.setBounds(30, 190, 130, 30);
		dexNumber.setFont(inGameFont.getFont());
		dexNumber.setEditable(false);
		dexNumber.setBackground(Color.white);
		dexNumber.setHighlighter(null);
		dexNumber.setBorder(BorderFactory.createEmptyBorder());
		add(dexNumber);
		//////// STATSFIELD /////////
		stats = new JLabel();
		stats.setBounds(10, 220, 200, 200);
		stats.setBorder(inner);
		stats.setLayout(null);
		add(stats);
		/////// ATTACK STAT ////////
		statsField = new JTextField("ATTACK");
		statsField.setSize(140, 20);
		statsField.setFont(inGameFont.getFont());
		statsField.setBounds(10, 6, 140, 20);
		statsField.setEditable(false);
		statsField.setBackground(Color.white);
		statsField.setHighlighter(null);
		statsField.setBorder(BorderFactory.createEmptyBorder());
		stats.add(statsField);
		
		statsField = new JTextField(Integer.toString(m1.getAttack()));
		statsField.setSize(74, 20);
		statsField.setFont(inGameFont.getFont());
		statsField.setBounds(120, 26, 74, 20);
		statsField.setEditable(false);
		statsField.setBackground(Color.white);
		statsField.setHighlighter(null);
		statsField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		statsField.setBorder(BorderFactory.createEmptyBorder());
		stats.add(statsField);
		/////// DEFENSE STAT ////////
		statsField = new JTextField("DEFENSE");
		statsField.setSize(149, 20);
		statsField.setFont(inGameFont.getFont());
		statsField.setBounds(10, 56, 149, 20);
		statsField.setEditable(false);
		statsField.setBackground(Color.white);
		statsField.setHighlighter(null);
		statsField.setBorder(BorderFactory.createEmptyBorder());
		stats.add(statsField);
		
		statsField = new JTextField(Integer.toString(m1.getDefense()));
		statsField.setSize(74, 20);
		statsField.setFont(inGameFont.getFont());
		statsField.setBounds(120, 76, 74, 20);
		statsField.setEditable(false);
		statsField.setBackground(Color.white);
		statsField.setHighlighter(null);
		statsField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		statsField.setBorder(BorderFactory.createEmptyBorder());
		stats.add(statsField);
		/////// SPEED STAT ////////
		statsField = new JTextField("SPEED");
		statsField.setSize(130, 20);
		statsField.setFont(inGameFont.getFont());
		statsField.setBounds(10, 106, 130, 20);
		statsField.setEditable(false);
		statsField.setBackground(Color.white);
		statsField.setHighlighter(null);
		statsField.setBorder(BorderFactory.createEmptyBorder());
		stats.add(statsField);
		
		statsField = new JTextField(Integer.toString(m1.getSpeed()));
		statsField.setSize(74, 20);
		statsField.setFont(inGameFont.getFont());
		statsField.setBounds(120, 126, 74, 20);
		statsField.setEditable(false);
		statsField.setBackground(Color.white);
		statsField.setHighlighter(null);
		statsField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		statsField.setBorder(BorderFactory.createEmptyBorder());
		stats.add(statsField);
		/////// SPECIAL STAT ////////
		statsField = new JTextField("SPECIAL");
		statsField.setSize(145, 20);
		statsField.setFont(inGameFont.getFont());
		statsField.setBounds(10, 156, 145, 20);
		statsField.setEditable(false);
		statsField.setBackground(Color.white);
		statsField.setHighlighter(null);
		statsField.setBorder(BorderFactory.createEmptyBorder());
		stats.add(statsField);
		
		statsField = new JTextField(Integer.toString(m1.getSpecial()));
		statsField.setSize(74, 20);
		statsField.setFont(inGameFont.getFont());
		statsField.setBounds(120, 176, 74, 20);
		statsField.setEditable(false);
		statsField.setBackground(Color.white);
		statsField.setHighlighter(null);
		statsField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		statsField.setBorder(BorderFactory.createEmptyBorder());
		stats.add(statsField);
		/////// HEALTH&STATUS ////////
		healthStatus = new JLabel();
		healthStatus.setBounds(190, 20, 300, 188); // (40, 40, 140, 140);
		healthStatus.setBorder(BorderFactory.createMatteBorder(0, 0, 10, 5, Color.DARK_GRAY));
		healthStatus.setLayout(null);
		add(healthStatus);
		/////// FILLERS ////////
		fillLabel = new JLabel(" ");
		fillLabel.setBounds(0, 178, 5, 8);
		fillLabel.setOpaque(true);
		fillLabel.setBorder(BorderFactory.createEmptyBorder());
		fillLabel.setBackground(Color.white);
		healthStatus.add(fillLabel);
		fillLabel = new JLabel(" ");
		fillLabel.setBounds(5, 178, 5, 5);
		fillLabel.setOpaque(true);
		fillLabel.setBorder(BorderFactory.createEmptyBorder());
		fillLabel.setBackground(Color.white);
		healthStatus.add(fillLabel);
		fillLabel = new JLabel(" ");
		fillLabel.setBounds(10, 178, 5, 3);
		fillLabel.setOpaque(true);
		fillLabel.setBorder(BorderFactory.createEmptyBorder());
		fillLabel.setBackground(Color.white);
		healthStatus.add(fillLabel);
		/////// NAMEFIELD ////////
		nameField = new JTextField(m1.getName());
		nameField.setSize(295, 27);
		nameField.setBounds(10, 0, 295, 27);
		inGameFont = new InGameFont(gameFont, 3);
		nameField.setFont(inGameFont.getFont());
		nameField.setEditable(false);
		nameField.setBackground(Color.white);
		nameField.setHighlighter(null);
		nameField.setBorder(BorderFactory.createEmptyBorder());
		healthStatus.add(nameField);
		/////// STATUSFIELD ////////
		statusField = new JTextField("STATUS/OK");
		statusField.setSize(285, 40);
		statusField.setBounds(15, 143, 280, 40);
		inGameFont = new InGameFont(gameFont, 3);
		statusField.setFont(inGameFont.getFont());
		statusField.setEditable(false);
		statusField.setBackground(Color.white);
		statusField.setHighlighter(null);
		statusField.setBorder(BorderFactory.createEmptyBorder());
		healthStatus.add(statusField);
		/////// HP: ////////
		inGameFont = new InGameFont(gameFont, 1);
		hp = new JTextField("HP:");
		hp.setBounds(80, 60, 40, 10);
		hp.setBorder(BorderFactory.createEmptyBorder());
		hp.setHighlighter(null);
		hp.setBackground(Color.white);
		hp.setFont(inGameFont.getFont());
		hp.setEditable(false);
		healthStatus.add(hp); 
		/////// HEALTHBAR ////////
		healthBar = new HealthBar(m1.getHP(), m1.getMaxHP());
		healthBar.setBounds(120, 60, 190, 10);
		healthStatus.add(healthBar);
		/////// HP/MAXHP ////////
		inGameFont = new InGameFont(gameFont, 5);
		hp = new JTextField(m1.getHP() + " / " + m1.getMaxHP());
		hp.setBounds(130, 75, 165, 20);
		hp.setBorder(BorderFactory.createEmptyBorder());
		hp.setHighlighter(null);
		hp.setBackground(Color.white);
		hp.setFont(inGameFont.getFont());
		hp.setEditable(false);
		healthStatus.add(hp);
		/////// LEVEL ////////
		inGameFont = new InGameFont(gameFont, 0);
		level = new JTextField(":L " + m1.getLevel());
		level.setBounds(150, 30, 96, 20);
		level.setBorder(BorderFactory.createEmptyBorder());
		level.setHighlighter(null);
		level.setBackground(Color.white);
		level.setFont(inGameFont.getFont());
		level.setEditable(false);
		healthStatus.add(level); 
		/////// GENERALINFO ////////
		generalInfo = new JLabel();
		generalInfo.setBounds(220, 240, 230, 180); // 220, 240, 270, 180
		generalInfo.setBorder(BorderFactory.createMatteBorder(0, 0, 10, 5, Color.DARK_GRAY));
		generalInfo.setLayout(null);
		add(generalInfo);
		/////// FILLERS ////////
		fillLabel = new JLabel(" ");
		fillLabel.setBounds(0, 170, 45, 10);
		fillLabel.setOpaque(true);
		fillLabel.setBorder(BorderFactory.createEmptyBorder());
		fillLabel.setBackground(Color.white);
		generalInfo.add(fillLabel);
		fillLabel = new JLabel(" ");
		fillLabel.setBounds(45, 170, 5, 8);
		fillLabel.setOpaque(true);
		fillLabel.setBorder(BorderFactory.createEmptyBorder());
		fillLabel.setBackground(Color.white);
		generalInfo.add(fillLabel);
		fillLabel = new JLabel(" ");
		fillLabel.setBounds(50, 170, 5, 5);
		fillLabel.setOpaque(true);
		fillLabel.setBorder(BorderFactory.createEmptyBorder());
		fillLabel.setBackground(Color.white);
		generalInfo.add(fillLabel);
		fillLabel = new JLabel(" ");
		fillLabel.setBounds(55, 170, 5, 3);
		fillLabel.setOpaque(true);
		fillLabel.setBorder(BorderFactory.createEmptyBorder());
		fillLabel.setBackground(Color.white);
		generalInfo.add(fillLabel);
		fillLabel = new JLabel(" ");
		fillLabel.setBounds(60, 170, 5, 0);
		fillLabel.setOpaque(true);
		fillLabel.setBorder(BorderFactory.createEmptyBorder());
		fillLabel.setBackground(Color.white);
		generalInfo.add(fillLabel);
		fillLabel = new JLabel(" ");
		fillLabel.setBounds(65, 170, 160, 5);
		fillLabel.setOpaque(true);
		fillLabel.setBorder(BorderFactory.createEmptyBorder());
		fillLabel.setBackground(Color.white);
		generalInfo.add(fillLabel);
		/////// TYPE1 ////////
		type = new JTextField("TYPE1/");
		type.setBounds(5, 5, 150, 20);
		type.setBorder(BorderFactory.createEmptyBorder());
		type.setEditable(false);
		type.setBackground(Color.white);
		inGameFont = new InGameFont(gameFont, 5);
		type.setFont(inGameFont.getFont());
		type.setHighlighter(null);
		generalInfo.add(type);
		/////// TYPE1 ////////
		type = new JTextField(m1.getType());
		type.setBounds(27, 25, 150, 20);
		type.setBorder(BorderFactory.createEmptyBorder());
		type.setEditable(false);
		type.setBackground(Color.white);
		inGameFont = new InGameFont(gameFont, 5);
		type.setFont(inGameFont.getFont());
		type.setHighlighter(null);
		generalInfo.add(type);
		if (m1.getTypeTwo() != null) {
			/////// TYPE2 ////////
			type = new JTextField("TYPE2/");
			type.setBounds(5, 45, 150, 20);
			type.setBorder(BorderFactory.createEmptyBorder());
			type.setEditable(false);
			type.setBackground(Color.white);
			inGameFont = new InGameFont(gameFont, 5);
			type.setFont(inGameFont.getFont());
			type.setHighlighter(null);
			generalInfo.add(type);
			/////// TYPE2 ////////
			type = new JTextField(m1.getTypeTwo());
			type.setBounds(27, 65, 150, 20);
			type.setBorder(BorderFactory.createEmptyBorder());
			type.setEditable(false);
			type.setBackground(Color.white);
			inGameFont = new InGameFont(gameFont, 5);
			type.setFont(inGameFont.getFont());
			type.setHighlighter(null);
			generalInfo.add(type);
		}
		/////// ID ////////
		idNo = new JTextField("IDNo/");
		idNo.setBounds(5, 85, 150, 20);
		idNo.setBorder(BorderFactory.createEmptyBorder());
		idNo.setEditable(false);
		idNo.setBackground(Color.white);
		inGameFont = new InGameFont(gameFont, 5);
		idNo.setFont(inGameFont.getFont());
		idNo.setHighlighter(null);
		generalInfo.add(idNo);
		/////// ID ////////
		idNo = new JTextField(m1.getRandomIDNo());
		idNo.setBounds(35, 105, 150, 20);
		idNo.setBorder(BorderFactory.createEmptyBorder());
		idNo.setEditable(false);
		idNo.setBackground(Color.white);
		inGameFont = new InGameFont(gameFont, 5);
		idNo.setFont(inGameFont.getFont());
		idNo.setHighlighter(null);
		generalInfo.add(idNo);
		/////// OTNAME ////////
		idNo = new JTextField("OT/");
		idNo.setBounds(5, 125, 150, 20);
		idNo.setBorder(BorderFactory.createEmptyBorder());
		idNo.setEditable(false);
		idNo.setBackground(Color.white);
		inGameFont = new InGameFont(gameFont, 5);
		idNo.setFont(inGameFont.getFont());
		idNo.setHighlighter(null);
		generalInfo.add(idNo);
		/////// OTNAME ////////
		idNo = new JTextField("RED");
		idNo.setBounds(35, 145, 150, 20);
		idNo.setBorder(BorderFactory.createEmptyBorder());
		idNo.setEditable(false);
		idNo.setBackground(Color.white);
		inGameFont = new InGameFont(gameFont, 5);
		idNo.setFont(inGameFont.getFont());
		idNo.setHighlighter(null);
		generalInfo.add(idNo);
	}

	private ImageIcon loadFrontSprite(Monsters m1) {
		String userDirectory = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Version 1.0 Folder" + File.separator + "Game assets" + File.separator + "Sprites" + File.separator + "gray";
		File custom = new File(userDirectory);
		String absoluteP = custom.getAbsolutePath();
		String imgFile = absoluteP +  File.separator + m1.getID();
		ImageIcon icon = new ImageIcon(imgFile);
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(140, 140,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		icon = new ImageIcon(newimg);
		
        return icon;
	}
	public void setInGameListener(InGameListener listener) {
		this.inGameListener = listener;
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
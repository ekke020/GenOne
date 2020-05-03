package inGameInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import inGamePlayer.Player;

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
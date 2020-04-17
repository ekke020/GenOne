package inGameInterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import inGamePlayer.Player;

public class ItemTeamMenu extends JPanel{

	private InGameListener inGameListener;
	private InGameInventoryListener inGameInventoryListener;
	
	private HashMap<Integer, Integer> buttonIndex;
	private HashMap<Integer, JButton> buttonMap;
	private HashMap<Integer, HealthBar> hpBarMap;
	private HashMap<Integer, JTextField> hpTextField;
	public HashMap<Integer, Integer> getButtonIndex() {
		return buttonIndex;
	}
	
	private InGameFont inGameFont;
	private Font gameFont;
	
	private Color transparent;
	private JLabel p1s;

	private HealthBar p1Bar;
	
	private JTextField p1n;
	private JTextField hpt;
	private JTextField hp1;
	private JTextField lvl1;
	
	private JButton use;
	private JButton back;
	
	private Timer timer;
	
	private int bounds1, bounds2, bounds3, bounds4, bounds5;
	private static boolean itemUsed;
	
	ItemTeamMenu(Player p1) {
		setLayout(null);
		
		setBackground(Color.white);
		transparent = new Color( 0, 0, 0, 0);
		
		
		bounds1 = 2;
		bounds2 = 31;
		bounds3 = 32;
		bounds4 = 5;
		bounds5 = 6;
		
		buttonIndex = new HashMap<Integer, Integer>();
		buttonMap = new HashMap<Integer, JButton>();
		hpBarMap = new HashMap<Integer, HealthBar>();
		hpTextField = new HashMap<Integer, JTextField>();
		
		for (int i = 0; i < p1.getEntireActiveTeam().length; i++) {
			if (i != 0) {
				bounds1 += 55;
				bounds2 += 55;
				bounds3 += 55;
				bounds4 += 55;
				bounds5 += 55;
			}
			p1s = new JLabel(loadMenuSprite(p1.getActiveTeam(i).getMenuID()));
			p1s.setBounds(10, bounds1, 45, 45); // 10, 57, 45, 45) (2), 10, 112, 45, 45) (3), 10, 167, 45, 45) (4), 10, 222, 45, 45) (5), 0, 277, 45, 45), (6)
			add(p1s); // Menu sprite
			inGameFont = new InGameFont(gameFont, 1);
			hpt = new JTextField("HP:");
			hpt.setBounds(80, bounds2, 40, 10);
			hpt.setBorder(BorderFactory.createEmptyBorder());
			hpt.setBackground(transparent);
			hpt.setFont(inGameFont.getFont());
			hpt.setEditable(false);
			add(hpt); // HP
			p1Bar = new HealthBar(p1.getActiveTeam(i).getHP(), p1.getActiveTeam(i).getMaxHP());
			hpBarMap.put(i, p1Bar);
			p1Bar.setBounds(120, bounds3, 190, 75);
			add(p1Bar); // HP bar
			inGameFont = new InGameFont(gameFont, 0);
			p1n = new JTextField(p1.getActiveTeam(i).getName());
			p1n.setBorder(BorderFactory.createEmptyBorder());
			p1n.setEditable(false);
			p1n.setFont(inGameFont.getFont());
			p1n.setBounds(70, bounds1, 200, 30);
			add(p1n); // Name
			inGameFont = new InGameFont(gameFont, 1);
			hp1 = new JTextField(p1.getActiveTeam(i).getHP() + " / " + p1.getActiveTeam(i).getMaxHP());
			hpTextField.put(i, hp1);
			hp1.setBounds(300, bounds2, 108, 10);
			hp1.setBorder(BorderFactory.createEmptyBorder());
			hp1.setBackground(transparent);
			hp1.setFont(inGameFont.getFont());
			hp1.setEditable(false);
			add(hp1); // HP / MAXHP
			inGameFont = new InGameFont(gameFont, 0);
			lvl1 = new JTextField(":L " + p1.getActiveTeam(i).getLevel());
			lvl1.setBounds(285, bounds4, 93, 20);
			lvl1.setBorder(BorderFactory.createEmptyBorder());
			lvl1.setBackground(transparent);
			lvl1.setFont(inGameFont.getFont());
			lvl1.setEditable(false);
			add(lvl1); // Level
			inGameFont = new InGameFont(gameFont, 4);
			use = new JButton("Use");
			buttonIndex.put(use.hashCode(), i);
			buttonMap.put(i, use);
			use.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					JButton pokemon = (JButton) e.getSource();
					InventoryClicks gc = new InventoryClicks(this, pokemon.hashCode());
					if (inGameInventoryListener != null) {
						inGameInventoryListener.formEventOccurred(gc);
					}	
						
				}
					
			});
			use.setFont(inGameFont.getFont());
			use.setHorizontalAlignment(SwingConstants.CENTER);
			use.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
			use.setBounds(415, bounds5, 45, 40);
			add(use);
		}
		
	}
	ItemTeamMenu(TextUI textUI) {
		
		setLayout(null);
		setBackground(Color.white);
		
		textUI.setInstantText("Choose  a  POKEMON.");
		inGameFont = new InGameFont(gameFont, 1);
		
		back = new JButton("Back");
		back.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
					
				InGameClicks gc = new InGameClicks(this, 0, itemUsed);
					
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
	
	public void removeUse() {
		
		for (int i = 0; i < buttonMap.size(); i++ ) {
			JButton key = buttonMap.get(i);
			remove(key);
		}
		itemUsed = true;
		validate();
		repaint();
	}
	
	private int amounts;
	public void heal(Player p1, BattleElements b1, int x, int amount) {
		this.amounts = amount;
		HealthBar updatehp = hpBarMap.get(x); 
		JTextField updatehpText = hpTextField.get(x);
		int hptwo = 1;
		timer = new Timer(65, new ActionListener() { //45

			public void actionPerformed(ActionEvent e) {
				
				if (p1.getActiveTeam(x).getHP() == p1.getActiveTeam(x).getMaxHP()) 
					amounts = 0;	
				else if (amounts >= 1) {
					p1.getActiveTeam(x).potion(hptwo);
					amounts--;
					updatehp.updateHP(p1.getActiveTeam(x).getHP(), p1.getActiveTeam(x).getMaxHP());
					updatehpText.setText(p1.getActiveTeam(x).getHP() + " / " + p1.getActiveTeam(x).getMaxHP());
					repaint();
					revalidate();
					System.out.println(amounts);
				}	
				if (amounts == 0) {
					if (x == 0)
						b1.updateInterface(p1, x);
					((Timer) e.getSource()).stop();
					
				}	
			}
			
		});
		timer.start();
				
	}
	public Timer getTimer() {
		return timer;
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
	public void SetInGameInventoryListener(InGameInventoryListener listener) {
		this.inGameInventoryListener = listener;
	}
}

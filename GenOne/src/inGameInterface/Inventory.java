package inGameInterface;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import inGamePlayer.Player;

public class Inventory extends JPanel{

	private InGameListener inGameListener;
	
	private JPanel inventoryPanel;
	
	private JButton items;
	private JButton balls;
	private JButton keyItems;
	private JButton tmHm;
	private JButton back;
	
	private JTextField name;
	private JTextField nameTwo;
	private JTextField nameThree;
	private JTextField nameFour;
	
	private InGameFont inGameFont;
	private Font gameFont;
	Inventory() {
	//	setLayout(new FlowLayout(FlowLayout.LEFT));
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		setBackground(Color.white);
		
		inGameFont = new InGameFont(gameFont, 6);
		items = new JButton(loadMenuIcon("Items.png", 70, 50));
		items.setBorder(null);
		balls = new JButton(loadMenuIcon("PokeBalls.png", 70, 50));
		balls.setBorder(null);
		keyItems = new JButton(loadMenuIcon("KeyItems.png", 70, 50));
		keyItems.setBorder(null);
		tmHm = new JButton(loadMenuIcon("TMHM.png", 70, 50));
		tmHm.setBorder(null);
		name = new JTextField("Items");
		name.setFont(inGameFont.getFont());
		name.setBorder(null);
		name.setEditable(false);
		name.setHighlighter(null);
		nameTwo = new JTextField(" PokeBalls");
		nameTwo.setFont(inGameFont.getFont());
		nameTwo.setBorder(null);
		nameTwo.setEditable(false);
		nameTwo.setHighlighter(null);
		nameThree = new JTextField(" Key Items");
		nameThree.setFont(inGameFont.getFont());
		nameThree.setBorder(null);
		nameThree.setEditable(false);
		nameThree.setHighlighter(null);
		nameFour = new JTextField("TM/HM");
		nameFour.setFont(inGameFont.getFont());
		nameFour.setBorder(null);
		nameFour.setEditable(false);
		nameFour.setHighlighter(null);
		
		///////////// FIRST ICON //////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 40, 0, 0);
		add(items, gc);
		/////////// FIRST NAME /////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 1;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 54, 0, 0);
		add(name, gc);
		///////////// SECOND ICON //////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(balls, gc);
		/////////// SECOND NAME /////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.gridy = 1;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameTwo, gc);
		///////////// THIRD ICON //////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 2;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(keyItems, gc);
		/////////// THIRD NAME /////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 2;
		gc.gridy = 1;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(nameThree, gc);
		///////////// FOURTH ICON //////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 3;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(tmHm, gc);
		/////////// FOURTH NAME /////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 3;
		gc.gridy = 1;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(0, 12, 0, 0);
		add(nameFour, gc);
	}
	Inventory(Player p1) {
		
		GridBagConstraints gc = new GridBagConstraints();
		setBackground(Color.white);
		setLayout(null);
		EmptyBorder emptyBorder = new EmptyBorder(30,30,10,10);
		BevelBorder lowerBevel = new BevelBorder(BevelBorder.LOWERED);
		LineBorder outerLine = new LineBorder(Color.DARK_GRAY);
		
        CompoundBorder outter = new CompoundBorder(outerLine, lowerBevel);
        CompoundBorder inner = new CompoundBorder(outter, emptyBorder);
		
		inGameFont = new InGameFont(gameFont, 6);
		
		inventoryPanel = new JPanel();
		inventoryPanel.setBackground(Color.white);
		inventoryPanel.setBounds(15, 15, 460, 228); // 5, 70, 490, 258
		inventoryPanel.setBorder(inner);
		add(inventoryPanel);
		
		
	}
	Inventory(TextUI textUI) {
		
		setLayout(null);
		setBackground(Color.white);
		
		//textUI.setInstantText("Choose  a  POKEMON.");
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
	protected ImageIcon loadMenuIcon(String id, int x, int y) {
		String userDirectory = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Version 1.0 Folder" + File.separator + "Game assets" + File.separator + "InventorySprites";
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
	public void setInGameListener(InGameListener listener) {
		this.inGameListener = listener;
	}
}

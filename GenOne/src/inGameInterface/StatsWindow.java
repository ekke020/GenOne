package inGameInterface;

import java.awt.Color;
import java.awt.ComponentOrientation;
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
import javax.swing.JToggleButton;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import pokemon.Monsters;

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

	StatsWindow(Monsters m1, int x, int y) {
		
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
		sprite.setBounds(40, 70, 140, 140);
		add(sprite);
		
		dexNumber = new JTextField("No. " + m1.getDexNumber());
		dexNumber.setBounds(40, 230, 140, 20);
		dexNumber.setFont(inGameFont.getFont());
		dexNumber.setBorder(BorderFactory.createEmptyBorder());
		dexNumber.setEditable(false);
		dexNumber.setBackground(Color.white);
		dexNumber.setHighlighter(null);
		add(dexNumber);
		
		nameField = new JTextField(m1.getName());
		nameField.setBounds(200, 40, 220, 20);
		nameField.setFont(inGameFont.getFont());
		nameField.setBorder(BorderFactory.createEmptyBorder());
		nameField.setEditable(false);
		nameField.setBackground(Color.white);
		nameField.setHighlighter(null);
		add(nameField);
		
		if (m1.getTypeTwo() == null) {
			type = new JTextField(m1.getType());
			type.setBounds(200, 80, 260, 20);
			type.setFont(inGameFont.getFont());
			type.setBorder(BorderFactory.createEmptyBorder());
			type.setEditable(false);
			type.setBackground(Color.white);
			type.setHighlighter(null);
			add(type);
		}
		else {
			type = new JTextField(m1.getType() + " / " + m1.getTypeTwo());
			type.setBounds(200, 80, 260, 20);
			type.setFont(inGameFont.getFont());
			type.setBorder(BorderFactory.createEmptyBorder());
			type.setEditable(false);
			type.setBackground(Color.white);
			type.setHighlighter(null);
			add(type);
		}
		level = new JTextField("Level:  " + m1.getLevel());
		level.setBounds(200, 120, 200, 20);
		level.setFont(inGameFont.getFont());
		level.setBorder(BorderFactory.createEmptyBorder());
		level.setEditable(false);
		level.setBackground(Color.white);
		level.setHighlighter(null);
		add(level);
		
		generalInfo = new JLabel();
		generalInfo.setBounds(200, 160, 280, 140);
		generalInfo.setBorder(inner);
		add(generalInfo);
		
		/////// HEALTH ///////
		statsField = new JTextField("HP:");
		statsField.setBounds(10, 10, 70, 20);
		statsField.setFont(inGameFont.getFont());
		statsField.setBorder(BorderFactory.createEmptyBorder());
		statsField.setEditable(false);
		statsField.setBackground(Color.white);
		statsField.setHighlighter(null);
		generalInfo.add(statsField);
		
		statsField = new JTextField(Integer.toString(m1.getMaxHP()));
		statsField.setBounds(170, 10, 100, 20);
		statsField.setFont(inGameFont.getFont());
		statsField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		statsField.setBorder(BorderFactory.createEmptyBorder());
		statsField.setEditable(false);
		statsField.setBackground(Color.white);
		statsField.setHighlighter(null);
		generalInfo.add(statsField);
		/////// ATTACK ///////
		statsField = new JTextField("ATTACK:");
		statsField.setBounds(10, 40, 160, 20);
		statsField.setFont(inGameFont.getFont());
		statsField.setBorder(BorderFactory.createEmptyBorder());
		statsField.setEditable(false);
		statsField.setBackground(Color.white);
		statsField.setHighlighter(null);
		generalInfo.add(statsField);
		
		statsField = new JTextField(Integer.toString(m1.getAttack()));
		statsField.setBounds(170, 40, 100, 20);
		statsField.setFont(inGameFont.getFont());
		statsField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		statsField.setBorder(BorderFactory.createEmptyBorder());
		statsField.setEditable(false);
		statsField.setBackground(Color.white);
		statsField.setHighlighter(null);
		generalInfo.add(statsField);
		/////// DEFENSE ///////
		statsField = new JTextField("DEFENSE:");
		statsField.setBounds(10, 70, 170, 20);
		statsField.setFont(inGameFont.getFont());
		statsField.setBorder(BorderFactory.createEmptyBorder());
		statsField.setEditable(false);
		statsField.setBackground(Color.white);
		statsField.setHighlighter(null);
		generalInfo.add(statsField);
		
		statsField = new JTextField(Integer.toString(m1.getDefense()));
		statsField.setBounds(170, 70, 100, 20);
		statsField.setFont(inGameFont.getFont());
		statsField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		statsField.setBorder(BorderFactory.createEmptyBorder());
		statsField.setEditable(false);
		statsField.setBackground(Color.white);
		statsField.setHighlighter(null);
		generalInfo.add(statsField);
		/////// SPECIAL ///////
		statsField = new JTextField("SPECIAL:");
		statsField.setBounds(10, 100, 165, 20);
		statsField.setFont(inGameFont.getFont());
		statsField.setBorder(BorderFactory.createEmptyBorder());
		statsField.setEditable(false);
		statsField.setBackground(Color.white);
		statsField.setHighlighter(null);
		generalInfo.add(statsField);
		
		statsField = new JTextField(Integer.toString(m1.getSpecial()));
		statsField.setBounds(170, 100, 100, 20);
		statsField.setFont(inGameFont.getFont());
		statsField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		statsField.setBorder(BorderFactory.createEmptyBorder());
		statsField.setEditable(false);
		statsField.setBackground(Color.white);
		statsField.setHighlighter(null);
		generalInfo.add(statsField);
		
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
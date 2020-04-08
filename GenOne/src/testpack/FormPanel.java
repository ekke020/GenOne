package testpack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class FormPanel extends JPanel {

	private JButton newGame;
	private JButton loadGame;
	private JButton options;
	private FormListener formListener;
	
	private JTextArea newGameInfo;
	private JButton startGame;
	
	private JButton save1;
	private JButton save2;
	private JButton save3;
	FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		newGame = new JButton("New Game");
		newGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				FormClicks fc = new FormClicks(this, 1);
				
				if (formListener != null) {
					formListener.formEventOccurred(fc);
				}
			}
			
		});
		loadGame = new JButton("Load Game");
		loadGame.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				FormClicks fc = new FormClicks(this, 2);
				
				if (formListener != null) {
					formListener.formEventOccurred(fc);
				}
				
			}
			
		});
		options = new JButton("Options");
		
		newGame.setBorder(BorderFactory.createEtchedBorder());
		loadGame.setBorder(BorderFactory.createEtchedBorder());
		options.setBorder(BorderFactory.createEtchedBorder());
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		
		///////// FIRST BUTTON ///////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0; // sets the horizontal alignment.
		gc.gridy = 0; // sets the vertical alignment.
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(newGame, gc);
		///////// SECOND BUTTON ///////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0; // sets the horizontal alignment.
		gc.gridy = 1; // sets the vertical alignment.
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(loadGame, gc);
		///////// THIRD BUTTON ///////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0; // sets the horizontal alignment.
		gc.gridy = 2; // sets the vertical alignment.
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.CENTER;
		gc.insets = new Insets(0, 0, 0, 0);
		add(options, gc);
	}
	
	public FormPanel(Users u) {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
									 
		newGameInfo = new JTextArea("***********************************************************\n"
								 +  "*                       Welcome to Pokemon 1.0                       *\n" 
								 +  "***********************************************************\n"
								 +  "* Developed and published by Johan Ekman!                  *\n"
								 +  "* Some elements might have been borrowed or              *\n"
								 +  "* inspired by another game.                                            *\n"
								 +  "***********************************************************\n"
								 +  "*                                                                                      *\n"
								 +  "***********************************************************\n"
								 +  "*Make sure you are logged in before starting the game! *\n"
								 +  "*If you are not logged in you wont be able to save your *\n"
								 +  "*progress.                                                                       *\n"
								 +  "***********************************************************");
		newGameInfo.setEditable(false);
		newGameInfo.setBorder(BorderFactory.createEtchedBorder());
		startGame = new JButton("Start GAME");
		startGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormClicks fc = new FormClicks(this, 1);
				
				if (formListener != null) {
					formListener.formEventOccurred(fc);
				}
				
			}
			
		});
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		//////////////////// FIRST ROW //////////////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0; // sets the horizontal alignment.
		gc.gridy = 0; // sets the vertical alignment.
		gc.fill = GridBagConstraints.LINE_START;
		gc.anchor = GridBagConstraints.NORTH;
		gc.insets = new Insets(0, 0, 0, 0);
		
		add(newGameInfo, gc);
		////////////////////Second ROW //////////////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0; // sets the horizontal alignment.
		gc.gridy = 1; // sets the vertical alignment.
		gc.fill = GridBagConstraints.LINE_START;
		gc.anchor = GridBagConstraints.NORTH;
		gc.insets = new Insets(0, 0, 0, 0);
		
		add(startGame, gc);
	}
	
	public FormPanel(Users u, int i) {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		if(u.getSaves(1) != null) {
			save1 = new JButton(u.getSaves(1).getName());
		}
		else {
			save1 = new JButton("Empty SAVEFILE");
		}
		if(u.getSaves(2) != null) {
			save2 = new JButton(u.getSaves(1).getName());
		}
		else {
			save2 = new JButton("Empty SAVEFILE");
		}
		if(u.getSaves(3) != null) {
			save3 = new JButton(u.getSaves(1).getName());
		}
		else {
			save3 = new JButton("Empty SAVEFILE");
		}
// Buttons are not finished. Add elements and customize the buttons so that they show progress of the saveFile.
		
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		//////////////////// FIRST BUTTON //////////////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0; // sets the horizontal alignment.
		gc.gridy = 0; // sets the vertical alignment.
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.NORTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(save1, gc);
		//////////////////// SECOND BUTTON //////////////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0; // sets the horizontal alignment.
		gc.gridy = 1; // sets the vertical alignment.
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.NORTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(save2, gc);
		//////////////////// THIRD BUTTON //////////////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0; // sets the horizontal alignment.
		gc.gridy = 2; // sets the vertical alignment.
		gc.fill = GridBagConstraints.BOTH;
		gc.anchor = GridBagConstraints.NORTH;
		gc.insets = new Insets(0, 0, 0, 0);
		add(save3, gc);
	}
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
}

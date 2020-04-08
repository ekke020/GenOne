package testpack;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel{

	private JButton sMenu;
	private JButton sLogin;
	private JButton logout;
	private JLabel userName;
	
	
	private Users users;
	private ToolListener toolListener;
	


	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		
		sMenu = new JButton("Menu");
		sMenu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ToolClicks tl = new ToolClicks(this, 1);
				
				if (toolListener != null) {
					toolListener.formEventOccurred(tl);
				}
			}
			
		});
		
		sLogin = new JButton("Login");
		sLogin.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ToolClicks tl = new ToolClicks(this, 2);
				
				if (toolListener != null) {
					toolListener.formEventOccurred(tl);
				}
			}
			
		});
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(sMenu);
		add(sLogin);		
	}
	public Toolbar(Users u) {
		setBorder(BorderFactory.createEtchedBorder());
			
		sMenu = new JButton("Menu");
		sMenu.addActionListener(new ActionListener() {
				
			public void actionPerformed(ActionEvent e) {
				ToolClicks tl = new ToolClicks(this, 1);
				
				if (toolListener != null) {
					toolListener.formEventOccurred(tl);
				}
			}
				
		});
		logout = new JButton("Logout");
		logout.addActionListener(new ActionListener( ) {
			public void actionPerformed(ActionEvent e) {
				ToolClicks tl = new ToolClicks(this, 2);
				
				if (toolListener != null) {
					toolListener.formEventOccurred(tl);
				}
				
			}
			
		});
		userName = new JLabel("Welcome: " + u.getUserName() + "!");
		setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		//////////////// FIRST ITEM ////////////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 0;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(userName, gc);
		//////////////// SECOND ITEM ////////////////////	
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 1;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(sMenu);
		//////////////// THIRD ITEM ////////////////////
		gc.weightx = 1;
		gc.weighty = 0.1;
		
		gc.gridx = 2;
		gc.gridy = 0;
		
		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.insets = new Insets(0, 0, 0, 0);
		add(logout);
		
	}
	public void setToolListener(ToolListener listener) {
		this.toolListener = listener;
	}
}

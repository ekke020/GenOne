package testpack;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import inGameInterface.InGameWindow;


public class WindowFrame extends JFrame {

	private FormPanel formPanel;
	private Toolbar toolbar;
	private LoginFrame loginFrame;
	private Users u;
	private InGameWindow inGameWindow;
	public WindowFrame() {
		super("Main menu");
		u = new Users();
		setLayout(new BorderLayout());
		addLoginToolbar();
		addStartMenu();
		
		setSize(500, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	private void addStartMenu() {
		formPanel = new FormPanel();
		
		add(formPanel, BorderLayout.CENTER);
		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormClicks e) {
				int button = e.getButton();
				
				if (button == 1) {
					wipeFrame();
					addNewGamePanel(u);
					validate();
				}
				else if (button == 2) {
					if (!(u.isOnline())) {
						JOptionPane.showMessageDialog(formPanel, "Please log in before accessing savefiles", "Error", JOptionPane.ERROR_MESSAGE);
					}
					else if (u.isOnline()) {
						wipeFrame();
						addNewSavesFrame(u);
						validate();
					}
				}
			}
			
		});
	}
	private void addNewGamePanel(Users u) {
		formPanel = new FormPanel(u);
		add(formPanel, BorderLayout.CENTER);
		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormClicks e) {
				int button = e.getButton();
				if (u.isOnline() == false) {
					int i = JOptionPane.showInternalConfirmDialog(null, "You wont be able to save\nAre you sure you want to continue?", "", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
					if (i == 0) { // YES
						setVisible(false);
						newInGameWindow();
					}
					else if (i == 1) { // NO
						setEnabled(false);
						wipeFrame();
						addStartMenu();
						validate();
						loginFrame();
					}
				}
				else if (u.isOnline() == true) {
					// Start the game....
				}
			}
		});
	}
	private void addLoginToolbar() {
		toolbar = new Toolbar();
		
		add(toolbar, BorderLayout.NORTH);
		toolbar.setToolListener(new ToolListener() {
			public void formEventOccurred(ToolClicks e) {
				int button = e.getButton();
				if (button == 1) {
					wipeFrame();
					addStartMenu();
					validate();
				}
				else if (button == 2) {
					setEnabled(false);
					loginFrame();	
				}
			}
			
		});
	}
	private void addLoggedInToolbar(Users u) {
		toolbar = new Toolbar(u);
		
		add(toolbar, BorderLayout.NORTH);
		toolbar.setToolListener(new ToolListener() {
			public void formEventOccurred(ToolClicks e) {
				int button = e.getButton();
				if (button == 1) {
					wipeFrame();
					addStartMenu();
					validate();
				}
				else if (button == 2) {
					u.setOnline(false);
					updateToolbar();
					addLoginToolbar();
					wipeFrame();
					addStartMenu();
					validate();
				}
			}
			
		});
	}
	private void loginFrame() {
		
		loginFrame = new LoginFrame();
		
		loginFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
    		 setEnabled(true);
                
          }        
            public void windowClosed(WindowEvent windowEvent) {	
            	try {
                	for (int i = 0; i <= loginFrame.getUserData().size(); i++) {
                		System.out.println(loginFrame.getUserData().get(i).isOnline());
                		if (loginFrame.getUserData().get(i).isOnline() == true) {
                			u = loginFrame.getUserData().get(i);
                			break;
                		}
                	}
            	} catch (IndexOutOfBoundsException i){
            		System.out.println("Out of bounds");
            	}

            	if (u.isOnline() == true) {
                	updateToolbar();
                	addLoggedInToolbar(u);
                	validate();
            	}
            	setEnabled(true);
            }
       });
		addWindowListener(new WindowAdapter() {
			@Override
            public void windowDeactivated(WindowEvent e) {
                loginFrame.setAlwaysOnTop(false);
            }
            @Override
            public void windowActivated(WindowEvent e) {
                loginFrame.setAlwaysOnTop(true);
            }
		});
	}

	private void addNewSavesFrame(Users u) {
		
	formPanel = new FormPanel(u, 1);
		
		add(formPanel, BorderLayout.CENTER);
		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormClicks e) {
				int button = e.getButton();
				
				if (button == 1) {
				
				}
				else if (button == 2) {
						
				}
				else if (button == 3) {
					
				}
			}
			
		});
	}
	private void newInGameWindow() {
		inGameWindow = new InGameWindow();
		
		inGameWindow.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent windowEvent) {
				wipeFrame();
				addStartMenu();
				validate();
				setVisible(true);
			}
			public void windowClosed(WindowEvent windowEvent) {
				
			}
		});
        // INTE KLAR	
	}
	private void wipeFrame() {
		 getContentPane().remove(formPanel);
		 repaint();
	}
	private void updateToolbar() {
		 getContentPane().remove(toolbar);
		 repaint();
	}

}

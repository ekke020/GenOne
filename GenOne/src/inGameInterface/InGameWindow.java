package inGameInterface;



import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;


import javax.swing.BorderFactory;


import javax.swing.JFrame;

import javax.swing.JLayeredPane;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import inGamePlayer.Player;
import music.Sound;
import pokemon.Bulbasaur;
import pokemon.Charmander;
import pokemon.Monsters;
import pokemon.Rattata;
import pokemon.Squirtle;


public class InGameWindow extends JFrame {
	
	private BattleElements battlePanel;
	private JLayeredPane lpane;
	private JLayeredPane lpaneTwo;
	private JLayeredPane bottomLayer;
	private PlayerMainMenuUI playerMainMenuUI;
	private TextUI textUI;
	private PlayerAttackMenuUI playerAttackMenuUI;
	private Tooltip tooltip;
	private Sound sound;
	private Player p1;
	private Monsters m1;
	private CombatVsWild cvw;
	private BattleTeamMenu battleTeamMenu;
	private StatsWindow statsWindow;
	private StatsWindow bottomStatsWindow;
	private StatsWindow statsWindowTwo;
	private Inventory inventory;
	private Inventory inventoryBag;
	
	private ActionListener listener;
	private Timer timer;
	
	private int pa;
	private boolean startBattle = false;
	private boolean fleeBattle = false;
	private boolean turn = false;
	private String filepath = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Version 1.0 Folder" + File.separator + "Game assets" + File.separator + "music" + File.separator + "24(BattleVSWildP2).wav";
	/*private String filepath2 = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Version 1.0 Folder" + File.separator + "Game assets" + File.separator + "music" + File.separator + "24(BattleVSWildP2).wav";
	inGameMusic = new InGameMusic();
	inGameMusic.playLoopedMusic(filepath, filepath2); */

	public InGameWindow() {
		super("Placeholder");
		p1 = new Player();
		m1 = generateRandomMonster();
		
		setSize(500, 500);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		for (int i = 0; i < 6; i ++)
			p1.addTeam(generateAndAddTest());
		
		

		addBottomLayer();
		addBattlePanel();
		addLayerdPane();
		//sound = new Sound(filepath);
		//sound.play();
	}
	private void addBottomLayer() {
		bottomLayer = new JLayeredPane();
		bottomLayer.setBounds(0, 0, 500, 500); 
		bottomLayer.setBackground(Color.white);
		bottomLayer.setOpaque(true);
		add(bottomLayer);
		
	}
	private void addBattlePanel() {
		lpaneTwo = new JLayeredPane();
		lpaneTwo.setBounds(0, 0, 500, 330); 
		lpaneTwo.setOpaque(true);
		bottomLayer.add(lpaneTwo);
		battlePanel = new BattleElements(p1, m1);
		battlePanel.setBounds(0, 0, 500, 330);
		lpaneTwo.add(battlePanel, JLayeredPane.DEFAULT_LAYER);

	}
	private void addLayerdPane() {
		lpane = new JLayeredPane();
		lpane.setBounds(0, 330, 500, 147); 
		lpane.setOpaque(true);
		bottomLayer.add(lpane);
		addBattleTextUI();
		//addBattleMainMenuUI();
	}
	private void addBattleMainMenuUI() {
		playerMainMenuUI = new PlayerMainMenuUI();
		//playerUI.setBounds(0,330, 500, 147);
		playerMainMenuUI.setBounds(320, 15, 146, 112);
		playerMainMenuUI.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY), BorderFactory.createBevelBorder(BevelBorder.LOWERED)));
		
		lpane.add(playerMainMenuUI, 1, 0);
		playerMainMenuUI.setInGameListener(new InGameListener() {

			public void formEventOccurred(InGameClicks e) {
				int button = e.getButton();
				
				if (button == 1) {
					lpane.remove(0);
					lpane.revalidate();
					lpane.repaint();
					addBattleAttackMenu();
				}
				if (button == 2) {
					lpane.remove(0);
					lpane.revalidate();
					lpane.repaint();
					addBattleTeamMenu();
				}
				if (button == 3) {
					lpane.remove(0);
					lpane.revalidate();
					lpane.repaint();
					addInventory();
				}
				if (button == 4) {
					/*lpane.remove(0);
					lpane.revalidate();
					lpane.repaint();*/
					
				}
			}
		});
	}
	private void addBattleTextUI() {
		textUI = new TextUI();
		cvw = new CombatVsWild();
		textUI.setBounds(10, 0, 480, 140);
		textUI.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2, true));
		textUI.setNewText("Wild  " + m1.getName() + "\n\nappeared!", 0, true);
		startBattle = true;
		textUI.setInGameMouseClickListener(new InGameMouseClickListener() {

			@Override
			public void formEventOccurred(InGameMouseClick c) {
				if (textUI.isCont() == true) {
					textUI.revalidateText();
					textUI.setCont(false);
					next();
					}
				
					
			}
			
			
	});
		lpane.add(textUI, 0, -1);
	}
	
	private void addBattleTooltip(int x) {
		tooltip = new Tooltip(p1, x);
		tooltip.setBounds(148, 15, 90, 45);
		tooltip.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY), BorderFactory.createBevelBorder(BevelBorder.LOWERED)));
		lpane.add(tooltip, 1, 1);
	}
	private void addBattleAttackMenu() {
		playerAttackMenuUI = new PlayerAttackMenuUI(p1);
		playerAttackMenuUI.setBounds(240, 15, 226, 112); // 320, 15, 146, 112
		playerAttackMenuUI.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY), BorderFactory.createBevelBorder(BevelBorder.LOWERED)));
		lpane.add(playerAttackMenuUI, 1, 0);
		playerAttackMenuUI.setInGameListener(new InGameListener() {

			public void formEventOccurred(InGameClicks e) {
				int button = e.getButton();
				
				if (button == 1) {
					lpane.remove(1);
					lpane.remove(0);
					lpane.revalidate();
					lpane.repaint();
					turn = true;
					pa = 0;
					cvw.setButton(pa);
					next();
					//textUI.setNewText(p1.getFirstActiveTeam().getName() + "\n\nused  " + p1.getFirstActiveTeam().getMoves(0).getName() + "!", 0, false);
				}
				if (button == 2) {
					lpane.remove(1);
					lpane.remove(0);
					lpane.revalidate();
					lpane.repaint();
					turn = true;
					pa = 1;
					cvw.setButton(pa);
					next();
					//textUI.setNewText(p1.getFirstActiveTeam().getName() + "\n\nused  " + p1.getFirstActiveTeam().getMoves(1).getName() + "!", 0, false);
				}
				if (button == 3) {
					lpane.remove(1);
					lpane.remove(0);
					lpane.revalidate();
					lpane.repaint();
					turn = true;
					pa = 2;
					cvw.setButton(pa);
					next();
					//textUI.setNewText(p1.getFirstActiveTeam().getName() + "\n\nused  " + p1.getFirstActiveTeam().getMoves(2).getName() + "!", 0, false);
				}
				if (button == 4) {
					lpane.remove(1);
					lpane.remove(0);
					lpane.revalidate();
					lpane.repaint();
					turn = true;
					pa = 3;
					cvw.setButton(pa);
					next();
					//textUI.setNewText(p1.getFirstActiveTeam().getName() + "\n\nused  " + p1.getFirstActiveTeam().getMoves(3).getName() + "!", 0, false);
				}
				if (button == 5) {
					lpane.remove(0);
					lpane.revalidate();
					lpane.repaint();
					addBattleMainMenuUI();
				}
			}
		});
		playerAttackMenuUI.setInGameTooltipListener(new InGameTooltipListener() {

			public void formEventOccurred(InGameTooltip t) {
				int tooltip = t.getTooltip();
				if (tooltip == 1) {
					lpane.revalidate();
					lpane.repaint();
					addBattleTooltip(0);
				}
				if (tooltip == 2) {
					lpane.revalidate();
					lpane.repaint();
					addBattleTooltip(1);
				}
				if (tooltip == 3) {
					lpane.revalidate();
					lpane.repaint();
					addBattleTooltip(2);
				}
				if (tooltip == 4) {
					lpane.revalidate();
					lpane.repaint();
					addBattleTooltip(3);
				}
				if (tooltip == 5) {
					lpane.remove(1);
					lpane.revalidate();
					lpane.repaint();
				}
			}
			
		});
	}
	private void addBattleTeamMenu() {
		battleTeamMenu = new BattleTeamMenu(p1);
		battleTeamMenu.setBounds(0, 0, 500, 330);
		lpaneTwo.add(battleTeamMenu, 1, 0);
		battleTeamMenu.setInGameListener(new InGameListener() {

			public void formEventOccurred(InGameClicks e) {
				double button = e.getDButton();
				if (button == 0.0 || button == 1.0 || button == 2.0 || button == 3.0 || button == 4.0 || button == 5.0) {
					if (!(p1.getFirstActiveTeam().equals(p1.getActiveTeam((int)button))) && p1.getActiveTeam((int)button).getHP() >= 1) {
						lpane.remove(0);
						lpane.revalidate();
						lpane.repaint();
						textUI.revalidateText();
						lpaneTwo.remove(0);
						lpaneTwo.revalidate();
						lpaneTwo.repaint();
						textUI.setNewText(p1.getFirstActiveTeam().getName() + " enough!\n\nCome back!", 0, false);
						listener = new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								if (!(textUI.getTimer().isRunning())) {
									timer.stop();
									battlePanel.pokemonToPokemon(p1, (int)button, textUI);
									listener = new ActionListener() {

										@Override
										public void actionPerformed(ActionEvent e) {
											if (!(battlePanel.getTimer().isRunning()) && !(textUI.getTimer().isRunning())) {
												timer.stop();
												cvw.onlyAI(p1, m1, textUI, battlePanel);
											}
												
											
										}
							    		
							    	};
									timer = new Timer(1500,listener);
									timer.start();
									
								}
									
								
							}
				    		
				    	};
						timer = new Timer(500,listener);
						timer.start();
					}
					else if (p1.getFirstActiveTeam().equals(p1.getActiveTeam((int)button)) && !(textUI.getTimer().isRunning())){
						textUI.revalidateText();
						textUI.setNewText(p1.getFirstActiveTeam().getName() + " is\n\nalready out!", 0, false);
					}
					else if (p1.getActiveTeam((int)button).getHP() <= 0 && !(textUI.getTimer().isRunning())) {
						textUI.revalidateText();
						textUI.setNewText(p1.getActiveTeam((int)button) + " is\n\nfainted!", 0, false);	
					}
				}
				if (button == 0.1 || button == 1.1 || button == 2.1 || button == 3.1 || button == 4.1 || button == 5.1) {
					lpaneTwo.remove(0);
					lpane.remove(0);
					System.out.println("STATS");
					addStatsWindow(p1.getActiveTeam((int)button));
				}

			}
			
		});
		battleTeamMenu = new BattleTeamMenu(textUI);
		battleTeamMenu.setBounds(397, 74, 70, 54);
		lpane.add(battleTeamMenu, 1, 0);
		battleTeamMenu.setInGameListener(new InGameListener() {

			public void formEventOccurred(InGameClicks e) {
				int button = e.getButton();
				
				if (button == 0) {
					lpane.remove(0);
					lpane.revalidate();
					lpane.repaint();
					textUI.revalidateText();
					lpaneTwo.remove(0);
					lpaneTwo.revalidate();
					lpaneTwo.repaint();
					addBattleMainMenuUI();
				}
			}
			
		});
	}
	private void addStatsWindow(Monsters m1) {
		statsWindow = new StatsWindow(m1);
		statsWindow.setBounds(0, 0, 500, 420);
		bottomLayer.add(statsWindow, 1, 0);
		statsWindowTwo = new StatsWindow(m1, 1);
		statsWindowTwo.setBounds(0, 0, 500, 420);
		bottomStatsWindow = new StatsWindow();
		bottomStatsWindow.setInGameListener(new InGameListener() {

			public void formEventOccurred(InGameClicks e) {
				int button = e.getButton();
				boolean page1 = e.getToggle();
				System.out.println(button);
				System.out.println(page1);
				if (button == 0) {
					bottomLayer.remove(0);
					bottomLayer.remove(0);
					bottomLayer.revalidate();
					bottomLayer.repaint();
					addBattleTeamMenu();
				}
				if (button == 1) {
					if (page1 == false) {
						bottomLayer.remove(statsWindowTwo);
						bottomLayer.revalidate();
						bottomLayer.repaint();
						bottomLayer.add(statsWindow, 1, 0);
					}
				}
				if (button == 2) {
					if (page1 == true) {
						bottomLayer.remove(statsWindow);
						bottomLayer.revalidate();
						bottomLayer.repaint();
						bottomLayer.add(statsWindowTwo, 1, 0);
					}
				}
	
			}
			
		});
		bottomStatsWindow.setBounds(0, 420, 500, 80);
		bottomLayer.add(bottomStatsWindow, 1, 0);
	}
	private void addInventory() {
		
		inventory = new Inventory();
		inventory.setInGameListener(new InGameListener() {

			@Override
			public void formEventOccurred(InGameClicks e) {
				int button = e.getButton();
				if (button == 0) {
					
				}
				if (button == 1) {
					
				}
				if (button == 2) {
					
				}
				
			}
			
		});
		inventory.setBounds(5, 5, 490, 65);
		//inventory.setBorder(BorderFactory.createLineBorder(Color.black));
		inventory.setBorder(null);
		lpaneTwo.add(inventory, JLayeredPane.PALETTE_LAYER);
		
		inventoryBag = new Inventory(p1);
		inventoryBag.setInGameListener(new InGameListener() {

			@Override
			public void formEventOccurred(InGameClicks e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		inventoryBag.setBorder(BorderFactory.createLineBorder(Color.black));
		inventoryBag.setBounds(5, 70, 490, 258);
		lpaneTwo.add(inventoryBag, JLayeredPane.PALETTE_LAYER);
		
		lpaneTwo.revalidate();
		lpaneTwo.repaint();
		
		inventory = new Inventory(textUI);
		inventory.setBounds(397, 74, 70, 54);
		lpane.add(inventory, 1, 0);
		inventory.setInGameListener(new InGameListener() {

			public void formEventOccurred(InGameClicks e) {
				int button = e.getButton();
				
				if (button == 0) {
					lpane.remove(0);
					lpane.revalidate();
					lpane.repaint();
					textUI.revalidateText();
					lpaneTwo.remove(1);
					lpaneTwo.remove(0);
					lpaneTwo.revalidate();
					lpaneTwo.repaint();
					addBattleMainMenuUI();
				}
			}
			
		});
	}
	private Monsters generateAndAddTest() {
		Monsters m1[] = new Monsters[4];	
		m1[0] = new Charmander();
		m1[1] = new Bulbasaur();
		m1[2] = new Squirtle();
		m1[3] = new Rattata();
		int x = (int)(Math.random() * ((1 - 1) + 4));
		return m1[2];
	}
	private Monsters generateRandomMonster() {
		Monsters m1[] = new Monsters[4];	
		m1[0] = new Charmander();
		m1[1] = new Bulbasaur();
		m1[2] = new Squirtle();
		m1[3] = new Rattata();
		int x = (int)(Math.random() * ((1 - 1) + 4));
		return m1[3];
	}
	private void next() {
		//cvw = new CombatVsWild(pa);
		if (startBattle == true) {
			startBattle = false;
			p1.getFirstActiveTeam().xpEnable();
			battlePanel.playerToPokemon(p1, m1);
			textUI.setNewText("Go!  " + p1.getFirstActiveTeam().getName() + "!", 0, false);
			delay(1500, 0);	        
		}
		if (fleeBattle == true) {
			fleeBattle = false;
			boolean flee = cvw.escape(p1, m1);
			if (flee == true) {
				cvw.disableXP(p1);
				p1.getFirstActiveTeam().removeStageModifier();
				System.exit(EXIT_ON_CLOSE);
			}
			else if (flee == false) {
				// add enemy turn.
			}
			

		}
		if (turn == true) {
			turn = false;
			cvw.playerAndAI(p1, m1, textUI, battlePanel);		
		}
		if (cvw.isTurnOver() == true) {
			cvw.setTurnOver(false);
			//textUI.setCont(false);
			System.out.println("Over!");
			addBattleMainMenuUI();
		}
		if (cvw.isBattleOver() == true) {
			p1.getFirstActiveTeam().removeStageModifier();
			if (cvw.isBattleWon() == true) {
			m1.xpGain(p1, m1);
			// add end of battle interface.
			}
			else if (cvw.isBattleWon() == false) {
				textUI.revalidateText();
				textUI.setNewText(p1.getName() + " blacked\n\nout!", 0, true);
			}
		}
	}
	private void delay(int x, int y) {
		if (y == 0) {
			listener = new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               
	                validate();
	                repaint();
	                if (!(textUI.getTimer().isRunning())) {
	                    timer.stop();
	                    textUI.revalidateText();
	                    addBattleMainMenuUI();
	                }

	            }
	        };
			timer = new Timer(x,listener);
	        timer.start();
		}
	}
}
class InGameFont {
	
	private Font gameFont;
	
	InGameFont(Font f, int x) {
		this.gameFont = f;
		gameFont(x, gameFont);
	}
	public Font getFont() {
		return this.gameFont;
	}
	private void gameFont(int x, Font f) {
		String userDirectory = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Version 1.0 Folder" + File.separator + "Game assets";
		File custom = new File(userDirectory);
		String absoluteP = custom.getAbsolutePath();
		String fontFile = absoluteP +  "/PKMN RBYGSC.ttf";
		if (x == 0) {
			try {
				gameFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(fontFile))).deriveFont(Font.PLAIN, 20);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if (x == 1) {
			try {
				gameFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(fontFile))).deriveFont(Font.BOLD, 12);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if (x == 2) {
			try {
				gameFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(fontFile))).deriveFont(Font.BOLD, 10);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if (x == 3) {
			try {
				gameFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(fontFile))).deriveFont(Font.PLAIN, 27);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if (x == 4) {
			try {
				gameFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(fontFile))).deriveFont(Font.BOLD, 8);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if (x == 5) {
			try {
				gameFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(fontFile))).deriveFont(Font.BOLD, 20);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else if (x == 6) {
			try {
				gameFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(fontFile))).deriveFont(Font.PLAIN, 10);
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}



/* 
 *
 * 
 **/


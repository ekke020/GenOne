package testpack;
import javax.swing.SwingUtilities;

import inGameInterface.InGameWindow;

public class App {


	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new InGameWindow();
			}
		});

	}

}

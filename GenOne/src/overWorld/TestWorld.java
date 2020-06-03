package overWorld;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class TestWorld extends JPanel implements ActionListener {

	private final Timer timer = new Timer(1, this);
	private PlayerCharacter p1;
	
	
	public TestWorld() {
		setLayout(null);
		p1 = new PlayerCharacter();
		p1.setBounds(222, 222, 64, 64);
		add(p1);
		timer.start();
	}
	public void actionPerformed(ActionEvent e) {
		p1.updateSprite();
	}
	
	public PlayerCharacter getP1() {
		return p1;
	}
	
}

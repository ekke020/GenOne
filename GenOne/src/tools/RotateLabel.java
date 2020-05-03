package tools;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class RotateLabel extends JLabel {

	private int degree;
	private ImageIcon rIcon;
	public RotateLabel(ImageIcon icon, int degree) {

		super(icon);
		this.degree = degree;
		rIcon = icon;
	}

	
	public ImageIcon getRIcon() {
		return this.rIcon;
	}
	@Override
	public void paintComponent(Graphics g) {

		Graphics2D gx = (Graphics2D) g;
		gx.rotate(Math.toRadians(this.degree),rIcon.getIconWidth()/2,rIcon.getIconHeight()/2);
		super.paintComponent(g);
		
	}
}
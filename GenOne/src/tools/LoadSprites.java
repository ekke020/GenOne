package tools;

import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

import pokemon.Monsters;

public class LoadSprites {

	
	public ImageIcon loadFrontSprite(Monsters m1) {
		String userDirectory = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Version 1.0 Folder" + File.separator + "Game assets" + File.separator + "Sprites";
		File custom = new File(userDirectory);
		String absoluteP = custom.getAbsolutePath();
		String imgFile = absoluteP +  File.separator + m1.getID();
		ImageIcon icon = new ImageIcon(imgFile);
		Image image = icon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
		icon = new ImageIcon(newimg);
		
        return icon;
	}
}

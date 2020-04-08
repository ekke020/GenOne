package testpack;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveFile implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private String userDirectory = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Version 1.0 Folder" + File.separator + "Data";
	private File custom = new File(userDirectory);
	private String absoluteP = custom.getAbsolutePath();
	private String userFile = absoluteP +  "/users.ser";
	
	public SaveFile() {
		if (custom.exists()) {
		    System.out.println(custom + ": already exists");
		} else if (custom.mkdirs()) {
		    System.out.println(custom + ": was created");
		} else {
		    System.out.println(custom + ": was not created");
		}	
		
	}
	public void save(ArrayList<Users> u) {
		try {
			FileOutputStream fileOut = new FileOutputStream(userFile);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(u);
			out.close();
	        fileOut.close();
	        
      } catch (IOException e) {
         e.printStackTrace();
      }
	}	
	public ArrayList<Users> load() {
		ArrayList<Users> u = new ArrayList<Users>();
	      try {
	         FileInputStream fileIn = new FileInputStream(userFile);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         u = (ArrayList<Users>) in.readObject();
	         in.close();
	         fileIn.close();
	         return u;
	      } catch (IOException e) {
	    	 System.out.println("File does not exist");
	   
	      } catch (ClassNotFoundException c) {
	         System.out.println("File does not exist");
	         c.printStackTrace();  
	      }
	      return u;
	}

}

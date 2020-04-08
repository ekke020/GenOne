package testpack;

import inGamePlayer.Player;

public class Users implements java.io.Serializable{

	private static final long serialVersionUID = 9143322569592345943L;
	private Player[] saves = new Player[3]; // Holds up to 3 savefiles for the game.
	

	private String userName;
	private String userPassword;
	private String userLevel;
	private boolean online;
	
	public Users() {
		
	}
	public Users(String userName, String userPassword) {
		this.userName = userName;
		this.userPassword = userPassword;
	}
	
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}	
	public String getUserName() {
		return this.userName;
	}
	public Player getSaves(int x) {
		try {
			return saves[x];
		}
		catch(NullPointerException e) {
			return null;
		}
			
		
	}
	public void setSaves(Player[] saves) {
		this.saves = saves;
	}

}
 /*SKRIV OM ALLT I PROGRAMMET SÅ ATT DET GÅR ATT SPARA HUR MÅNGA ANVÄNDA SOM HELST!
  * JUST NU FUNKAR DET INTE SOM DET SKA....... BEHÖVER UPPDATERA SÅ ALL FUNKAR. */
  
/*  DEAD CODE
 * try {
 *
			for (int i = 0; i <= sf.getUserData().size(); i++) {
				System.out.println(sf.getUserData().get(i).userName);
				
			}
		} catch (IndexOutOfBoundsException i){
			System.out.println("out of bounds");
		}
*/
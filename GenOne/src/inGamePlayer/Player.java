package inGamePlayer;

import java.util.ArrayList;

import pokemon.Monsters;

public class Player implements java.io.Serializable{

	private static final long serialVersionUID = 9210013657798378622L;
	
	private Monsters[] activeTeam = new Monsters[6]; // En array av Monsters klassen som kan hålla 6 av spelarens pokemon.
	private Monsters[] temp = new Monsters[1]; // en temporär array av Monsters klassen som används för att byta plats på lagmeddlemmar hos spelaren.
	private ArrayList<Monsters> team = new ArrayList<Monsters>(); // En lista som håller alla spelarens pokemon.
	
	private int money;
	private String name = "Red";
	
	public Player() {
		
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Monsters getFirstActiveTeam() {
		return activeTeam[0];
	} // returnerar spelarens första pokemon.
	public Monsters getActiveTeam(int x) {
		return activeTeam[x];
	} // Returnerar en av spelarens aktiva lagmeddlemmar.

	public boolean isTeamAlive() {
		int x = 6;
		int y = 0;
		for (int i = 1; i <= 5; i++) {
			if (getActiveTeam(i) == null) {
				x--;
				}
			} // kollar hur många spelaren har i sitt lag.
		x--;
	
		for (;x >= 0; x--) {
			if (getActiveTeam(x).getHP() >= 1) {
				y++;
			}	
		}
		if (y >= 1)
			return true;
		
		return false;
	}
	public void addTeam(Monsters m1) {
		if (activeTeam[0] == null) 
			activeTeam[0] = m1;	
		else if (activeTeam[1] == null)
			activeTeam[1] = m1;
		else if (activeTeam[2] == null)
			activeTeam[2] = m1;
		else if (activeTeam[3] == null)
			activeTeam[3] = m1;
		else if (activeTeam[4] == null)
			activeTeam[4] = m1;
		else if (activeTeam[5] == null)
			activeTeam[5] = m1;	
		else {
			team.add(m1);
			//System.out.println("Your party was full, " + m1.getName() + " was transferred to storage.");
		}
	} // lägger till monster i activeteam, ifall ingen plats är ledig läggs den till i team.
	
	public void swap(int i, int j) {
		this.temp[0] = this.activeTeam[i]; 
		this.activeTeam[i] = this.activeTeam[j];
		this.activeTeam[j] = this.temp[0];
	} // Byter plats på 2 pokemon i spelarens lag.
}

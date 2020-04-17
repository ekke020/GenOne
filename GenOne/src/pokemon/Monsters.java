package pokemon;


//import java.util.Arrays;
//import java.util.List;
import java.util.UUID;

import javax.swing.Timer;

import inGamePlayer.Player;
import moves.Moves;
import pokemon.Monsters;
public class Monsters implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Moves moveSet[]  = new Moves[4]; // Håller fyra object av Moves som används till attacker.
	protected int[] lvl = new int[100]; // Håller 100 olika levels.
	protected int maxHp = 0; // Håller pokemonens maximala HP.
	protected int hp = 0; // Håller det aktuella HPt.
	protected int attack = 0; // Håller attackvärdet.
	protected int defense = 0; // Håller defensvärdet.
	protected int special = 0; // Håller specialvärdet.
	protected int speed = 0; // Håller speedvärdet.
	protected int level = 0; // håller pokemonens aktuella level.
	protected int xp = 0; // Håller pokemonens totala XP.
	protected int xpYield = 0; // Håller pokemonens XP värde.
	protected int catchRate = 0; // Avgör hur lätt/svårt det är att fånga.
	
	protected int a = 0; // Attack IV
	protected int d = 0; // Defense IV
	protected int s = 0; // Speed IV
	protected int h = 0; // Health IV
	protected int ss = 0; // Special IV
	
	protected int bAttack = 0; // Attack bas
	protected int bDefense = 0; // Defense bas
	protected int bSpeed = 0; // Speed bas
	protected int bSpecial = 0; // Special bas
	protected int bMaxHP = 0; // HP bas
	
	protected int attackStage = 0;
	
	protected int cAttack = 0; // Totala attackvärdet bas+IV.
	protected int cDefense = 0; // Totala defensevärdet bas+IV.
	protected int cSpeed = 0; // Totala speedvärdet bas+IV.
	protected int cSpecial = 0; // // Totala HPvärdet bas+IV.
	
	protected String randomIDNo = null;
	protected String name = null; // Håller namnet.
	protected String iD = null; // Håller ID.
	protected String menuID = null; // Håller menyID;
	protected String type = null; // Håller pokemonens typ.
	protected String typeTwo = null;
	protected String dexNumber = null;
	
	boolean xpEligible = false; // Säger ifall den är berättigad till XP.
	
	public String getRandomIDNo() {
		return this.randomIDNo;
	}
	public String getDexNumber() {
		return this.dexNumber;
	}
	public String getMenuID() {
		return this.menuID;
	}
	public String getID() {
		return this.iD;
	}
	public String getType() {
		return this.type;
	} // Returnerar typen.
	public String getTypeTwo() {
		return this.typeTwo;
	}
	public int getEXPToLVL(int i) {
		return this.lvl[i];
	}
	public int getCatchrate() {
		return this.catchRate;
	} // Returnerar catchRate.
	public void xpEnable() {
		this.xpEligible = true;
	} // Sätter XP rättigheten till true.
	public void xpDisable() {
		this.xpEligible = false;
	} // Stänger av XP rättigheten.
	public boolean getxpEligible() {
		return this.xpEligible;
	} // Returnerar XPrättigheten
	public void xpIncrease(int xp) {
		this.xp += xp;
	} // Ökar XPn
	
	protected void xpToLevel() {
		int y = 1;
		for(int i = 0; i < this.lvl.length; i++) {
			this.lvl[i] = (int)Math.pow(y, 3);
			y++;
		}		
	} // Initierar lvl fältet och sätter hur mycket xp som krävs för att man ska komma till nästa nivå.
	public void xpGain(Player p1, Monsters m1) {
		int xp = (m1.getXPYield() * m1.getLevel()) / 7;
		int p = 5;
		int y = 0;
		
		for (int i = 1; i <= 5; i++) {
			if (p1.getActiveTeam(i) != null) {
				if (p1.getActiveTeam(i).getxpEligible() == true)
					y++;
				else 
					p--;
			}
			else 
				p--;
		} // Går igenom spelarens lag och kollar ifall det finns någon pokemon och ifall den är berättigad till XP.
		y++;
		// öka y så att vi delar på mängden meddlemar i spelarens grupp.
		xp = xp / y;
		// dela xp på mängden meddlemar som deltog i striden.
		for (; p >= 0; p--) {
			if (p1.getActiveTeam(p) != null) {
				if (p1.getActiveTeam(p).getxpEligible() == true) {
					p1.getActiveTeam(p).xpIncrease(xp);
					System.out.println(p1.getActiveTeam(p).getName() + " gained " + xp + " EXP points ");
					this.levelUp(p1, p);
				}
			} // Ökar spelarens lags XP och kollar ifall dem ska levla.
		}
		
	} // Ökar spelarens pokemons xp beroende på vilka som deltagit och överlevt.
	public int getXPYield() {
		return this.xpYield;
	} // Returnerar xpYield
	public int getXP() {
		return this.xp;
	} // Returnerar hur mycket xp som pokemonen har.
	private void levelUp(Player p1, int p) {
		boolean exp = true;
		while (exp == true) {
			if (p1.getActiveTeam(p).xp >= lvl[p1.getActiveTeam(p).getLevel()]) {
				p1.getActiveTeam(p).level ++;
				System.out.println(p1.getActiveTeam(p).getName() + " grew to level " + p1.getActiveTeam(p).getLevel());
			
				p1.getActiveTeam(p).cAttack = p1.getActiveTeam(p).attack =  (((p1.getActiveTeam(p).bAttack * 2 + p1.getActiveTeam(p).a) * p1.getActiveTeam(p).level) / 100 ) + 5;
				p1.getActiveTeam(p).cDefense = p1.getActiveTeam(p).defense = (((p1.getActiveTeam(p).bDefense * 2 + p1.getActiveTeam(p).d) * p1.getActiveTeam(p).level) / 100 ) + 5;
				p1.getActiveTeam(p).maxHp = (((p1.getActiveTeam(p).bMaxHP * 2 + p1.getActiveTeam(p).h) * p1.getActiveTeam(p).level) / 100 ) + 10 + p1.getActiveTeam(p).level;
				p1.getActiveTeam(p).cSpeed = p1.getActiveTeam(p).speed = (((p1.getActiveTeam(p).bSpeed * 2 + p1.getActiveTeam(p).s) * p1.getActiveTeam(p).level) / 100 ) + 5;
				p1.getActiveTeam(p).cSpecial = p1.getActiveTeam(p).special = (((p1.getActiveTeam(p).bSpecial * 2 + p1.getActiveTeam(p).s) * p1.getActiveTeam(p).level) / 100 ) + 5;
				
			}
			else 
				exp = false;
		}

	} // Kollar ifall Spelarens pokemon ska levla upp eller inte och ökar dess stats ifall dem gör det.
	public int getMaxHP() {
		return this.maxHp;
	} // Returnerar maxHP.
	public int getHP() {
		return this.hp;
	} // Returnerar det aktuella hpt.
	public void setHP(int x) {
		this.hp -= x;
	} // Sätter det aktuella hpt.
	public void hpZero() {
		this.hp = 0;
	} // Sätter det aktuella hpt till 0.
	public void healHP() {
		this.hp = this.maxHp;
	}
	public void potion(int x) {
		this.hp += x;
	}
	public int getAttack() {
		return this.attack;
	} // Returnerar attack stats
	public void removeStageModifier() {
		this.attackStage = 0;
	}
	public boolean setAttackStage(int x) {
		System.out.println(this.attackStage);
		//int y = this.attack / x;
		//this.attack -= y;
		this.attackStage += x;
		System.out.println(this.attackStage);
		if (this.attackStage >= 6) {
			this.attackStage = 6;
			System.out.println(this.attackStage);
			return false;
		}	
		if (this.attackStage <= -6) {
			this.attackStage = -6;
			System.out.println(this.attackStage);
			return false;
		}
		return true;
		
	} // sets the attackStage
	public double getAttackStageModifier() {
		double[] modifier = {0.25, 0.28, 0.33, 0.40, 0.50, 0.66, 1.0, 1.5, 2.0, 2.5, 3.0, 3.5, 4.0};
		int mod = -1;
		for (int i = -6; i <= 6; i++) {
			mod++;
			if (this.attackStage == i)
				return modifier[mod];
		}	
		return 0;
	}
	public int getDefense() {
		return this.defense;
	}
	private void setDefense(int x) {
		int y = this.defense / x;
		this.defense -= y;
		if (this.defense <= 2)
			this.defense = 2;
	} // Delar defense värdet på x och tar bort värdet från defense.
	public int getSpecial() {
		return this.special;
	} // Returnerar special värdet.
	public int getSpeed() {
		return this.speed;
	} // Returnerar speed värdet.
	private void setSpeed(int x) {
		int y = this.speed / x;
		this.speed -= y;
		if (this.speed <= 2)
			this.speed = 2;
	} // Delar speed värdet på x och tar bort värdet från speed.
	public String getName() {
		return this.name;
	} // Returnerar pokemonens namn. 
	public int getLevel() {
		return this.level;
	} // returnerar pokemonens level.
	protected int levelGenerator() {
		int x = (int)(Math.random() * ((4 - 0) + 1)) + 1;
		return x;
	} // Genererar ett slumpat tal från 1-5.
	protected String idGenerator() {
		String id = "";
		for(int i = 0; i < 5; i++){
           id += (int)((Math.random() * 9));
        }
		return id;
	}
	protected void addMoves(int x, Moves m1) {
		moveSet[x] = m1;
	} // Lägger till en attack till pokemonen.
	public Moves getMoves(int x) {
		return this.moveSet[x];
	} // Returnerar pokemonens attacker.
	public Timer getMovesTimer(int x) {
		return this.moveSet[x].getTimer();
	}
	protected int ivGenerator() {
		int iv = (int)(Math.random() * ((1 - 1) + 32));
		return iv;
	} // Genererar IV mellan 0 och 32 och returnerar värdet.

	public double pDamage(Player p1, Monsters m1, int x) {
		double dmg = 0;
		String effect = effectiveness(m1.getType(), p1.getFirstActiveTeam().getMoves(x).getType()); // Kollar vilken effekt attacken har.
		if (p1.getFirstActiveTeam().getMoves(x).getDamageType().equalsIgnoreCase("Special")) { // Om attacken använder sig av special värdet.
			dmg = ((2 * p1.getFirstActiveTeam().getLevel() / 5 + 2) * p1.getFirstActiveTeam().getMoves(x).getPower() * p1.getFirstActiveTeam().getSpecial() / m1.getSpecial()) / 50 + 2;
		}
		else if (p1.getFirstActiveTeam().getMoves(x).getDamageType().equalsIgnoreCase("Physical")) { // Om attacken använder sig av physical värdet.
			dmg = ((2 * p1.getFirstActiveTeam().getLevel() / 5 + 2) * p1.getFirstActiveTeam().getMoves(x).getPower() * (p1.getFirstActiveTeam().getAttack() * p1.getFirstActiveTeam().getAttackStageModifier()) / m1.getDefense()) / 50 + 2;
		} 
		if (effect.equalsIgnoreCase("super")) {
			dmg *= 2;
		} // Om effect är super dubblas skadan.
		else if (effect.equalsIgnoreCase("weak")) {
			dmg *= 0.5;
		} // om den är weak halveras skadan.
		boolean crit = false;
		int crit1 = (int)(Math.random() * ((1 - 1) + 256));
		if ((p1.getFirstActiveTeam().getSpeed() / 2) > crit1) {
			dmg *= 2;
			crit = true;
		} // ifall det är en crit dubblar vi skadan.
		if (p1.getFirstActiveTeam().getType().equalsIgnoreCase(p1.getFirstActiveTeam().getMoves(x).getType())) {
			dmg *= 1.5;
		} // ifall attacken är av samma typ som användaren ökar vi skadan med 50%.
		if (crit == false) {
			System.out.println("Wild " + m1.getName() + " took " + dmg + " damage.");
			if (effect.equalsIgnoreCase("super"))
				System.out.println("It's super effective!");
			else if (effect.equalsIgnoreCase("weak"))
				System.out.println("It's not very effective...");
		} // berättar för spelaren vad som hände.
		else {
			System.out.println("Wild " + m1.getName() + " took " + dmg + " critical damage");
			if (effect.equalsIgnoreCase("super"))
				System.out.println("It's super effective!");
			else if (effect.equalsIgnoreCase("weak"))
				System.out.println("It's not very effective...");
		} // berättar för spelaren vad som hände.
		//m1.setHP(dmg); // Reducerar motståndarens hp med värdet i dmg.
		return dmg;
	} // Räknar ut spelarens skada och levererar den till motståndaren.
	public boolean phit(Player p1, int x) {
		if ((p1.getFirstActiveTeam().getMoves(x).getAccuracy() - 10) > (int)(Math.random() * ((1 + 1) + 99))) {
			return true;
		}
		return false;
	} // Kollar ifall spelarens attack träffar.
	
	public int attackGenerator(Monsters m1) {
		int x = 10;
		do {
			x = (int)(Math.random() * ((1 - 1) + 4));
		} while (m1.getMoves(x) == null);
		
		return x;
	} // Genererar en slumpad attack för datorns pokemon.
	public int mDamage(Player p1, Monsters m1, int x) {
		int dmg = 0;
		String effect = effectiveness(p1.getFirstActiveTeam().getType(), m1.getMoves(x).getType());
		if (m1.getMoves(x).getDamageType().equalsIgnoreCase("Special")) {
			dmg = ((2 * m1.getLevel() / 5 + 2) * m1.getMoves(x).getPower() * m1.getSpecial() / p1.getFirstActiveTeam().special) / 50 + 2;
		}
		else if (m1.getMoves(x).getDamageType().equalsIgnoreCase("Physical")) {
			dmg = ((2 * m1.getLevel() / 5 + 2) * m1.getMoves(x).getPower() * m1.getAttack() / p1.getFirstActiveTeam().defense) / 50 + 2;
		}

		if (effect.equalsIgnoreCase("super")) {
			dmg *= 2;
		}
		else if (effect.equalsIgnoreCase("weak")) {
			dmg *= 1.5;
		}			
		boolean crit = false;
		int crit1 = (int)(Math.random() * ((1 - 1) + 256));
		if ((m1.getSpeed() / 2) > crit1) {
			dmg *= 2;
			crit = true;
		}
		if (m1.getType().equalsIgnoreCase(m1.getMoves(x).getType())) {
			dmg += dmg * 0.5;
		}
		if (crit == false) {
			System.out.println(p1.getFirstActiveTeam().getName() + " took " + dmg + " damage.");
			if (effect.equalsIgnoreCase("super"))
				System.out.println("It's super effective!");
			else if (effect.equalsIgnoreCase("weak"))
				System.out.println("It's not very effective...");
		}
		else {
			System.out.println(p1.getFirstActiveTeam().getName() + " took " + dmg + " critical damage");
			if (effect.equalsIgnoreCase("super"))
				System.out.println("It's super effective!");
			else if (effect.equalsIgnoreCase("weak"))
				System.out.println("It's not very effective...");
		}		
		return dmg;
	} // en modifierad metod som räknar ut datorns skada på spelaren.
	public boolean mhit(Monsters m1, int x) {
		if ((m1.getMoves(x).getAccuracy() - 10) > (int)(Math.random() * ((1 + 1) + 99))) {
			return true;
		}
		return false;
	} // Kollar ifall datorns attack träffar.
	public String effectiveness(String p, String m) {

		// p är målets typ.
		// m är attackens typ.
		if (p.equalsIgnoreCase("fire")) {
			if (m.equalsIgnoreCase("water"))
				return "super";
			else if (m.equalsIgnoreCase("grass"))
				return "weak";
			else if (m.equalsIgnoreCase("normal"))
				return "normal";
			else if (m.equalsIgnoreCase("fire"))
				return "weak";
		}
		else if (p.equalsIgnoreCase("water")) {
			if (m.equalsIgnoreCase("water"))
				return "weak";
			else if (m.equalsIgnoreCase("grass"))
				return "super";
			else if (m.equalsIgnoreCase("normal"))
				return "normal";
			else if (m.equalsIgnoreCase("fire"))
				return "weak";
		}
		else if (p.equalsIgnoreCase("grass")) {
			if (m.equalsIgnoreCase("water"))
				return "weak";
			else if (m.equalsIgnoreCase("grass"))
				return "weak";
			else if (m.equalsIgnoreCase("normal"))
				return "normal";
			else if (m.equalsIgnoreCase("fire"))
				return "super";
		}
		else if (p.equalsIgnoreCase("normal")) {
			if (m.equalsIgnoreCase("water"))
				return "normal";
			else if (m.equalsIgnoreCase("grass"))
				return "normal";
			else if (m.equalsIgnoreCase("normal"))
				return "normal";
			else if (m.equalsIgnoreCase("fire"))
				return "normal";
		}
		return null;
	} // Kollar vilken effekt attacken kommer ha.
}

/*
 * Monsters klassen var något som till en början bara skulle hålla alla
 * värden till de olika pokemon som finns i spelet. Sen efter att jag skrev 
 * och la in fler metoder i Combat klassen flyttade jag många av dem hit eftersom
 * jag tyckte att dem bättre passade in här. 
 * 
 * Den metod som sticker ut allra mest i den här klassen är nog xpGain metoden
 * mycket pga hur klurigt det var att få den att göra precis det jag ville. 
 * 
 * Många av alla metoderna i klassen har genomgått en hel del förändring
 * under programmets liv. Damage metoderna har skrivits om ett par gånger
 * och en hel del saker har lagts till lite eftersom. 
 */

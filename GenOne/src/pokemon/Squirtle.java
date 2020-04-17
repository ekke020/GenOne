package pokemon;

import moves.Bubble;
import moves.Growl;
import moves.Tackle;

public class Squirtle extends Monsters {

	public Squirtle() {
		this.randomIDNo = idGenerator();
		this.level = levelGenerator();
		this.attack =  ((((this.bAttack = 48) * 2 + (this.a = ivGenerator())) * this.level) / 100 ) + 5;
		this.defense = ((((this.bDefense = 65) * 2 + (this.d = ivGenerator())) * this.level) / 100 ) + 5;
		this.special = ((((this.bSpecial = 50) * 2 + (this.ss = ivGenerator())) * this.level) / 100 ) + 5;
		this.maxHp = ((((this.bMaxHP = 44) * 2 + (this.h = ivGenerator())) * this.level) / 100 ) + 10 + this.level;
		this.hp = 1; //maxHp;
		this.speed = ((((this.bSpeed = 43) * 2 + (this.s = ivGenerator())) * this.level) / 100 ) + 5;
		this.name = "SQUIRTLE";
		this.catchRate = 45;
		
		this.xpYield = 65;
		this.xp = (int)Math.pow(this.level, 3);
		this.xpEligible = false;
		
		this.cAttack = this.attack;
		this.cDefense = this.defense;
		this.cSpeed = this.speed;
		
		this.type = "WATER";
		this.iD = "7.png";
		this.menuID = "MS_Aquatic_I.png";
		this.dexNumber = "007";
		
		this.xpToLevel();
		this.addMoves(0, new Tackle());
		this.addMoves(1, new Growl());
		this.addMoves(2, new Bubble());
	} // total 199
	
}

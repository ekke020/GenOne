package pokemon;

import moves.Ember;
import moves.Growl;
import moves.Tackle;

public class Charmander extends Monsters {
	
	
	public Charmander() {
		this.randomIDNo = idGenerator();
		this.level = levelGenerator();
		this.attack =  ((((this.bAttack = 52) * 2 + (this.a = ivGenerator())) * this.level) / 100 ) + 5;
		this.defense = ((((this.bDefense = 43) * 2 + (this.d = ivGenerator())) * this.level) / 100 ) + 5;
		this.special = ((((this.bSpecial = 50) * 2 + (this.ss = ivGenerator())) * this.level) / 100 ) + 5;
		this.maxHp = ((((this.bMaxHP = 39) * 2 + (this.h = ivGenerator())) * this.level) / 100 ) + 10 + this.level;
		this.hp = maxHp;
		this.speed = ((((this.bSpeed = 65) * 2 + (this.s = ivGenerator())) * this.level) / 100 ) + 5;
		this.name = "CHARMANDER";
		this.catchRate = 45;
		
		this.xpYield = 65;
		this.xp = (int)Math.pow(this.level, 3);
		this.xpEligible = false;
		
		this.cAttack = this.attack;
		this.cDefense = this.defense;
		this.cSpeed = this.speed;
		this.cSpecial = this.special;
		
		this.type = "FIRE";
		this.typeTwo = "FLYING";
		this.iD = "4.png";
		this.menuID = "MS_Rhydon_I.png";
		this.dexNumber = "004";
		
		this.xpToLevel();
		this.addMoves(0, new Tackle());
		this.addMoves(1, new Ember());
		this.addMoves(2, new Growl());

	} // total 199
}

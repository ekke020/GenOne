package pokemon;

import moves.Growl;
import moves.QuickAttack;
import moves.Tackle;

public class Rattata extends Monsters {

	public Rattata() {
		this.randomIDNo = idGenerator();
		this.level = levelGenerator();
		this.attack =  ((((this.bAttack = 56) * 2 + (this.a = ivGenerator())) * this.level) / 100 ) + 5;
		this.defense = ((((this.bDefense = 35) * 2 + (this.d = ivGenerator())) * this.level) / 100 ) + 5;
		this.special = ((((this.bSpecial = 25) * 2 + (this.ss = ivGenerator())) * this.level) / 100 ) + 5;
		this.maxHp = ((((this.bMaxHP = 30) * 2 + (this.h = ivGenerator())) * this.level) / 100 ) + 10 + this.level;
		this.hp = maxHp;
		this.speed = ((((this.bSpeed = 72) * 2 + (this.s = ivGenerator())) * this.level) / 100 ) + 5;
		this.name = "RATTATA";
		this.catchRate = 255;
		
		this.xpYield = 57;
		this.xp = (int)Math.pow(this.level, 3);
		this.xpEligible = false;
		
		this.cAttack = this.attack;
		this.cDefense = this.defense;
		this.cSpeed = this.speed;
		
		this.type = "NORMAL";
		this.iD = "19.png";
		this.menuID = "MS_Quadruped_I.png";
		this.dexNumber = "019";
		
		this.xpToLevel();
		this.addMoves(0, new QuickAttack());
		this.addMoves(1, new Tackle());
	}
}

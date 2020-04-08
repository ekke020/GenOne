package pokemon;

import moves.Growl;
import moves.Tackle;

public class Bulbasaur extends Monsters {

	public Bulbasaur() {
		this.randomIDNo = idGenerator();
		this.level = levelGenerator();
		this.attack = ((((this.bAttack = 49) * 2 + (this.a = ivGenerator())) * this.level) / 100 ) + 5;
		this.defense = ((((this.bDefense = 49) * 2 + (this.d = ivGenerator())) * this.level) / 100 ) + 5;
		this.special = ((((this.bSpecial = 65) * 2 + (this.ss = ivGenerator())) * this.level) / 100 ) + 5;
		this.maxHp = ((((this.bMaxHP = 45) * 2 + (this.h = ivGenerator())) * this.level) / 100 ) + 10 + this.level;
		this.hp = maxHp;
		this.speed = ((((this.bSpeed = 45) * 2 + (this.s = ivGenerator())) * this.level) / 100 ) + 5;
		this.name = "BULBASAUR";
		this.catchRate = 45;
		
		this.xpYield = 64;
		this.xp = (int)Math.pow(this.level, 3);
		this.xpEligible = false;
		
		this.cAttack = this.attack;
		this.cDefense = this.defense;
		this.cSpeed = this.speed;
		this.cSpecial = this.special;
		
		this.attackStage = 0;
		this.type = "GRASS";
		this.iD = "1.png";
		this.menuID = "MS_Plant_I.png";
		this.dexNumber = "001";
		
		this.xpToLevel();
		this.addMoves(0, new Growl());
		this.addMoves(1, new Tackle());
		
	}
}

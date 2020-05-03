package tools;

import java.util.Random;

public class GenerateRandomNum {

	   public int randomNumFromZero(int num) {
		   
		      Random randomGenerator = new Random();
		      int randomInt = randomGenerator.nextInt(num);
		      System.out.println(randomInt);
		      return randomInt;
		   }
	   public int randomShakeNum() {
		   
		      Random randomGenerator = new Random();
		      int randomInt = randomGenerator.nextInt(65536);
		      System.out.println(randomInt);
		      return randomInt;
		   
	   }
}

package phase2;
// phase2
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Battlefield {//extends Application {
	 ArrayList<Fighter> fighter = new ArrayList<Fighter>();
	 private int 		distance;
	 int 				round =1;
	 Random				random = new Random();

	    public 				Battlefield(){
			fighter.add(new Fighter());
			fighter.add(new Fighter());
		}
		public 				Battlefield(Fighter f1, Fighter f2){
			if(fighter.isEmpty()){
				fighter.add(f1);
				fighter.add(f2);
			}
			else{
				fighter.clear();
				fighter.add(f1);
				fighter.add(f2);
			}
		}
		// fight loop tell a fighter will lose
		public int  		fight() {

		// start loop

		int secondFighter = 1;
		int firstFighter = random.nextInt(2); // 0 or 1
		if (firstFighter == 1)
			secondFighter = 0;
		//CHECK IF SHELL IS THERE IF YES DAMGE IT
		if (shellHealth(firstFighter, secondFighter)) {}
		//DAMAGE THE FIGHTER
		else
			fighterHealth(firstFighter, secondFighter);


		if (getDistance() < 14) {} // 0 - 17 any weapon
		else if ((getDistance() < 26) && (fighter.get(firstFighter).weapon[fighter.get(firstFighter).weaponIndix]
				.getWeaponName().equals("Gun"))) {} // 0-17 and Gun
		else {
			fighter.get(firstFighter).setCanNotHit(1);
			increaseHealth(firstFighter, secondFighter);
			round++;
		}
		// end loop
		TimeUnit time = TimeUnit.SECONDS;
//		try {
//			time.sleep(10);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return firstFighter;
		}
		//it will damage (reduce) the fighter health
		public void 		fighterHealth(int f1, int f2) {
			//preparation
			fighter.get(f1).setChanceToDefence();
			fighter.get(f1).setChanceToHit();
			fighter.get(f2).setChanceToDefence();
			fighter.get(f2).setChanceToHit();

			if(fighter.get(f1).getChanceToHit() > fighter.get(f2).getChanceToDefence() && (canHit(f1))){
				int damage = fighter.get(f1).weapon[fighter.get(f1).weaponIndix].hit(fighter.get(f1).getChanceToHit()); //hit (set damage)
				fighter.get(f2).setHp(fighter.get(f2).getHp() - damage );//set f2's hp
				fighter.get(f2).setEnergy(fighter.get(f2).getHp());			//set f2's energy
				fighter.get(f1).setEnergy(fighter.get(f1).getHp());			// set f1's energy

				if(fighter.get(f2).getHp() < 0){
					fighter.get(f2).setHp(0);
					fighter.get(f2).setEnergy(0);
				}
			}
			else if(fighter.get(f1).getChanceToHit() == fighter.get(f2).getChanceToDefence() && canHit(f1)){
				fighter.get(f1).setEnergy(fighter.get(f1).getHp());//reduce f1's energy
				fighter.get(f2).setEnergy(fighter.get(f2).getHp());//reduce f2's energy
			}
			else if(fighter.get(f1).getChanceToHit() < fighter.get(f2).getChanceToDefence() && canHit(f1))
				fighter.get(f1).setEnergy(fighter.get(f1).getHp());//reduce f1's energy
			}
		//it will damage (reduce) the shell health
		public boolean 		shellHealth(int f1, int f2) {// true means there is a shell
			if(fighter.get(f2).shell[fighter.get(f2).shellIndix].getHp()<=0){
				return false;
			}
			// preparation
			fighter.get(f1).setChanceToDefence();
			fighter.get(f1).setChanceToHit();
			fighter.get(f2).setChanceToDefence();
			fighter.get(f2).setChanceToHit();

			if (fighter.get(f1).getChanceToHit() >= fighter.get(f2).getChanceToDefence() && (canHit(f1))) {
				int damage = fighter.get(f1).weapon[fighter.get(f1).weaponIndix].hit(fighter.get(f1).getChanceToHit()); /** hit (set damage) */
					fighter.get(f2).shell[fighter.get(f2).shellIndix].deter(fighter.get(f2).shell[fighter.get(f2).shellIndix].getHp() - damage); /** set f2 shell's hp */ // f2

					fighter.get(f2).setEnergy(fighter.get(f2).getHp()); /** set f2's energy */
					fighter.get(f1).setEnergy(fighter.get(f1).getHp()); /** set f1's energy */

					if (fighter.get(f2).shell[fighter.get(f2).shellIndix].getHp() <= 0) {
						fighter.get(f2).shell[fighter.get(f2).shellIndix].deter(0);
						return false;
					}
					return true;
			} else if (fighter.get(f1).getChanceToHit() < fighter.get(f2).getChanceToDefence() && canHit(f1)) {
				fighter.get(f1).setEnergy(fighter.get(f1).getHp());/** reduce f1's energy */

				if (fighter.get(f2).shell[fighter.get(f2).shellIndix].getHp() <= 0) {
					fighter.get(f2).shell[fighter.get(f2).shellIndix].deter(0);
					return false;
				}
			}
			return true;
		}
		//chek if a fighter can hit
		public boolean 		canHit(int f){
			/** Sword can hit from 0 - 13 */
			/**Gun    can hit from 0 - 25 */
			setDistance();
			if(getDistance()<14)//0 - 13 any weapon
				return true;
			else if((getDistance()<26) && (fighter.get(f).weapon[fighter.get(f).weaponIndix].getWeaponName().equals("Gun"))) //0-25 and Gun
				return true;
			else
				return false;
		}
		public int 			getDistance() {
			return distance;
		}
		/**distance between two fighters*/
		public void 		setDistance() {
			double X		 = Math.pow(fighter.get(0).getPosition().getX() - fighter.get(1).getPosition().getX(),2);//delta x
			double Y 		 = Math.pow(fighter.get(0).getPosition().getY() - fighter.get(1).getPosition().getY(),2);//delta y
			this.distance 	 = (int)Math.sqrt(X+Y);
			//change fighters position
			fighter.get(0).setPosition();
			fighter.get(1).setPosition();
		}
		//print if a fighter got power drink
		public void 		healthIsIncreased(int f){
		//	System.out.println("\n\n\n\t\t\t\t******ID \""+fighter.get(f).getFighterID()+"\" health is increased to : "+fighter.get(f).getHp() +" ****************************\n\n\n");

		}
		//printing summery of the fight
		public void 		fightSummery(Fighter  f1, Fighter f2, int round){
			System.out.print("round "+ round+ " start! \n\ndistance: "+distance+"\n");
			System.out.printf("%5s |  \t%5s | \t%10s | \t%10s | \t%7s | \t%5s | \t%10s | \t%10s\n","ID","HP","chance to hit","chance to defence","weapon","shell","shell hp","cannot hit");
			System.out.printf("%4d  |  \t%5d | \t%10d    | \t%14d    | \t%6s  | \t%5s | \t%7d    | \t%7d\n",f1.getFighterID(),f1.getHp(),f1.getChanceToHit(),f1.getChanceToDefence(),f1.weapon[f1.weaponIndix].getWeaponName(),f1.shell[f1.shellIndix].getShellMatrial(),f1.shell[f1.shellIndix].getHp(),f1.getCanNotHit());
			System.out.printf("%4d  |  \t%5d | \t%10d    | \t%14d    | \t%6s  | \t%5s | \t%7d    | \t%7d\n ",f2.getFighterID(),f2.getHp(),f2.getChanceToHit(),f2.getChanceToDefence(),f2.weapon[f2.weaponIndix].getWeaponName(),f2.shell[f2.shellIndix].getShellMatrial(),f2.shell[f2.shellIndix].getHp(),f2.getCanNotHit());
			System.out.println("===========================================================================================================================================");
		}
		//increase health if a fighter found power drink
		public void 		increaseHealth(int  f1,int f2){
			if(fighter.get(f1).powerDrink())
				healthIsIncreased(f1);	//print that his health is increased
			if(fighter.get(f2).powerDrink())
				healthIsIncreased(f2);	//print that his health is increased

		}
}

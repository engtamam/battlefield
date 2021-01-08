package phase2;
import java.util.Random;

public class Fighter implements OlympicPlayer{

	//data filed
	private final int 	fighterID;  			//replace it with ID;
	private static int	copyOfLastId = 0;
	private int 		hp =100; 				//blood
	private int 		energy;			     	// relation with blood, player level
    private int 		chanceToHit ; 			/** relation with fighter leve and energy*/
	private int 		chanceToDefence ; 		/** relation with fighter leve and energy*/
	private _Point		position ;
	private int 		canNotHit =0;			// number of time could not hit
	public final Weapon weapon[] = {new Gun(), new Sward()};
	public final Shell  shell [] = {new WoodShell(), new IronShell()};
	Random random = new Random();  				//random value when needed in methods
	final int  weaponIndix = random.nextInt(2); // 0 or 1 but final
	final int shellIndix  = random.nextInt(2); 	// 0 or 1 but final

	//constructor
	public Fighter(){
		/** adjust with the set methods or by your way ; random as we said in the meeting*/
		copyOfLastId = copyOfLastId + 1;
		fighterID = copyOfLastId;
		position = new _Point();
		position.setX();//0 - 30
		position.setY();//0 - 30
		setEnergy(hp);
	}
	//methods

	public int getEnergy() {
		return energy;
	}
	public void setEnergy(int hp) {
		if(hp ==0)
			this.energy =0;
		else
		this.energy = (hp/7) + 50 - random.nextInt(3); // max energy = 50 + 50 = 100
	}
	public int getHp() {
		if(hp>100)
			hp=100;
		return hp;
	}
	public void setHp(int hp) {
//		if(hp<=0)
//			this.hp = 0;
//		else
		this.hp = hp + random.nextInt(15);
	}
	public int getFighterID() {
		return fighterID;
	}
	public int getChanceToHit() {
		return chanceToHit;
	}
	public void setChanceToHit() {
		chanceToHit		 = (int)(getEnergy()/10) + 100 + random.nextInt(6);
	}
	public int getChanceToDefence() {
		return chanceToDefence;
	}
	public void setChanceToDefence() {
		chanceToDefence	 = (int)(getEnergy()/10) + 100 + random.nextInt(3);
	}
	public _Point getPosition() {
		return position;
	}
	public void setPosition() {
		this.position.setX();
		this.position.setY();
	}
	public String toString() {
		 return String.format("ID: %d hp: %d energy: %d chance to hit: %d , chance to defence: %d, weapon: %s, shell: %s, shell hp: %d"
				 ,fighterID,hp,energy,chanceToHit,chanceToDefence,weapon[weaponIndix].getWeaponName(),shell[shellIndix].getShellMatrial(),shell[shellIndix].getHp());
	}
	//put power drink in some position in the map;
	public boolean powerDrink(){
		/**at x=0,y=0 * at x=3,y=5 * at x=5,y=2| * at x=8,y=4 * at x=10,y=10*/
		/**at x=13,y=0 * at x=5,y=15 * at x=13,y=14 * at x=15,y=18 * at x=20,y=7 * at x=19,y=9 * at x=3,y=22
		 * at x=6,y=19 * at x=22,y=23 * at x=24,y=30 * at x=27,y=8 * at x=6,y=30 * at x=30,y=30 * at x=26,y=17*/
		int max = 11;
		int min = 25;
			if(position.getX()==0 && position.getY()==0 && hp<100){
				setHp(hp + random.nextInt(max)+min);
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			else if(position.getX()==3 && position.getY()==5 && hp<100){
				setHp(hp + random.nextInt(max)+min); //10-25
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			else if(position.getX()==5 && position.getY()==2 && hp<100){
				setHp(hp + random.nextInt(max)+min); //10-25
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			else if(position.getX()==8 && position.getY()==4 && hp<100){
				setHp(hp + random.nextInt(max)+min);
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			else if(position.getX()==10 && position.getY()==10 && hp<100){
				setHp(hp + random.nextInt(max)+min);
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			else if(position.getX()==13 && position.getY()==0 && hp<100){
				setHp(hp + random.nextInt(max)+min);
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			else if(position.getX()==5 && position.getY()==15 && hp<100){
				setHp(hp + random.nextInt(max)+min);
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			else if(position.getX()==13 && position.getY()==14 && hp<100){
				setHp(hp + random.nextInt(max)+min);
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			else if(position.getX()==15 && position.getY()==18 && hp<100){
				setHp(hp + random.nextInt(max)+min);
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			else if(position.getX()==20 && position.getY()==7 && hp<100){
				setHp(hp + random.nextInt(max)+min);
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			else if(position.getX()==19 && position.getY()==9 && hp<100){
				setHp(hp + random.nextInt(max)+min);
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			else if(position.getX()==3 && position.getY()==22 && hp<100){
				setHp(hp + random.nextInt(max)+min);
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			else if(position.getX()==6 && position.getY()==19 && hp<100){
				setHp(hp + random.nextInt(max)+min);
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			else if(position.getX()==22 && position.getY()==23 && hp<100){
				setHp(hp + random.nextInt(max)+min);
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			else if(position.getX()==24 && position.getY()==30 && hp<100){
				setHp(hp + random.nextInt(max)+min);
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			else if(position.getX()==27 && position.getY()==8 && hp<100){
				setHp(hp + random.nextInt(max)+min);
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			else if(position.getX()==6 && position.getY()==30 && hp<100){
				setHp(hp + random.nextInt(max)+min);
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			else if(position.getX()==30 && position.getY()==30 && hp<100){
				setHp(hp + random.nextInt(max)+min);
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			else if(position.getX()==26 && position.getY()==17 && hp<100) {
				setHp(hp + random.nextInt(max)+min);
				if(getHp()>100)
					setHp(100);
				setEnergy(getHp());
				return true;
			}
			return false;



	}

	public int getCanNotHit() {
		return canNotHit;
	}

	public void setCanNotHit(int canNotHit) {
		this.canNotHit += canNotHit;
	}

}

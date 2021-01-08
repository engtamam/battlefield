package phase2;

public abstract class Weapon {
	//data field
	private int  damage;
	private String WeaponName;

    //method
	public abstract int hit(int energy) ;
	public void setWeaponName(String weaponName) {
		WeaponName = weaponName;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(double chance){
		damage = (int)(chance);

	}
	public String getWeaponName() {
		return WeaponName;
	}
}

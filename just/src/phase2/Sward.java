package phase2;

public class Sward extends Weapon {
	public Sward() {
		setWeaponName("Sward");
	}

	@Override
	public int hit(int chance) {
		// 5 to 16 difference damage
		setDamage(chance / 2);
		return getDamage();
	}

}

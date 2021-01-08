package phase2;

public class Gun extends Weapon {
	public Gun() {
		setWeaponName("Gun");
	}

	@Override
	public int hit(int chance) {
		// 5 to 16 difference damage
		setDamage(chance / 5);
		return getDamage();
	}
}

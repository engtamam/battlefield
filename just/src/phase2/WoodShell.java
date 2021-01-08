package phase2;

public class WoodShell extends Shell {

	public WoodShell() {
		super();
		// TODO Auto-generated constructor stub
		setShellMatrial("wood");
	}

	@Override
	public void deter(int newHp) {
		// TODO Auto-generated method stub
		setHp(newHp / 2);

	}

}

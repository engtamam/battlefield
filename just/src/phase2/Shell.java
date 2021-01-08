package phase2;

public abstract class Shell {
	private int hp =100;
	private String shellMatrial ;
	public abstract void  deter(int deter);
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public String getShellMatrial() {
		return shellMatrial;
	}
	public void setShellMatrial(String shellMatrial) {
		this.shellMatrial = shellMatrial;
	}

}

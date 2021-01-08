
package phase2;

import java.util.Random;

public class _Point {
	private  int x;
	private   int y;

	Random r = new Random();
	public 			_Point() {
		/**generate random values foe x and y (positions*/
		x= r.nextInt(31);
		y=r.nextInt(31);
	}

	public void 	setX() {
		this.x =r.nextInt(31);
	}
	public int 		getX() {
		return x;
	}
	public  void 	setY() {
		this.y = r.nextInt(31);
	}
	public  int 	getY() {
		return y;
	}
	public String 	toString() {
		return String.format("position: (%d,%d) ", x,y);
	}
}

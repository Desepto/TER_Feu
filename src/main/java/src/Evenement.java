package src;

/**
 * Décrit l'apparition d'un acteur sur la carte. A compléter.
 * 
 * @author Nicolas
 *
 */
public class Evenement {

	private int X, Y, type;

	public Evenement(int x, int y, int type) {
		X = x;
		Y = y;
		this.type = type;
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public int getType() {
		return type;
	}

}

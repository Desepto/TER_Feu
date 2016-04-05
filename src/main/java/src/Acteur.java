package src;

/**
 * Classe mère de tous les acteurs Un bon acteur doit bien connaître son texte
 * pour bien jouer la comédie !
 *
 * @author Nicolas
 *
 */

public abstract class Acteur {

	private int X, Y; // Ses coordonnées sur la carte.0

	public abstract void agi(Carte maCarte);

	/**
	 * El Constrouctivoush
	 *
	 * @param X
	 * @param Y
	 */
	public Acteur(int X, int Y) {
		this.X = X;
		this.Y = Y;
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public void setX(int x) {
		X = x;
	}

	public void setY(int y) {
		Y = y;
	}

}

package acteurs;

import java.awt.Point;

import main.Carte;

/**
 * Classe mère de tous les acteurs Un bon acteur doit bien connaître son texte
 * pour bien jouer la comédie !
 * 
 * @author Nicolas
 * 
 */

public abstract class Acteur {

	protected int X, Y; // Ses coordonnées sur la carte.0

	/**
	 * Méthode abstraite redéfinie pour tous les acteurs. Pour chaque acteur de
	 * la carte, l'évolueur devra appeler sa méthode agi Pour un pompier, c'est
	 * une tentative d'extinction de feu. Pour un canadaire, le largage de son
	 * réservoir d'eau. Pour de la pluie, l'humidification de la case et ses
	 * voisins. Pour du feu, la carbonisation totale.
	 * 
	 * @param maCarte
	 */
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

	/**
	 * Déplace un acteur.
	 * 
	 * @param X
	 * @param Y
	 */
	public void setActeur(int X, int Y) {

		this.X = X;
		this.Y = Y;
	}

	/**
	 * Déplace un acteur et l'ajoute à la liste des modifications si la case
	 * destination est différente de celle de départ
	 * 
	 * @param p
	 *            La destination
	 * @param c
	 */
	public void setActeur(Point p, Carte c) {

		c.getModifications().add(new Point(this.X, this.Y));

		if (this.X != p.x || this.Y != p.y)
			c.getModifications().add(p);

		this.X = p.x;
		this.Y = p.y;

	}
}

package src;

/**
 * La classe noeud est utilisée dans l'algorithme Astar (A*). Un noeud
 * correspond à un Terrain, avec des attributs supplémentaires nécessaires à A*.
 *
 * @author Nicolas
 */
public class Noeud {

	public Noeud parent;
	public int F, G, H, i, j;
	boolean traversable;

	//////////////////////////
	/* Constructeurs */
	//////////////////////////

	public Noeud() {
		this.F = 0; // Correspond à G + H
		this.G = 0; // Correspond à la distance case départ => case présente en
					// tenant compte du chemin
		this.H = 0; // Correspond à la distance case présente => case arrivée
					// sans tenir compte du chemin.
		this.traversable = false;
	}

	public Noeud(boolean traversable) {
		this();
		this.traversable = traversable;
	}

	public Noeud(int i, int j, boolean traversable) {
		this(traversable);
		this.i = i;
		this.j = j;
	}

	//////////////////////////
	/* Méthodes */
	//////////////////////////

	public void calcul_H(int k, int l) {
		this.H = Math.abs(k - this.i) + Math.abs(l - this.j);
	}

	public void calcul_F() {
		this.F = this.G + this.H;
	}

	public void calcul_G() {
		if (this.parent != null)
			this.G = this.parent.G + 1;
		else
			this.G = 0;
	}

	@Override
	public String toString() {
		return ("Noeud " + this.i + this.j + " F = " + this.F + " G = " + this.G + " H= " + this.H);
	}
}

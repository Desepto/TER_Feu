/**
 *
 */
package src;

/**
 * @author Nicolas Allumeeeeeeeeeeeeeeez, le feu ! Allumeeeeeeeeeeeeeez le
 *         feuuuu !
 *
 */
public class Feu extends Acteur {

	// Lorsque le feu se déclenche sur une case, il fait immédiatement baisser
	// l'humidité de la case d'un taux égal à assechement.
	private final int assechement = 20;
	private boolean apparition = true;
	// Pour savoir si c'est la première fois que cet acteur agi sur sa case.

	private final int intensiteFeu = 5;
	// La force du feu : Il assechera l'humidité des cases de 5% par toc sauf au
	// début.

	private final int propaFeu = 1;
	// La fameuse variable à équilibrer. Décrit la capacité du feu à se propager
	// sur ses voisins.

	/**
	 * Déclenche l'apocalypse de l'armageddon sur la terre.
	 *
	 * Grosse perte d'humidité sur la case centrale au premier toc.
	 *
	 * Perte de PV et humidité sur case centrale à tous les tocs.
	 */
	@Override
	public void agi(Carte maCarte) {

		// On essaie pas de mettre le feu n'importe où...
		// On est entre personnes civilisées.
		if (!maCarte.getTerrain(X, Y).isInflammable()) {
			System.out.println("Euh, Herr General, vous voulez vraiment mettre le feu au lac ??");
			return;
		}

		if (maCarte.getTerrain(X, Y).getPV() == 0) {
			int indice = 0;
			for (Acteur courant : maCarte.getSesActeurs()) {
				if (courant.X == this.X && courant.Y == this.Y && courant instanceof Feu) {
					maCarte.supprActeur(indice);
					// On supprime le feu de la case.
					return;
					// On sort de la fonction après extinction du feu.
				}
				indice++;
			}
		}

		if (apparition) {
			maCarte.getTerrain(X, Y).asseche(assechement);
			// On baisse l'humidité d'un montant exceptionnel au début.
			apparition = false;
		} else {
			maCarte.getTerrain(X, Y).asseche(intensiteFeu);
			// On baisse l'humidite d'un montant classique.
			if (maCarte.getTerrain(X, Y).getPV() != 0)
				maCarte.getTerrain(X, Y).brule(1);
			// On décrémente les PV du terrain de 1.
		}

	}

	/**
	 * @param X
	 * @param Y
	 */
	public Feu(int X, int Y) {
		super(X, Y);

	}

	/**
	 * La fameuse fonction à équilibrer. Une carte est nécessaire pour récupérer
	 * force et direction du vent.
	 *
	 * @return vrai si le Terrain propageur met le feu au Terrain propage.
	 */
	/*
	 * public boolean propage(Carte maCarte) { double vent, humidite, trans,
	 * directionVent;
	 * 
	 * switch (maCarte.getForceVent()) { case faible: vent = 1; break; case
	 * moyen: vent = 2; break; case fort: vent = 3; break; }
	 * 
	 * Terrain propageur = maCarte.getTerrain(X, Y); trans = propageur.trans;
	 * Direction ventPropageur = maCarte.getDirectionVent(); int indice = 0;
	 * 
	 * for (Terrain courant : maCarte.superVoisinage(X, Y)) { humidite =
	 * courant.humidite; switch (ventPropageur) { case DirectionVent.HG &&
	 * indice == 1: vent = 1;
	 * 
	 * }
	 * 
	 * }
	 * 
	 * return true; }
	 */
}

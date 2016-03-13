package src;

import java.util.ArrayList;

/**
 * Le gestionnaire des évènements
 *
 * (apparation d'aliens, de pompiers, d'Anouar sur la carte etc).
 *
 * @author Nicolas
 *
 */
public class Gestionnaire {

	private ArrayList<Evenement> mesEvents;
	private Carte laCarte;

	public Gestionnaire(Carte maCarte) {
		mesEvents = new ArrayList<Evenement>();
		laCarte = maCarte;
	}

	public Evenement getUnEvent(int indice) {
		return mesEvents.get(indice);
	}

	public void ajoutEvenement(Evenement monEvent) {
		mesEvents.add(mesEvents.size(), monEvent);

	}

	public void supprUnEvent(int indice) {
		mesEvents.get(0).declenche(laCarte);
		mesEvents.remove(indice);
	}

	/**
	 * Envoit un top d'horloge. Déclence un évènement (le supprime de la liste).
	 */
	public void top() {
		mesEvents.get(0).declenche(laCarte);
		supprUnEvent(0);
	}

}

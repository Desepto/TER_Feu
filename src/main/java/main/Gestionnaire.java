package main;

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

	public Gestionnaire() {
		mesEvents = new ArrayList<Evenement>();
	}

	public Evenement getUnEvent(int indice) {
		return mesEvents.get(indice);
	}

	public void ajoutEvenement(Evenement monEvent) {
		mesEvents.add(mesEvents.size(), monEvent);

	}

	public void supprUnEvent(int indice) {
		this.mesEvents.remove(indice);
	}

}

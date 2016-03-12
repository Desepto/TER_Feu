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

	public Gestionnaire() {
		mesEvents = new ArrayList<Evenement>();
	}

	public Evenement getUnEvent(int indice) {
		return mesEvents.get(indice);
	}

	public Evenement SupprUnEvent(int indice) {
		return mesEvents.remove(indice);
	}

}

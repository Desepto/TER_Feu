package main;

import java.util.ArrayList;
import java.util.Vector;

import acteurs.Acteur;

/**
 * Le gestionnaire des évènements
 *
 * (apparation d'aliens, de pompiers, d'Anouar sur la carte etc).
 *
 * @author Nicolas
 *
 */
public class Gestionnaire {

	private Vector<Evenement> mesEvents;

	public Gestionnaire() {
		mesEvents = new Vector<Evenement>();
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

	public void ajoutActeurPosition(int p, Acteur a) {
		if (mesEvents.size() <= p) {
			Evenement e = new Evenement();
			e.ajouterActeur(a);
			mesEvents.setSize(p + 1);
			mesEvents.set(p, e);
		} else {
			Evenement e = mesEvents.get(p);
			if (e == null) {
				e = new Evenement();
			}
			e.ajouterActeur(a);

			mesEvents.set(p, e);
		}
	}

	// pour les test Ba ui
	public void afficher() {
		for (int i = 0; i < mesEvents.size(); i++) {
			System.out.print("Emplacement  ");
			System.out.print(i);
			System.out.print("  :  ");
			if (mesEvents.get(i) != null) {
				ArrayList<Acteur> a = mesEvents.get(i).getActeurs();
				for (int j = 0; j < a.size(); j++) {
					System.out.print("Classe : " + a.get(j).getClass());
					System.out.print("   X :" + a.get(j).getX());
					System.out.print(" Y:" + a.get(j).getY() + "  ");
				}
				System.out.println(" ");
			} else {
				System.out.println("0");
			}
		}

	}
}

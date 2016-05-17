package main;

import java.util.ArrayList;
import java.util.Vector;

import acteurs.Acteur;
import acteurs.Canadair;
import acteurs.Feu;
import acteurs.Pluie;
import acteurs.Pompier;

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

	public boolean presenceFeu(int x, int y) {
		for (int i = 0; i < mesEvents.size(); i++) {
			if (mesEvents.get(i) != null) {
				ArrayList<Acteur> liste = mesEvents.get(i).getActeurs();
				for (int j = 0; j < liste.size(); j++) {
					if (liste.get(j) instanceof Feu) {
						if (liste.get(j).getX() == x && liste.get(j).getX() == y) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean presencePluie(int x, int y) {
		for (int i = 0; i < mesEvents.size(); i++) {
			if (mesEvents.get(i) != null) {
				ArrayList<Acteur> liste = mesEvents.get(i).getActeurs();
				for (int j = 0; j < liste.size(); j++) {
					if (liste.get(j) instanceof Pluie) {
						if (liste.get(j).getX() == x && liste.get(j).getX() == y) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public int nombrePompier(int x, int y) {
		int compteur = 0;
		for (int i = 0; i < mesEvents.size(); i++) {
			if (mesEvents.get(i) != null) {
				ArrayList<Acteur> liste = mesEvents.get(i).getActeurs();
				for (int j = 0; j < liste.size(); j++) {
					if (liste.get(j) instanceof Pompier) {
						if (liste.get(j).getX() == x && liste.get(j).getX() == y) {
							compteur++;
						}
					}
				}
			}
		}
		return compteur;
	}

	public int nombreCanadair(int x, int y) {
		int compteur = 0;
		for (int i = 0; i < mesEvents.size(); i++) {
			if (mesEvents.get(i) != null) {
				ArrayList<Acteur> liste = mesEvents.get(i).getActeurs();
				for (int j = 0; j < liste.size(); j++) {
					if (liste.get(j) instanceof Canadair) {
						if (liste.get(j).getX() == x && liste.get(j).getX() == y) {
							compteur++;
						}
					}
				}
			}
		}
		return compteur;
	}
}

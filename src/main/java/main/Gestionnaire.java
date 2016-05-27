package main;

import java.util.ArrayList;
import java.util.Vector;

import acteurs.Acteur;
import acteurs.Canadair;
import acteurs.Feu;
import acteurs.Pluie;
import acteurs.Pompier;

/**
 * Le gestionnaire des évènements, s'occupe de l'apparition des différents
 * acteurs
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

	/**
	 * Fonction utilisée pour rajouter un acteur à une position p
	 * 
	 * @param p
	 * @param a
	 */
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

	/**
	 * Fonction utilisée pour les tests
	 */
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

	/**
	 * Indique s'il y a du feu dans la liste d'acteurs
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean presenceFeu(int x, int y) {
		for (int i = 0; i < mesEvents.size(); i++) {
			if (mesEvents.get(i) != null) {
				ArrayList<Acteur> liste = mesEvents.get(i).getActeurs();
				for (int j = 0; j < liste.size(); j++) {
					if (liste.get(j) instanceof Feu) {
						if (liste.get(j).getX() == x
								&& liste.get(j).getX() == y) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Indique s'il y a de la pluie dans le gestionnaire
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean presencePluie(int x, int y) {
		for (int i = 0; i < mesEvents.size(); i++) {
			if (mesEvents.get(i) != null) {
				ArrayList<Acteur> liste = mesEvents.get(i).getActeurs();
				for (int j = 0; j < liste.size(); j++) {
					if (liste.get(j) instanceof Pluie) {
						if (liste.get(j).getX() == x
								&& liste.get(j).getX() == y) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Compte le nombre de pompiers dans le gestionnaire
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int nombrePompier(int x, int y) {
		int compteur = 0;
		for (int i = 0; i < mesEvents.size(); i++) {
			if (mesEvents.get(i) != null) {
				ArrayList<Acteur> liste = mesEvents.get(i).getActeurs();
				for (int j = 0; j < liste.size(); j++) {
					if (liste.get(j) instanceof Pompier) {
						if (liste.get(j).getX() == x
								&& liste.get(j).getX() == y) {
							compteur++;
						}
					}
				}
			}
		}
		return compteur;
	}

	/**
	 * Compte le nombre de canadairs dans le gestionnaire
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public int nombreCanadair(int x, int y) {
		int compteur = 0;
		for (int i = 0; i < mesEvents.size(); i++) {
			if (mesEvents.get(i) != null) {
				ArrayList<Acteur> liste = mesEvents.get(i).getActeurs();
				for (int j = 0; j < liste.size(); j++) {
					if (liste.get(j) instanceof Canadair) {
						if (liste.get(j).getX() == x
								&& liste.get(j).getX() == y) {
							compteur++;
						}
					}
				}
			}
		}
		return compteur;
	}
}

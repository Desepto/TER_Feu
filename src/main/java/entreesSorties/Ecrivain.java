package entreesSorties;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import acteurs.Acteur;
import enumerations.Direction;
import enumerations.Force;
import enumerations.NiveauDensite;
import main.Carte;
import terrains.CoupeFeu;
import terrains.Foret;
import terrains.Lac;
import terrains.Maison;
import terrains.Plaine;
import terrains.Prairie;
import terrains.Rocher;
import terrains.Route;

/**
 * Classe qui génère le fichier de sortie Appelée au début pour le head, pendant
 * pour le body et à la fin pour le foot
 *
 * @author Thomas
 *
 */
public class Ecrivain {

	/**
	 * @param chaine
	 * @return
	 */
	public static ArrayList<String> sanslesPointvirgules(String chaine) {
		ArrayList<String> temp = new ArrayList<String>();
		for (String resultat : chaine.split(";")) {
			temp.add(resultat);
		}
		return temp;

	}

	/**
	 * @param chaine
	 * @return
	 */

	public static ArrayList<String> sanslesVirgules(String chaine) {
		ArrayList<String> temp = new ArrayList<String>();
		for (String resultat : chaine.split(",")) {
			temp.add(resultat);
		}
		return temp;

	}

	/**
	 * @param nomFichier
	 * @return
	 */
	public static Carte creemapFichier(String nomFichier) {

		BufferedReader lecteurAvecBuffer = null;
		String ligne;
		try {
			lecteurAvecBuffer = new BufferedReader(new FileReader(nomFichier));
			Carte maCarte = null;
			int taille = 0;
			Force forceVent = null;
			Direction directionVent = null;

			for (int i = 0; (ligne = lecteurAvecBuffer.readLine()) != null; i++) {

				if (i == 0) {
					taille = Integer.parseInt(ligne);
				} else if (i == 1) {
					forceVent = Force.valueOf(ligne);
				} else if (i == 2) {
					directionVent = Direction.valueOf(ligne);
					maCarte = new Carte(taille, forceVent, directionVent);
				} else if (i < taille + 3) {
					ArrayList<String> liste = sanslesPointvirgules(ligne);
					for (int x = 0; x < liste.size(); x++) {

						ArrayList<String> liste2 = sanslesVirgules(liste.get(x));
						String typeCase = null;
						int humidite = 0;
						NiveauDensite densite = null;
						for (int k = 0; k < liste2.size(); k++) {

							if (k == 0) {
								typeCase = liste2.get(k);
							} else if (k == 1) {
								humidite = Integer.parseInt(liste2.get(k));
							} else if (k == 2) {
								densite = NiveauDensite.valueOf(liste2.get(k));
							}
						}
						switch (typeCase) {
						case "Prairie":
							Prairie p = new Prairie();
							p.setDensite(densite);
							p.setHumidite(humidite);
							maCarte.transformeTerrain(x, i - 3, p);
							break;
						case "Lac":
							Lac l = new Lac();
							l.setDensite(densite);
							l.setHumidite(humidite);
							maCarte.transformeTerrain(x, i - 3, l);
							break;
						case "Maison":
							Maison m = new Maison();
							m.setDensite(densite);
							m.setHumidite(humidite);
							maCarte.transformeTerrain(x, i - 3, m);
							break;
						case "CoupeFeu":
							CoupeFeu c = new CoupeFeu();
							c.setDensite(densite);
							c.setHumidite(humidite);
							maCarte.transformeTerrain(x, i - 3, c);
							break;
						case "Plaine":
							Plaine pl = new Plaine();
							pl.setDensite(densite);
							pl.setHumidite(humidite);
							maCarte.transformeTerrain(x, i - 3, pl);
							break;
						case "Foret":
							Foret f = new Foret();
							f.setDensite(densite);
							f.setHumidite(humidite);
							maCarte.transformeTerrain(x, i - 3, f);
							break;
						case "Rocher":
							Rocher r = new Rocher();
							r.setDensite(densite);
							r.setHumidite(humidite);
							maCarte.transformeTerrain(x, i - 3, r);
							break;
						case "Route":
							Route ro = new Route();
							ro.setDensite(densite);
							ro.setHumidite(humidite);
							maCarte.transformeTerrain(x, i - 3, ro);
							break;
						default:
							System.out.println("BUGGGGGGGGGGGGGGGGGGG;");
							break;

						}
					}
				} else {
					System.out.println(ligne);
				}
				// System.out.println(ligne);
			}
			lecteurAvecBuffer.close();
			return maCarte;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * @param maCarte
	 * @param nomFichier
	 */
	public static void ecrireMap(Carte maCarte, String nomFichier) {
		File f = new File(nomFichier);
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f, false)));
			pw.println(maCarte.getTailleCarte());
			pw.println(maCarte.getForceVent());
			pw.println(maCarte.getDirectionVent());

			for (int Y = 0; Y < maCarte.getTabHexagones()[0].length; Y++) {
				for (int X = 0; X < maCarte.getTabHexagones()[0].length; X++) {
					switch (maCarte.getTerrain(X, Y).getClass().getSimpleName()) {
					case "Prairie":
						pw.print(maCarte.getTerrain(X, Y).getClass().getSimpleName() + ","
								+ maCarte.getTerrain(X, Y).getHumidite() + "," + maCarte.getTerrain(X, Y).getDensite()
								+ ";");
						break;
					case "Lac":
						pw.print(maCarte.getTerrain(X, Y).getClass().getSimpleName() + ","
								+ maCarte.getTerrain(X, Y).getHumidite() + "," + maCarte.getTerrain(X, Y).getDensite()
								+ ";");
						break;
					case "Maison":
						pw.print(maCarte.getTerrain(X, Y).getClass().getSimpleName() + ","
								+ maCarte.getTerrain(X, Y).getHumidite() + "," + maCarte.getTerrain(X, Y).getDensite()
								+ ";");
						break;
					case "CoupeFeu":
						pw.print(maCarte.getTerrain(X, Y).getClass().getSimpleName() + ","
								+ maCarte.getTerrain(X, Y).getHumidite() + "," + maCarte.getTerrain(X, Y).getDensite()
								+ ";");
						break;
					case "Plaine":
						pw.print(maCarte.getTerrain(X, Y).getClass().getSimpleName() + ","
								+ maCarte.getTerrain(X, Y).getHumidite() + "," + maCarte.getTerrain(X, Y).getDensite()
								+ ";");
						break;
					case "Foret":
						pw.print(maCarte.getTerrain(X, Y).getClass().getSimpleName() + ","
								+ maCarte.getTerrain(X, Y).getHumidite() + "," + maCarte.getTerrain(X, Y).getDensite()
								+ ";");
						break;
					case "Rocher":
						pw.print(maCarte.getTerrain(X, Y).getClass().getSimpleName() + ","
								+ maCarte.getTerrain(X, Y).getHumidite() + "," + maCarte.getTerrain(X, Y).getDensite()
								+ ";");
						break;
					case "Route":
						pw.print(maCarte.getTerrain(X, Y).getClass().getSimpleName() + ","
								+ maCarte.getTerrain(X, Y).getHumidite() + "," + maCarte.getTerrain(X, Y).getDensite()
								+ ";");
						break;
					default:
						pw.print("BUGGGGGGGGGGGGGGGGGGG" + ";");
						System.out.println("bauii" + maCarte.getTerrain(X, Y).getClass().getSimpleName());
						break;

					}
				}
				pw.println();
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param maCarte
	 * @param nomFichier
	 */
	public static void initFichier(Carte maCarte, String nomFichier) {
		File f = new File(nomFichier);
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f, false)));
			pw.println("feu;");
			pw.println(1 + ";");
			pw.println(1 + ";");
			pw.println(maCarte.getTailleCarte() + ";" + maCarte.getTailleCarte() + ";");
			pw.println("–––––––––––––––––––––––––––––––––––––––;");

			for (int Y = 0; Y < maCarte.getTailleCarte(); Y++) {
				for (int X = 0; X < maCarte.getTailleCarte(); X++) {
					if (maCarte.getTerrain(X, Y).getPV() == 0) {
						pw.print("CaseDeadddddddddddddd;");
					} else {
						switch (maCarte.getTerrain(X, Y).getClass().getSimpleName()) {

						case "Prairie":
							pw.print(maCarte.getTerrain(X, Y).getClass().getSimpleName() + ";");
							break;
						case "Lac":
							pw.print(maCarte.getTerrain(X, Y).getClass().getSimpleName() + ";");
							break;
						case "Maison":
							pw.print(maCarte.getTerrain(X, Y).getClass().getSimpleName() + ";");
							break;
						case "CoupeFeu":
							pw.print(maCarte.getTerrain(X, Y).getClass().getSimpleName() + ";");
							break;
						case "Plaine":
							pw.print(maCarte.getTerrain(X, Y).getClass().getSimpleName() + ";");
							break;
						case "Foret":
							pw.print(maCarte.getTerrain(X, Y).getClass().getSimpleName() + ";");
							break;
						case "Rocher":
							pw.print(maCarte.getTerrain(X, Y).getClass().getSimpleName() + ";");
							break;
						case "Route":
							pw.print(maCarte.getTerrain(X, Y).getClass().getSimpleName() + ";");
							break;
						default:
							pw.print("BUGGGGGGGGGGGGGGGGGGG" + ";");
							System.out.println("bauii" + maCarte.getTerrain(X, Y).getClass().getSimpleName());
							break;
						}
					}
				}
				pw.println();
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param nomFichier
	 */
	public static void printChangement(String nomFichier, Carte carte, int tickClick) {
		File f = new File(nomFichier);
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f, true)));
			// pw.println("tickClick;");
			int nombreModif = carte.getModifications().size();
			for (int i = 0; i < nombreModif; i++) {
				double X = carte.getModifications().get(i).getX();
				double Y = carte.getModifications().get(i).getY();
				pw.print(tickClick + ";");
				pw.print((int) X + ";");
				pw.print((int) Y + ";");
				if (carte.getTerrain((int) X, (int) Y).getPV() == 0) {
					pw.print("CaseDeadddddddddddddd;");
				} else {
					switch (carte.getTerrain((int) X, (int) Y).getClass().getSimpleName()) {

					case "Prairie":
						pw.print(carte.getTerrain((int) X, (int) Y).getClass().getSimpleName());
						break;
					case "Lac":
						pw.print(carte.getTerrain((int) X, (int) Y).getClass().getSimpleName());
						break;
					case "Maison":
						pw.print(carte.getTerrain((int) X, (int) Y).getClass().getSimpleName());
						break;
					case "CoupeFeu":
						pw.print(carte.getTerrain((int) X, (int) Y).getClass().getSimpleName());
						break;
					case "Plaine":
						pw.print(carte.getTerrain((int) X, (int) Y).getClass().getSimpleName());
						break;
					case "Foret":
						pw.print(carte.getTerrain((int) X, (int) Y).getClass().getSimpleName());
						break;
					case "Rocher":
						pw.print(carte.getTerrain((int) X, (int) Y).getClass().getSimpleName());
						break;
					case "Route":
						pw.print(carte.getTerrain((int) X, (int) Y).getClass().getSimpleName());
						break;
					default:
						pw.print("BUGGGGGGGGGGGGGGGGGGG" + ";");
						System.out.println("bauii" + carte.getTerrain((int) X, (int) Y).getClass().getSimpleName());
						break;
					}
				}
				ArrayList<Acteur> listeActeur = carte.getActeurCase((int) X, (int) Y);
				for (int j = 0; j < listeActeur.size(); j++) {
					switch (listeActeur.get(j).getClass().getSimpleName()) {
					case "Feu":
						pw.print("-Feu");
						break;
					case "Pluie":
						pw.print("-Pluie");
						break;
					case "Canadair":
						pw.print("-Canadair");
						break;
					case "Anouar":
						pw.print("-Anouar");
						break;
					case "Pompier":
						pw.print("-Pompier");
						break;
					default:
						pw.print("BUGGGGGGGGGGGGGGGGGGG" + ";");
						System.out.println("bauii");
						break;

					}
				}
				pw.print(";");
				pw.println();
			}
			pw.close();
		} catch (IOException e) {
			// Celle-ci se produit lors d'une erreur d'�criture ou de lecture
			e.printStackTrace();
		}

	}

	/**
	 * @param nomFichier
	 */
	public static void printFin(String nomFichier) {
		File f = new File(nomFichier);
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f, true)));
			pw.println("------------------------");
			pw.close();

		} catch (IOException e) {
			// Celle-ci se produit lors d'une erreur d'�criture ou de lecture
			e.printStackTrace();
		}

	}

}

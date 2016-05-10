package entreesSorties;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import main.Carte;

/**
 * Classe qui génère le fichier de sortie Appelée au début pour le head, pendant
 * pour le body et à la fin pour le foot
 *
 * @author Thomas
 *
 */
public class Ecrivain {

	private String nomFichier;
	private final String prairie = "Prairie";
	private final String rocher = "Rocher";
	private final String lac = "Lac";
	private final String route = "Route";
	private final String plaine = "Plaine";
	private final String maison = "Maison";
	private final String foret = "Foret";
	private final String coupeFeu = "CoupeFeu";
	private final String caseDead = "CaseDead";
	private final String feu = "Feu";
	private final String pluie = "Pluie";
	private final String canadair = "Canadair";
	private final String pompier = "Pompier";

	public String getNomFichier() {
		return nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public Ecrivain(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public static int nombreCaseBrule(Carte maCarte) {
		int compteur = 0;
		for (int Y = 0; Y < maCarte.getTabHexagones()[0].length; Y++) {
			for (int X = 0; X < maCarte.getTabHexagones()[0].length; X++) {
				if (maCarte.getTerrain(X, Y).getPV() == 0) {
					compteur += 1;
				}
			}
		}
		return compteur;
	}

	public static int nombreCaseIntacte(Carte maCarte) {
		int compteur = 0;
		for (int Y = 0; Y < maCarte.getTabHexagones()[0].length; Y++) {
			for (int X = 0; X < maCarte.getTabHexagones()[0].length; X++) {
				if (maCarte.getTerrain(X, Y).getPV() != 0) {
					compteur += 1;
				}
			}
		}
		return compteur;
	}

	/**
	 * @param maCarte
	 * @param nomFichier
	 */
	public void ecrireMap(Carte maCarte) {
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
	public void initFichier(Carte maCarte) {
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
						pw.print(caseDead + ";");
					} else {
						switch (maCarte.getTerrain(X, Y).getClass().getSimpleName()) {

						case "Prairie":
							pw.print(prairie + ";");
							break;
						case "Lac":
							pw.print(lac + ";");
							break;
						case "Maison":
							pw.print(maison + ";");
							break;
						case "CoupeFeu":
							pw.print(coupeFeu + ";");
							break;
						case "Plaine":
							pw.print(plaine + ";");
							break;
						case "Foret":
							pw.print(foret + ";");
							break;
						case "Rocher":
							pw.print(rocher + ";");
							break;
						case "Route":
							pw.print(route + ";");
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
	public void printChangement(Carte carte, int tickClick) {
		File f = new File(nomFichier);
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f, true)));
			int nombreModif = carte.getModifications().size();
			for (int i = 0; i < nombreModif; i++) {
				double X = carte.getModifications().get(i).getX();
				double Y = carte.getModifications().get(i).getY();
				pw.print(tickClick + ";");
				pw.print((int) X + ";");
				pw.print((int) Y + ";");
				if (carte.getTabHexagones((int) X, (int) Y).getPV() == 0) {
					pw.print(caseDead);
				} else {
					switch (carte.getTabHexagones((int) X, (int) Y).getClass().getSimpleName()) {
					case "Prairie":
						pw.print(prairie);
						break;
					case "Lac":
						pw.print(lac);
						break;
					case "Maison":
						pw.print(maison);
						break;
					case "CoupeFeu":
						pw.print(coupeFeu);
						break;
					case "Plaine":
						pw.print(plaine);
						break;
					case "Foret":
						pw.print(foret);
						break;
					case "Rocher":
						pw.print(rocher);
						break;
					case "Route":
						pw.print(route);
						break;
					default:
						pw.print("BUGGGGGGGGGGGGGGGGGGG" + ";");
						System.out.println("bauii" + carte.getTerrain((int) X, (int) Y).getClass().getSimpleName());
						break;
					}
				}
				if (carte.presenceCanadair((int) X, (int) Y)) {
					pw.print("-" + canadair);
				}
				if (carte.presenceFeu((int) X, (int) Y)) {
					pw.print("-" + feu);
				}
				if (carte.presencePluie((int) X, (int) Y)) {
					pw.print("-" + pluie);
				}
				if (carte.getNbPompiers((int) X, (int) Y) == 1) {
					pw.print("-" + pompier + "1");
				} else if (carte.getNbPompiers((int) X, (int) Y) == 2) {
					pw.print("-" + pompier + "2");
				} else if (carte.getNbPompiers((int) X, (int) Y) == 3) {
					pw.print("-" + pompier + "3");
				} else if (carte.getNbPompiers((int) X, (int) Y) == 4) {
					pw.print("-" + pompier + "4");
				} else if (carte.getNbPompiers((int) X, (int) Y) == 5) {
					pw.print("-" + pompier + "5");
				} else if (carte.getNbPompiers((int) X, (int) Y) == 6) {
					pw.print("-" + pompier + "6");
				} else if (carte.getNbPompiers((int) X, (int) Y) == 7) {
					pw.print("-" + pompier + "7");
				} else if (carte.getNbPompiers((int) X, (int) Y) == 8) {
					pw.print("-" + pompier + "8");
				} else if (carte.getNbPompiers((int) X, (int) Y) == 9) {
					pw.print("-" + pompier + "9");
				}
				pw.print(";");
				pw.println();
			}
			for (int Y = 0; Y < carte.getTailleCarte(); Y++) {
				if (Y % 2 != 0)
					pw.print(" ");

				for (int X = 0; X < carte.getTailleCarte(); X++) {
					if (carte.presenceFeu(X, Y))
						pw.print("F");
					else {
						if (carte.getTerrain(X, Y).getPV() <= 0)
							pw.print("C");
						else
							pw.print("n");
					}
				}
				pw.println();
			}
			pw.println(carte.getFeu().size());
			pw.close();
		} catch (IOException e) {
			// Celle-ci se produit lors d'une erreur d'�criture ou de lecture
			e.printStackTrace();
		}

	}

	/**
	 * @param nomFichier
	 */
	public void printFin(Carte carte) {
		File f = new File(nomFichier);
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f, true)));
			pw.println("------------------------;");
			int nbCaseIntacte = nombreCaseIntacte(carte);
			int nbCaseBrule = nombreCaseBrule(carte);
			pw.println("Nombre de cases intactes : " + Integer.toString(nbCaseIntacte));
			pw.println("Nombre de cases brûlées : " + Integer.toString(nbCaseBrule));
			double p = new Double(new Integer(nbCaseBrule).doubleValue()
					/ new Integer(carte.getTailleCarte() * carte.getTailleCarte()).doubleValue());
			pw.println("Pourcentage de cases brûlées : " + p);
			pw.println("Nombre de pompiers déployés : " + Integer.toString(carte.nBPompiers()));
			pw.println("Nombre de pompiers morts : " + Integer.toString(carte.getnBpompiersMorts()));

			pw.println();
			pw.println("--------------------------------------");
			for (int Y = 0; Y < carte.getTailleCarte(); Y++) {
				for (int X = 0; X < carte.getTailleCarte(); X++) {
					if (carte.getTerrain(X, Y).getPV() <= 0)
						pw.print("0");
					else
						pw.print("#");
				}
				pw.println();
			}
			pw.close();
		} catch (IOException e) {
			// Celle-ci se produit lors d'une erreur d'écriture ou de lecture
			e.printStackTrace();
		}

	}

}

package entreesSorties;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import main.Carte;
import terrains.CoupeFeu;
import terrains.Lac;
import terrains.Rocher;

/**
 * Classe qui génère le fichier de sortie appelée au début pour le head, pendant
 * pour le body et à la fin pour le foot
 * 
 * @author André
 * 
 */
public class Ecrivain {

	// Liste des équivalences classes/nom de fichiers
	private String nomFichier;
	private final String prairie = "3";
	private final String rocher = "7";
	private final String lac = "8";
	private final String route = "4";
	private final String plaine = "2";
	private final String maison = "6";
	private final String foret = "1";
	private final String coupeFeu = "5";
	private final String caseDead = "0";
	private final String feu = "10";
	private final String pluie = "11";
	private final String inonde = "9";
	private final int canadair = 80;
	private final int pompier = 70;
	private final int vent = 90;

	public String getNomFichier() {
		return nomFichier;
	}

	public void setNomFichier(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	public Ecrivain(String nomFichier) {
		this.nomFichier = nomFichier;
	}

	/**
	 * Compte le nombre de cases brulées sur la carte.
	 * 
	 * @param maCarte
	 * @return Le nombre de cases brûlées
	 */
	public static int nombreCaseBrule(Carte maCarte) {
		int compteur = 0;
		for (int Y = 0; Y < maCarte.getTabHexagones()[0].length; Y++) {
			for (int X = 0; X < maCarte.getTabHexagones()[0].length + 1; X++) {
				if (Y % 2 == 0) {
					if (X == maCarte.getTabHexagones()[0].length) {
						continue;
					}
				}
				if (Y % 2 != 0) {
					if (X == 0) {
						continue;
					}
				}
				if (maCarte.getTabHexagones(X, Y).getPV() == 0) {
					compteur += 1;
				}
			}
		}
		return compteur;
	}

	/**
	 * Compte le nombre de cases intactes sur la carte.
	 * 
	 * @param maCarte
	 * @return Le nombre de cases intactes
	 */
	public static int nombreCaseIntacte(Carte maCarte) {
		int compteur = 0;
		for (int Y = 0; Y < maCarte.getTabHexagones()[0].length; Y++) {
			for (int X = 0; X < maCarte.getTabHexagones()[0].length + 1; X++) {
				if (Y % 2 == 0) {
					if (X == maCarte.getTabHexagones()[0].length) {
						continue;
					}
				}
				if (Y % 2 != 0) {
					if (X == 0) {
						continue;
					}
				}
				if (maCarte.getTabHexagones(X, Y).getPV() != 0) {
					compteur += 1;
				}
			}
		}
		return compteur;
	}

	/**
	 * Compte le nombre de cases pouvant être brulées sur la carte
	 * 
	 * @param maCarte
	 * @return Le nombre de cases pouvant brûler
	 */
	public static int nombreCaseBrulable(Carte maCarte) {
		int compteur = 0;
		for (int Y = 0; Y < maCarte.getTabHexagones()[0].length; Y++) {
			for (int X = 0; X < maCarte.getTabHexagones()[0].length + 1; X++) {
				if (Y % 2 == 0) {
					if (X == maCarte.getTabHexagones()[0].length) {
						continue;
					}
				}
				if (Y % 2 != 0) {
					if (X == 0) {
						continue;
					}
				}
				if (!(maCarte.getTabHexagones(X, Y) instanceof Lac)
						&& !(maCarte.getTabHexagones(X, Y) instanceof Rocher)
						&& !(maCarte.getTabHexagones(X, Y) instanceof CoupeFeu)) {
					compteur += 1;
				}
			}
		}
		return compteur;
	}

	/**
	 * Compte le nombre de cases n'ayant pas brulées alors qu'elles le peuvent
	 * 
	 * @param maCarte
	 * @return
	 */
	public static int nombreCaseIntacteBrulable(Carte maCarte) {
		int compteur = 0;
		for (int Y = 0; Y < maCarte.getTabHexagones()[0].length; Y++) {
			for (int X = 0; X < maCarte.getTabHexagones()[0].length + 1; X++) {
				if (Y % 2 == 0) {
					if (X == maCarte.getTabHexagones()[0].length) {
						continue;
					}
				}
				if (Y % 2 != 0) {
					if (X == 0) {
						continue;
					}
				}
				if (maCarte.getTabHexagones(X, Y).getPV() != 0
						&& !(maCarte.getTabHexagones(X, Y) instanceof Lac)
						&& !(maCarte.getTabHexagones(X, Y) instanceof Rocher)
						&& !(maCarte.getTabHexagones(X, Y) instanceof CoupeFeu)) {
					compteur += 1;
				}
			}
		}
		return compteur;
	}

	/**
	 * 
	 * Fonction qui écrit une carte dans le fichier de sortie -- OBSOLETE--
	 * 
	 * @param maCarte
	 * @param nomFichier
	 */
	@Deprecated
	public void ecrireMap(Carte maCarte) {
		File f = new File(nomFichier);
		try {

			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					f, false)));
			pw.println(maCarte.getTailleCarte());
			pw.println(maCarte.getForceVent());
			pw.println(maCarte.getDirectionVent());

			// Ecrit la carte
			for (int Y = 0; Y < maCarte.getTabHexagones()[0].length; Y++) {
				for (int X = 0; X < maCarte.getTabHexagones()[0].length + 1; X++) {
					if (Y % 2 == 0) {
						if (X == maCarte.getTabHexagones()[0].length) {
							continue;
						}
					}

					if (Y % 2 != 0) {
						if (X == 0) {
							continue;
						}
					}
					switch (maCarte.getTabHexagones(X, Y).getClass()
							.getSimpleName()) {
					case "Prairie":
						pw.print(maCarte.getTabHexagones(X, Y).getClass()
								.getSimpleName()
								+ ","
								+ maCarte.getTabHexagones(X, Y).getHumidite()
								+ ","
								+ maCarte.getTabHexagones(X, Y).getDensite()
								+ ";");
						break;
					case "Lac":
						pw.print(maCarte.getTabHexagones(X, Y).getClass()
								.getSimpleName()
								+ ","
								+ maCarte.getTabHexagones(X, Y).getHumidite()
								+ ","
								+ maCarte.getTabHexagones(X, Y).getDensite()
								+ ";");
						break;
					case "Maison":
						pw.print(maCarte.getTabHexagones(X, Y).getClass()
								.getSimpleName()
								+ ","
								+ maCarte.getTabHexagones(X, Y).getHumidite()
								+ ","
								+ maCarte.getTabHexagones(X, Y).getDensite()
								+ ";");
						break;
					case "CoupeFeu":
						pw.print(maCarte.getTabHexagones(X, Y).getClass()
								.getSimpleName()
								+ ","
								+ maCarte.getTabHexagones(X, Y).getHumidite()
								+ ","
								+ maCarte.getTabHexagones(X, Y).getDensite()
								+ ";");
						break;
					case "Plaine":
						pw.print(maCarte.getTabHexagones(X, Y).getClass()
								.getSimpleName()
								+ ","
								+ maCarte.getTabHexagones(X, Y)
								+ ","
								+ maCarte.getTabHexagones(X, Y).getDensite()
								+ ";");
						break;
					case "Foret":
						pw.print(maCarte.getTabHexagones(X, Y).getClass()
								.getSimpleName()
								+ ","
								+ maCarte.getTabHexagones(X, Y).getHumidite()
								+ ","
								+ maCarte.getTabHexagones(X, Y).getDensite()
								+ ";");
						break;
					case "Rocher":
						pw.print(maCarte.getTabHexagones(X, Y).getClass()
								.getSimpleName()
								+ ","
								+ maCarte.getTabHexagones(X, Y).getHumidite()
								+ ","
								+ maCarte.getTabHexagones(X, Y).getDensite()
								+ ";");
						break;
					case "Route":
						pw.print(maCarte.getTabHexagones(X, Y).getClass()
								.getSimpleName()
								+ ","
								+ maCarte.getTabHexagones(X, Y).getHumidite()
								+ ","
								+ maCarte.getTabHexagones(X, Y).getDensite()
								+ ";");
						break;
					default:
						pw.print("WARNING" + ";");
						System.out.println("Erreur : "
								+ maCarte.getTabHexagones(X, Y).getClass()
										.getSimpleName());
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
	 * Ecrit le header du fichier de sortie
	 * 
	 * @param maCarte
	 * @param nomFichier
	 */
	public void initFichier(Carte maCarte) {
		File f = new File(nomFichier);
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					f, false)));
			// Ecris les toutes premières infos
			pw.println("feu;");
			pw.println(1 + ";");
			pw.println(1 + ";");
			pw.println(maCarte.getTailleCarte() + ";"
					+ maCarte.getTailleCarte() + ";");
			pw.println("----------------------------------;");

			// Ecris la carte
			for (int Y = 0; Y < maCarte.getTailleCarte(); Y++) {
				for (int X = 0; X < maCarte.getTailleCarte() + 1; X++) {
					// S'il s'agit d'une case vide
					if (Y % 2 == 0) {
						if (X == maCarte.getTabHexagones()[0].length) {
							continue;
						}
					}

					if (Y % 2 != 0) {
						if (X == 0) {
							continue;
						}
					}
					if (maCarte.getTabHexagones(X, Y).getPV() == 0) {
						pw.print(caseDead + ";");
					} else {
						switch (maCarte.getTabHexagones(X, Y).getClass()
								.getSimpleName()) {

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
							pw.print("WARNING" + ";");
							System.out.println("Erreur : "
									+ maCarte.getTabHexagones(X, Y).getClass()
											.getSimpleName());
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
	 * Ecris le détail d'un changement dans le fichier de sortie
	 * 
	 * @param nomFichier
	 */
	public void printChangement(Carte carte, int tickClick) {
		File f = new File(nomFichier);
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					f, true)));
			int nombreModif = carte.getModifications().size();
			if (tickClick == 0) {
				pw.print(0 + ";");
				pw.print(0 + ";");
				pw.print(0 + ";");
				if (carte.getTabHexagones(0, 0).getPV() == 0) {
					pw.print(caseDead);
				} else {
					switch (carte.getTabHexagones(0, 0).getClass()
							.getSimpleName()) {
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
						pw.print("WARNING" + ";");
						System.out.println("Erreur : "
								+ carte.getTabHexagones(0, 0).getClass()
										.getSimpleName());
						break;
					}
				}
				int ventfinal = carte.getDirectionVent().ordinal() + vent;
				pw.println("-" + ventfinal + ";");
			}
			for (int i = 0; i < nombreModif; i++) {
				double X = carte.getModifications().get(i).getX();
				double Y = carte.getModifications().get(i).getY();
				pw.print(tickClick + ";");
				if (Y % 2 != 0) {
					pw.print(Integer.toString((int) X - 1) + ";");
				} else {
					pw.print((int) X + ";");
				}

				pw.print((int) Y + ";");
				if (carte.getTabHexagones((int) X, (int) Y).getPV() == 0) {
					pw.print(caseDead);
				} else {
					switch (carte.getTabHexagones((int) X, (int) Y).getClass()
							.getSimpleName()) {
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
						System.out.println("bauii"
								+ carte.getTabHexagones((int) X, (int) Y)
										.getClass().getSimpleName());
						break;
					}
				}

				if ((int) X == 2 && (int) Y == 4
						&& carte.getTabHexagones((int) X, (int) Y).isInonde()) {
					if (carte.presenceFeu((int) X, (int) Y)) {
						System.out.println("Y a un bug");
					}
				}
				if (carte.presenceFeu((int) X, (int) Y)) {
					pw.print("-" + feu);
				}
				if (carte.presencePluie((int) X, (int) Y)) {
					pw.print("-" + pluie);
				}
				if (carte.getTabHexagones((int) X, (int) Y).isInonde()) {
					pw.print("-" + inonde);
				}

				if (carte.getNbPompiers((int) X, (int) Y) == 1) {
					pw.print("-" + Integer.toString(pompier + 1));
				} else if (carte.getNbPompiers((int) X, (int) Y) == 2) {
					pw.print("-" + Integer.toString(pompier + 2));
				} else if (carte.getNbPompiers((int) X, (int) Y) == 3) {
					pw.print("-" + Integer.toString(pompier + 3));
				} else if (carte.getNbPompiers((int) X, (int) Y) == 4) {
					pw.print("-" + Integer.toString(pompier + 4));
				} else if (carte.getNbPompiers((int) X, (int) Y) == 5) {
					pw.print("-" + Integer.toString(pompier + 5));
				} else if (carte.getNbPompiers((int) X, (int) Y) == 6) {
					pw.print("-" + Integer.toString(pompier + 6));
				} else if (carte.getNbPompiers((int) X, (int) Y) == 7) {
					pw.print("-" + Integer.toString(pompier + 7));
				} else if (carte.getNbPompiers((int) X, (int) Y) == 8) {
					pw.print("-" + Integer.toString(pompier + 8));
				} else if (carte.getNbPompiers((int) X, (int) Y) > 8) {
					pw.print("-" + Integer.toString(pompier + 9));
				}

				if (carte.getNbCanadair((int) X, (int) Y) == 1) {
					pw.print("-" + Integer.toString(canadair + 1));
				} else if (carte.getNbCanadair((int) X, (int) Y) == 2) {
					pw.print("-" + Integer.toString(canadair + 2));
				} else if (carte.getNbCanadair((int) X, (int) Y) == 3) {
					pw.print("-" + Integer.toString(canadair + 3));
				} else if (carte.getNbCanadair((int) X, (int) Y) == 4) {
					pw.print("-" + Integer.toString(canadair + 4));
				} else if (carte.getNbCanadair((int) X, (int) Y) == 5) {
					pw.print("-" + Integer.toString(canadair + 5));
				} else if (carte.getNbCanadair((int) X, (int) Y) == 6) {
					pw.print("-" + Integer.toString(canadair + 6));
				} else if (carte.getNbCanadair((int) X, (int) Y) == 7) {
					pw.print("-" + Integer.toString(canadair + 7));
				} else if (carte.getNbCanadair((int) X, (int) Y) == 8) {
					pw.print("-" + Integer.toString(canadair + 8));
				} else if (carte.getNbCanadair((int) X, (int) Y) > 8) {
					pw.print("-" + Integer.toString(canadair + 9));
				}

				if (X == 0 && Y == 0) {
					int ventfinal = carte.getDirectionVent().ordinal() + vent;
					pw.print("-" + ventfinal);
				}
				pw.print(";");
				pw.println();
			}

			pw.close();
		} catch (IOException e) {
			// Celle-ci se produit lors d'une erreur d'écriture ou de lecture
			e.printStackTrace();
		}

	}

	/**
	 * Ecris la partie foot du fichier
	 * 
	 * @param nomFichier
	 */
	public void printFin(Carte carte) {
		File f = new File(nomFichier);
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
					f, true)));
			pw.println("------------------------;");
			int nbCaseIntacteBrulable = nombreCaseIntacteBrulable(carte);
			int nbCaseBrule = nombreCaseBrule(carte);
			int nbCaseBrulable = nombreCaseBrulable(carte);
			pw.println("Nombre de cases inflammables  : "
					+ Integer.toString(nbCaseBrulable));
			pw.println("Nombre de cases intactes : "
					+ Integer.toString(nbCaseIntacteBrulable));
			pw.println("Nombre de cases brulées : "
					+ Integer.toString(nbCaseBrule));
			double p = new Double(new Integer(nbCaseBrule).doubleValue()
					/ new Integer(nbCaseBrulable).doubleValue() * 100);
			DecimalFormat df = new DecimalFormat("########.00");
			pw.println("Pourcentage de cases brûlées : " + df.format(p));
			pw.println("Nombre de pompiers déployés : "
					+ Integer.toString(carte.nBPompiers()
							+ carte.getnBpompiersMorts()));
			pw.println("Nombre de canadairs déployés : "
					+ Integer.toString(carte.getCanadair().size()));
			pw.println("Nombre de pompiers morts : "
					+ Integer.toString(carte.getnBpompiersMorts()));

			pw.close();
		} catch (IOException e) {
			// Celle-ci se produit lors d'une erreur d'écriture ou de lecture
			e.printStackTrace();
		}

	}

}

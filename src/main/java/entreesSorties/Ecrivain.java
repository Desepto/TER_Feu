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
			pw.println("–––––––––––––––––––––––––––––––––––––––Anouar;");

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
				if (carte.presenceCanadair((int) X, (int) Y)) {
					pw.print("-Canadair");
				}
				if (carte.presenceFeu((int) X, (int) Y)) {
					pw.print("-Feu");
				}
				if (carte.presencePluie((int) X, (int) Y)) {
					pw.print("-Pluie");
				}
				if (carte.getNbPompiers((int) X, (int) Y) == 1) {
					pw.print("-Pompier1");
				} else if (carte.getNbPompiers((int) X, (int) Y) == 2) {
					pw.print("-Pompier2");
				} else if (carte.getNbPompiers((int) X, (int) Y) == 3) {
					pw.print("-Pompier3");
				} else if (carte.getNbPompiers((int) X, (int) Y) == 4) {
					pw.print("-Pompier4");
				} else if (carte.getNbPompiers((int) X, (int) Y) == 5) {
					pw.print("-Pompier5");
				} else if (carte.getNbPompiers((int) X, (int) Y) == 6) {
					pw.print("-Pompier6");
				} else if (carte.getNbPompiers((int) X, (int) Y) == 7) {
					pw.print("-Pompier7");
				} else if (carte.getNbPompiers((int) X, (int) Y) == 8) {
					pw.print("-Pompier8");
				} else if (carte.getNbPompiers((int) X, (int) Y) == 9) {
					pw.print("-Pompier9");
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
	public void printFin() {
		File f = new File(nomFichier);
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(f, true)));
			pw.println("------------------------Anouar");
			pw.close();

		} catch (IOException e) {
			// Celle-ci se produit lors d'une erreur d'�criture ou de lecture
			e.printStackTrace();
		}

	}

}

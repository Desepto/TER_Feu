package entreesSorties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import acteurs.Canadair;
import acteurs.Feu;
import acteurs.Pluie;
import acteurs.Pompier;
import enumerations.Direction;
import enumerations.Force;
import enumerations.NiveauDensite;
import main.Carte;
import main.Gestionnaire;
import terrains.CoupeFeu;
import terrains.Foret;
import terrains.Lac;
import terrains.Maison;
import terrains.Plaine;
import terrains.Prairie;
import terrains.Rocher;
import terrains.Route;
import terrains.TerrainVide;

/**
 * Classe qui lit le fichier de base et le transforme en une map
 *
 * @author Nicolas
 *
 */

public class Lecteur {

	/**
	 * Méthode statique pour générer une carte "en dur" sans passer par la
	 * lecture d'un fichier. Utile pour faire une carte de base avec des
	 * terrains aléatoires.
	 *
	 * @return la carte remplie.
	 */
	public static Carte carteEnDur() {

		Carte maCarte = new Carte(30);
		int nombre; // Nombre aléatoire qui contiendra le type de terrain.
		Random rand;
		for (int Y = 0; Y < 30; Y++) {
			for (int X = 0; X < 31; X++) {

				if (Y % 2 == 0) {
					if (X == 30) {
						maCarte.getTabHexagones()[X][Y] = new TerrainVide();
						continue;
					}
				}

				if (Y % 2 != 0) {
					if (X == 0) {
						maCarte.getTabHexagones()[X][Y] = new TerrainVide();
						continue;
					}
				}

				rand = new Random();
				nombre = rand.nextInt(8); // Entre 0 et 7 car il y a 8 terrains
											// différents.

				switch (nombre) {
				case 0:
					maCarte.getTabHexagones()[X][Y] = new Route();
					break;
				case 1:
					maCarte.getTabHexagones()[X][Y] = new CoupeFeu();
					break;
				case 2:
					maCarte.getTabHexagones()[X][Y] = new Prairie();
					break;
				case 3:
					maCarte.getTabHexagones()[X][Y] = new Plaine();
					break;
				case 4:
					maCarte.getTabHexagones()[X][Y] = new Lac();
					break;
				case 5:
					maCarte.getTabHexagones()[X][Y] = new Maison();
					break;
				case 6:
					maCarte.getTabHexagones()[X][Y] = new Foret();
					break;
				case 7:
					maCarte.getTabHexagones()[X][Y] = new Rocher();
					break;
				default:
					maCarte.getTabHexagones()[X][Y] = new TerrainVide();
					break;
				}

			}
		}
		return maCarte;
	}

	public static Carte carteEnDurToutCrame(int taille, Force f, Direction d) {

		Carte maCarte = new Carte(taille, f, d);
		int nombre; // Nombre aléatoire qui contiendra le type de terrain.
		Random rand;
		for (int Y = 0; Y < taille; Y++) {
			for (int X = 0; X < taille + 1; X++) {

				if (Y % 2 == 0) {
					if (X == taille) {
						maCarte.getTabHexagones()[X][Y] = new TerrainVide();
						continue;
					}
				}

				if (Y % 2 != 0) {
					if (X == 0) {
						maCarte.getTabHexagones()[X][Y] = new TerrainVide();
						continue;
					}
				}

				rand = new Random();
				nombre = rand.nextInt(8); // Entre 0 et 7 car il y a 8 terrains
											// différents.

				switch (nombre) {
				case 0:
					maCarte.getTabHexagones()[X][Y] = new Foret();
					break;
				case 1:
					maCarte.getTabHexagones()[X][Y] = new Foret();
					break;
				case 2:
					maCarte.getTabHexagones()[X][Y] = new Prairie();
					break;
				case 3:
					maCarte.getTabHexagones()[X][Y] = new Plaine();
					break;
				case 4:
					maCarte.getTabHexagones()[X][Y] = new Foret();
					break;
				case 5:
					maCarte.getTabHexagones()[X][Y] = new Maison();
					break;
				case 6:
					maCarte.getTabHexagones()[X][Y] = new Foret();
					break;
				case 7:
					maCarte.getTabHexagones()[X][Y] = new Foret();
					break;
				default:
					maCarte.getTabHexagones()[X][Y] = new TerrainVide();
					break;
				}

			}
		}
		return maCarte;
	}

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

	public static ArrayList<String> sansles2Points(String chaine) {
		ArrayList<String> temp = new ArrayList<String>();
		for (String resultat : chaine.split(":")) {
			temp.add(resultat);
		}
		return temp;

	}

	/**
	 * @param nomFichier
	 * @return
	 */
	public static Carte creemapFichier(String nomFichier, Gestionnaire g) {

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
					ArrayList<String> liste3 = sansles2Points(ligne);
					String type = "";
					int temps = 0;
					int x = 0;
					int y = 0;
					for (int l = 0; l < liste3.size() && liste3.size() == 4; l++) {
						if (l == 0) {
							type = liste3.get(l);
						} else if (l == 1) {
							temps = Integer.parseInt(liste3.get(l));
						} else if (l == 2) {
							x = Integer.parseInt(liste3.get(l));
						} else if (l == 3) {
							y = Integer.parseInt(liste3.get(l));
							if (y % 2 != 0) {
								x++;
							}
							if ((x == maCarte.getTailleCarte() && y % 2 == 0) || (x == 0 && y % 2 != 0) || x < 0
									|| x > maCarte.getTailleCarte() || y < 0 || y > maCarte.getTailleCarte() - 1) {
								System.out.println("Dar une case qui existe pas");
							} else {
								switch (type) {
								case "Pompier":
									Pompier p = new Pompier(x, y);
									if (maCarte.getTabHexagones(x, y) instanceof Lac) {
										System.out.println("Un Pompier sur un Lac pas possible");
									} else if (maCarte.getTabHexagones(x, y) instanceof Rocher) {
										System.out.println("Un Pompier sur un Rocher pas possible");
									} else {
										g.ajoutActeurPosition(temps, p);
									}
									break;
								case "Canadair":
									Canadair c = new Canadair(x, y);
									if (!maCarte.presenceLac()) {
										System.out.println("Pas de Lac");
									} else {
										g.ajoutActeurPosition(temps, c);
									}

									break;
								case "Feu":
									Feu f = new Feu(x, y);
									if (maCarte.getTabHexagones(x, y) instanceof Lac) {
										System.out.println("Un feu sur un Lac pas possible");
									} else if (maCarte.getTabHexagones(x, y) instanceof Rocher) {
										System.out.println("Un feu sur un Rocher pas possible");
									} else if (maCarte.getTabHexagones(x, y) instanceof CoupeFeu) {
										System.out.println("Un feu sur un CoupeFeu pas possible");
									} else if (g.presenceFeu(x, y)) {
										System.out.println("Un feu sur un feu pas possible");
									} else if (maCarte.getTabHexagones(x, y).isInonde()) {
										System.out.println("On peut pas brulé une piscine");
									} else {
										g.ajoutActeurPosition(temps, f);

									}
									break;
								case "Pluie":
									Pluie pl = new Pluie(x, y);
									if (g.presencePluie(x, y)) {
										System.out.println("il pleut deja");
									} else {
										g.ajoutActeurPosition(temps, pl);
										System.out.println("marche");
									}
									break;
								default:
									System.out.println("BUGGGGGGGGGGGGGGGGGGG;");
									break;
								}
							}
						}
					}
				}
			}
			lecteurAvecBuffer.close();
			return maCarte;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
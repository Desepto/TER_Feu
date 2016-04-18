package entreesSorties;

import java.awt.Point;
import java.util.ArrayList;

import acteurs.Feu;
import acteurs.Pluie;
import main.Carte;

public class Main {
	public static void main(String[] args) {
		Carte maCarte = Lecteur.carteEnDur();
		Ecrivain.initFichier(maCarte, "Testuuuuuuuuuuu");
		Point p1 = new Point(10, 10);
		Point p2 = new Point(10, 20);
		Point p3 = new Point(10, 25);
		ArrayList<Point> liste = new ArrayList<Point>();
		liste.add(p1);
		maCarte.setModifications(liste);
		Ecrivain.printChangement("Testuuuuuuuuuuu", maCarte, 1);
		liste = new ArrayList<Point>();
		liste.add(p1);
		liste.add(p2);
		maCarte.setModifications(liste);
		Ecrivain.printChangement("Testuuuuuuuuuuu", maCarte, 2);
		liste = new ArrayList<Point>();
		liste.add(p1);
		liste.add(p2);
		liste.add(p3);
		maCarte.ajoutActeur(new Pluie(10, 10));
		maCarte.ajoutActeur(new Feu(10, 10));
		maCarte.setModifications(liste);
		Ecrivain.printChangement("Testuuuuuuuuuuu", maCarte, 3);
		liste = new ArrayList<Point>();
		maCarte.setModifications(liste);
		Ecrivain.printChangement("Testuuuuuuuuuuu", maCarte, 4);
		Ecrivain.printFin("Testuuuuuuuuuuu");

	}

}

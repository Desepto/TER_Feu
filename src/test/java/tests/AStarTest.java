package tests;

import static org.junit.Assert.fail;

import java.awt.Point;

import main.Carte;

import org.junit.Test;

import pathfinding.AStar;
import terrains.Plaine;
import terrains.Rocher;
import terrains.Terrain;
import acteurs.Canadair;
import acteurs.Feu;
import enumerations.Direction;
import enumerations.Force;
import enumerations.NiveauDensite;

public class AStarTest {

	@Test
	public void PompierVaBienSurFeuAccessible() {

		Carte c = new Carte(25, Force.faible, Direction.BG);
		Terrain[][] tab = new Terrain[25][25];
		for (int i = 0; i < 25; i++)
			for (int j = 0; j < 25; j++)
				tab[i][j] = new Plaine(NiveauDensite.un);
		tab[1][0] = new Rocher();
		// tab[1][1] = new Rocher();
		c.setMaCarte(tab);
		c.ajoutActeur(new Feu(12, 12));
		c.ajoutActeur(new Feu(15, 20));
		// c.ajoutActeur(new Feu(1, 5));
		c.ajoutActeur(new Canadair(0, 0));
		c.getCanadair().get(0).agi(c);
		System.out.println(((Canadair) c.getCanadair().get(0)).isEstCharge());

		System.out.println("eesth");
		Point p = new Point(AStar.deplacement(c, c.getCanadair().get(0)));
		System.out.println(p.toString());

		// assertEquals(AStar.deplacement(c, c.getPompier().get(0)), new
		// Point(2,2));
		System.out.println(p.toString());
		fail("zsfe");
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

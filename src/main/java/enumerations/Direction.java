package enumerations;

/**
 * 
 * Les 6 directions autour d'un hexagone.
 * 
 * @author Nicolas
 */
public enum Direction {

	// L'ordre est très important car on utilise la méthode ordinal() de enum.
	// On doit absolument tourner dans le sens des aiguilles d'une montre.
	HG, HD, D, BD, BG, G;
}

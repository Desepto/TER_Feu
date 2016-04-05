package src;

/**
 * Terrain inexistant ne figurant pas sur la carte.
 *
 * @author Nicolas
 *
 */
public class TerrainVide extends Terrain {

	/**
	 * Constructeur par défaut.
	 */
	public TerrainVide() {
		super(false); // Un terrain qui n'existe pas est à priori
						// ininflammable... Bref.
	}

}

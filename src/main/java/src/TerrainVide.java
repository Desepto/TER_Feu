package src;

/**
 * Terrain Kloulita ne figurant pas sur la carte.
 *
 * @author Nicolas
 *
 */
public class TerrainVide extends Terrain {

	public TerrainVide(int monId, String nature) {
		this.identifiant = monId;
		this.nature = nature;
	}

	public TerrainVide() {

	}

}

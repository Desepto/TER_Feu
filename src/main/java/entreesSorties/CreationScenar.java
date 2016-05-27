package entreesSorties;

import main.Carte;
import enumerations.Direction;
import enumerations.Force;

/**
 * 
 * Classe utilisée pour créer des scénarios codés en dur.
 * 
 * @author André
 * 
 */
public class CreationScenar {
	public static void main(String[] args) {
		Carte maCarte = Lecteur.carteEnDurToutCrame(22, Force.faible,
				Direction.G);
		Ecrivain ecr = new Ecrivain("Scenario2");
		// ecr.ecrireMap(maCarte);

	}

}

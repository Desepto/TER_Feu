package entreesSorties;

import enumerations.Direction;
import enumerations.Force;
import main.Carte;

public class CreationScenar {
	public static void main(String[] args) {
		Carte maCarte = Lecteur.carteEnDurToutCrame(22, Force.faible, Direction.G);
		Ecrivain ecr = new Ecrivain("Scenario2");
		// ecr.ecrireMap(maCarte);

	}

}

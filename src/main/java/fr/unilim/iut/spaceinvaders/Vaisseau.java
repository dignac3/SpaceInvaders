package fr.unilim.iut.spaceinvaders;

import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class Vaisseau extends Sprite {

	public Vaisseau(Dimension dimension, Position positionOrigine, int vitesse) {
		super(positionOrigine, dimension, vitesse);
	}

	public Missile tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {
		if (dimensionMissile.longueur() > this.longueur()) {
			throw new MissileException("Missile > vaisseau");
		}

		int abscisseMilieuVaisseau = this.abscisseLaPlusAGauche() + (this.longueur() / 2);
		int abscisseOrigineMissile = abscisseMilieuVaisseau - (dimensionMissile.longueur() / 2);

		int ordonneeeOrigineMissile = this.ordonneeLaPlusBasse() - 1;
		Position positionOrigineMissile = calculerLaPositionDeTirDuMissile(abscisseOrigineMissile,
				ordonneeeOrigineMissile);
		return new Missile(positionOrigineMissile, dimensionMissile, vitesseMissile);
	}

	public Position calculerLaPositionDeTirDuMissile(int abscisseOrigineMissile, int ordonneeeOrigineMissile) {
		return new Position(abscisseOrigineMissile, ordonneeeOrigineMissile);
	}

	public int hauteur() {
		// TODO Auto-generated method stub
		return dimension.hauteur();
	}
}
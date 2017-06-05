package fr.unilim.iut.spaceinvaders;

import fr.unilim.iut.spaceinvaders.utils.DebordementEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.HorsEspaceJeuException;
import fr.unilim.iut.spaceinvaders.utils.MissileException;

public class SpaceInvaders {

	int longueur;
	int hauteur;
	Vaisseau vaisseau;
	Missile missile;
	Envahisseur envahisseur;

	public SpaceInvaders(int longueur, int hauteur) {
		this.longueur = longueur;
		this.hauteur = hauteur;
	}

	public boolean estDansEspaceJeu(int x, int y) {
		return (x >= 0) && (x < longueur) && (y >= 0) && (y < hauteur);
	}

	@Override
	public String toString() {
		return recupererEspaceJeuDansChaineASCII();
	}

	public String recupererEspaceJeuDansChaineASCII() {
		StringBuilder espaceDeJeu = new StringBuilder();

		for (int y = 0; y < hauteur; y++) {
			for (int x = 0; x < longueur; x++) {
				espaceDeJeu.append(recupererMarqueDeLaPosition(x, y));
			}
			espaceDeJeu.append("\n");
		}

		return espaceDeJeu.toString();
	}

	/**
	 * @param x
	 * @param y
	 * @return
	 */
	private char recupererMarqueDeLaPosition(int x, int y) {
		char marque;
		if (this.aUnVaisseauQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_VAISSEAU;
		else if (this.aUnMissileQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_MISSILE;
		else if (this.aUnEnvahisseurQuiOccupeLaPosition(x, y))
			marque = Constante.MARQUE_ENVAHISSEUR;
		else
			marque = Constante.MARQUE_VIDE;
		return marque;
	}

	private boolean aUnMissileQuiOccupeLaPosition(int x, int y) {
		return this.aUnMissile() && missile.occupeLaPosition(x, y);
	}

	private boolean aUnMissile() {
		return missile != null;
	}
	
	private boolean aUnEnvahisseurQuiOccupeLaPosition(int x, int y) {
		return this.aUnEnvahisseur() && envahisseur.occupeLaPosition(x, y);
	}

	private boolean aUnEnvahisseur() {
		
		return envahisseur != null;
	}

	private boolean aUnVaisseauQuiOccupeLaPosition(int x, int y) {

		return this.aUnVaisseau() && vaisseau.occupeLaPosition(x, y);

	}

	public boolean aUnVaisseau() {
		return vaisseau != null;
	}

	public void deplacerVaisseauVersLaDroite() {
		if (vaisseau.abscisseLaPlusADroite() < (longueur - 1)) {
			vaisseau.deplacerHorizontalementVers(Direction.DROITE);
			if (!estDansEspaceJeu(vaisseau.abscisseLaPlusADroite(), vaisseau.ordonneeLaPlusHaute())) {
				vaisseau.positionner(longueur - vaisseau.longueur(), vaisseau.ordonneeLaPlusHaute());
			}
		}
	}

	public void deplacerVaisseauVersLaGauche() {
		if (0 < vaisseau.abscisseLaPlusAGauche())
			vaisseau.deplacerHorizontalementVers(Direction.GAUCHE);
		if (!estDansEspaceJeu(vaisseau.abscisseLaPlusAGauche(), vaisseau.ordonneeLaPlusHaute())) {
			vaisseau.positionner(0, vaisseau.ordonneeLaPlusHaute());
		}
	}

	public void positionnerUnNouveauVaisseau(Dimension dimension, Position position, int vitesse) {

		int x = position.abscisse();
		int y = position.ordonnee();

		if (!estDansEspaceJeu(x, y))
			throw new HorsEspaceJeuException("La position du vaisseau est en dehors de l'espace jeu");

		int longueurVaisseau = dimension.longueur();
		int hauteurVaisseau = dimension.hauteur();

		if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers la droite à cause de sa longueur");
		if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
			throw new DebordementEspaceJeuException(
					"Le vaisseau déborde de l'espace jeu vers le bas à cause de sa hauteur");

		vaisseau = new Vaisseau(dimension, position, vitesse);
	}

	public void tirerUnMissile(Dimension dimensionMissile, int vitesseMissile) {

		if ((vaisseau.hauteur() + dimensionMissile.hauteur()) > this.hauteur)
			throw new MissileException(
					"Pas assez de hauteur libre entre le vaisseau et le haut de l'espace jeu pour tirer le missile");

		this.missile = this.vaisseau.tirerUnMissile(dimensionMissile, vitesseMissile);
	}

	public void deplacerMissile() {
		// TODO Auto-generated method stub
		missile.deplacerVerticalementVers(Direction.HAUT_ECRAN);
		if (0>missile.ordonneeLaPlusBasse()) {
			missile = null;
		}
	}

	public void deplacerEnvahisseurVersLaDroite() {
		// TODO Auto-generated method stub
		envahisseur.deplacerHorizontalementVers(Direction.DROITE);
	}
	
	public void deplacerEnvahisseurVersLaGauche() {
		// TODO Auto-generated method stub
		envahisseur.deplacerHorizontalementVers(Direction.GAUCHE);
	}

	public void deplacerEnvahisseur() {
		if (0 == envahisseur.abscisseLaPlusAGauche())
			envahisseur.setDirection(Direction.DROITE);
		else if(longueur - 1 == envahisseur.abscisseLaPlusADroite())
			envahisseur.setDirection(Direction.GAUCHE);
		envahisseur.deplacerHorizontalementVers(envahisseur.getDirection());
	}

	public void positionnerUnNouvelEnvahisseur(Dimension dimension, Position position, int vitesse, Direction direction) {
		// TODO Auto-generated method stub
				int x = position.abscisse();
				int y = position.ordonnee();

				if (!estDansEspaceJeu(x, y))
					throw new HorsEspaceJeuException("La position de l'envahisseur est en dehors de l'espace jeu");

				int longueurVaisseau = dimension.longueur();
				int hauteurVaisseau = dimension.hauteur();

				if (!estDansEspaceJeu(x + longueurVaisseau - 1, y))
					throw new DebordementEspaceJeuException(
							"L'envahisseur déborde de l'espace jeu vers la droite à cause de sa longueur");
				if (!estDansEspaceJeu(x, y - hauteurVaisseau + 1))
					throw new DebordementEspaceJeuException(
							"L'envahisseur déborde de l'espace jeu vers le bas à cause de sa hauteur");

				envahisseur = new Envahisseur(position, dimension, vitesse, direction);
		
	}
	

}

package fr.unilim.iut.spaceinvaders;

public class Collision {

		public boolean detecterCollision(Sprite objet1,Sprite objet2){
			if (objet1.ordonneeLaPlusBasse() <= objet2.ordonneeLaPlusHaute()
					&& objet1.ordonneeLaPlusBasse() >= objet2.ordonneeLaPlusBasse()
					|| objet1.ordonneeLaPlusHaute() <= objet2.ordonneeLaPlusHaute()
							&& objet1.ordonneeLaPlusHaute() >= objet2.ordonneeLaPlusBasse()) {

					if (objet1.abscisseLaPlusADroite() <= objet2.abscisseLaPlusADroite()
						&& objet1.abscisseLaPlusADroite() >= objet2.abscisseLaPlusAGauche()
						|| objet1.abscisseLaPlusAGauche() <= objet2.abscisseLaPlusADroite()
								&& objet1.abscisseLaPlusAGauche() >= objet2.abscisseLaPlusAGauche()) {
						return true;
					}
			}
			return false;
		}
}

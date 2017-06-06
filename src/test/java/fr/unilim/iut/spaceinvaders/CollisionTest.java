package fr.unilim.iut.spaceinvaders;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CollisionTest {

	private SpaceInvaders spaceinvaders;

	@Before
	public void initialisation() {
		spaceinvaders = new SpaceInvaders(15, 10);
	}
	
	
	@Test
	public void collisionEntreSpriteBasEtHaut(){
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2),new Position(1,1), 1);
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(1, 9), 1);
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
		for (int i = 0; i < 5; i++) {
		spaceinvaders.deplacerMissile();
		}
	       assertEquals("" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       ".VVV...........\n" + 
	       ".VVV...........\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
	
	@Test
	public void collisionEntreSpriteGaucheEtDroite(){
		spaceinvaders.positionnerUnNouvelEnvahisseur(new Dimension(2,2),new Position(0,1), 1);
		spaceinvaders.positionnerUnNouveauVaisseau(new Dimension(3, 2), new Position(1, 9), 1);
		spaceinvaders.tirerUnMissile(new Dimension(1, 2), 1);
		for (int i = 0; i < 5; i++) {
		spaceinvaders.deplacerMissile();
		}
		spaceinvaders.deplacerEnvahisseur();
	       assertEquals("" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" +
	       "...............\n" + 
	       "...............\n" +
	       "...............\n" + 
	       ".VVV...........\n" + 
	       ".VVV...........\n" , spaceinvaders.recupererEspaceJeuDansChaineASCII());
	}
}
